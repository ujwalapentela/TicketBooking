package com.cloudbees.assessment.TicketBooking.web.controller;

import com.cloudbees.assessment.TicketBooking.Tables.Bookings;
import com.cloudbees.assessment.TicketBooking.exceptions.AlreadyExistException;
import com.cloudbees.assessment.TicketBooking.service.BookingsService;
import com.cloudbees.assessment.TicketBooking.web.Bean.BookingBean;
import com.cloudbees.assessment.TicketBooking.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "bookings")
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @GetMapping(path = "/allBookings")
    public List<BookingBean> getAllBookings(){
        List<BookingBean> bookingBeanList = new ArrayList<>();
        Iterable<Bookings> bookingsIterable =bookingsService.getAllBookings();
        for(Bookings b:bookingsIterable){
            bookingBeanList.add(convertToBookingBean(b));
        }
        return bookingBeanList;
    }

    @GetMapping(path = "/{bookingId}")
    public BookingBean getBookingById(@PathVariable(value = "bookingId") int bookingId){
        return convertToBookingBean(verfiy(bookingId));
    }
    @GetMapping(path = "/user/{userId}")
    public BookingBean getBookingByUserId(@PathVariable(value = "userId") int userId){
        return convertToBookingBean(bookingsService.getUserBookings(userId));
    }

    @GetMapping(path = "/section/{sectionId}")
    public List<BookingBean> getBookingsInSection(@PathVariable(value = "sectionId") int sectionId){
        List<BookingBean> bookingBeanList = new ArrayList<>();
        Iterable<Bookings> bookingsIterable =bookingsService.bookingsInASection(sectionId);
        for(Bookings b:bookingsIterable){
            bookingBeanList.add(convertToBookingBean(b));
        }
        return bookingBeanList;
    }

    @GetMapping(path = "/section/{sectionId}/seats")
    public Map<Integer,String> getAvailableSeatsInASection(@PathVariable(value = "sectionId") int sectionId){
        return bookingsService.getSeatsInfoInASection(sectionId);
    }

    public Bookings verfiy(int id){
        return bookingsService.getById(id).orElseThrow(() ->
                new NoSuchElementException("Booking does not exist "+ id));
    }

    @PostMapping(path = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingBean submit(@RequestBody(required = true) @Validated BookDTO bookDTO) throws Exception{
        Bookings bookings = bookingsService.insertBookingsWithIds(bookDTO);
               return convertToBookingBean(bookings);
    }

    @DeleteMapping(path = "/cancel/{bookingId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancelTicket(@PathVariable(value = "bookingId") int bookingId){
        bookingsService.cancelBooking(bookingId);
    }

    @PutMapping(path = "/updateSeat")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingBean changeSeat(@RequestHeader(value = "bookingId") int bookingId,@RequestHeader(value = "sectionId") int sectionId,@RequestHeader(value = "seat") int seat) throws Exception{
        return convertToBookingBean(bookingsService.changeSeatForUser(bookingId,sectionId,seat));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(AlreadyExistException.class)
    public String return400(AlreadyExistException ex) {
        return ex.getMessage();
    }

    public BookingBean convertToBookingBean(Bookings bookings){
        BookingBean bean = new BookingBean();
        bean.setBookingId(bookings.getId());
        bean.setUserId(bookings.getUserId().getId());
        bean.setFirstName(bookings.getUserId().getFirstName());
        bean.setLastName(bookings.getUserId().getLastName());
        bean.setEmail(bookings.getUserId().getEmail());
        bean.setPrice(bookings.getPrice());
        bean.setSeat(bookings.getSeat());
        bean.setSectionId(bookings.getSectionId().getId());
        bean.setSectionName(bookings.getSectionId().getName());
        bean.setTrainId(bookings.getSectionId().getTrainId().getId());
        bean.setTrainName(bookings.getSectionId().getTrainId().getName());
        bean.setFromStationId(bookings.getSource().getId());
        bean.setFromStationName(bookings.getSource().getName());
        bean.setToStationId(bookings.getDestination().getId());
        bean.setToStationName(bookings.getDestination().getName());

        return bean;
    }
}
