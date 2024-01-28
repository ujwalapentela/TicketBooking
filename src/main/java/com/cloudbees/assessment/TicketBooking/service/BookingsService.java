package com.cloudbees.assessment.TicketBooking.service;

import com.cloudbees.assessment.TicketBooking.Tables.Bookings;
import com.cloudbees.assessment.TicketBooking.Tables.Sections;
import com.cloudbees.assessment.TicketBooking.Tables.Stations;
import com.cloudbees.assessment.TicketBooking.Tables.Users;
import com.cloudbees.assessment.TicketBooking.exceptions.AlreadyExistException;
import com.cloudbees.assessment.TicketBooking.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudbees.assessment.TicketBooking.repo.BookingsRepository;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class BookingsService {

    private BookingsRepository bookingsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private PricesService pricesService;

    @Autowired
    private SectionsService sectionsService;

    @Autowired
    private StationsService stationsService;



    @Autowired
    public BookingsService(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    public Bookings insertBooking(Bookings bookings){
        return bookingsRepository.save(bookings);
    }

    public Optional<Bookings> getById(int id){
        return bookingsRepository.findById(id);
    }

    public Iterable<Bookings> getAllBookings(){
        return bookingsRepository.findAll();
    }

    public Bookings insertBookingsWithIds(BookDTO bookDTO) throws Exception {
        Sections section = sectionsService.getById(bookDTO.getSectionId()).get();
        Users user = usersService.getById(bookDTO.getUserId()).get();
        Stations from = stationsService.getById(bookDTO.getFromId()).get();
        Stations to = stationsService.getById(bookDTO.getToId()).get();
        int price = bookDTO.getPrice();
        int seat = bookDTO.getSeat();

        if(seat>section.getCapacity()){
            throw new AlreadyExistException("seat you are selected is not available");
        }

        Bookings bookingForUserInASection = checkBookingForUserInASection(section,user);
        if (bookingForUserInASection!=null){
            throw new AlreadyExistException("Booking already exists for the user "+user.getFirstName()+" "+user.getLastName());
        }
        Bookings seatIsAllocatedInSection = checkSeatIsAllocatedInSection(section,seat);

        if (seatIsAllocatedInSection!=null){
            throw new AlreadyExistException("Seat is already allocated to another user");
        }

        return bookingsRepository.save(new Bookings(section,user,from,to,price,seat));

    }

    public Bookings checkBookingForUserInASection(Sections section,Users user){
        return bookingsRepository.findBySectionIdAndUserId(section,user);
    }
    public Bookings checkSeatIsAllocatedInSection(Sections section,int seat){
        return bookingsRepository.findBySectionIdAndSeat(section,seat);
    }

    public Iterable<Bookings> bookingsInASection(int sectionId){
        return bookingsRepository.findBySectionId(sectionsService.getById(sectionId).get());
    }
    public void cancelBooking(int id){
        Boolean exists = bookingsRepository.existsById(id);
        if(exists){
            bookingsRepository.deleteById(id);
        }
        else{
            throw new NoSuchElementException("There is no booking with the Id : "+id);
        }
    }
    public Bookings changeSeatForUser(int bookingId,int sectionId,int seat) throws AlreadyExistException {
        Boolean exists = bookingsRepository.existsById(bookingId);
        if(!exists){
            throw new NoSuchElementException("There is no booking with the Id : "+bookingId);
        }
        Bookings b = bookingsRepository.findById(bookingId).get();
        Sections s = sectionsService.getById(sectionId).get();
        if(seat>s.getCapacity()){
            throw new AlreadyExistException("seat you are selected is not available");
        }
        Bookings check = bookingsRepository.findBySectionIdAndSeat(s,seat);
        if(check!=null){
            throw new AlreadyExistException("seat is already allocated to another user");
        }
        b.setSeat(seat);
        b.setSectionId(s);
        return bookingsRepository.save(b);
    }

    public Bookings getUserBookings(int userId){
        Bookings b = bookingsRepository.findByUserId(usersService.getById(userId).get());
        if(b==null) throw new NoSuchElementException("No bookings found for the given user");
        return b;
    }

    public Map<Integer,String> getSeatsInfoInASection(int sectionId){
        Sections s = sectionsService.getById(sectionId).get();
        Iterable<Bookings> bookingsIterable = bookingsRepository.findBySectionId(s);
        Map<Integer,String> seats = new HashMap<>();
        for(int i=1;i<=s.getCapacity();i++){
            seats.put(i,"Available");
        }
        for(Bookings b : bookingsIterable){
            seats.put(b.getSeat(),"Booked");
        }
        return seats;
    }
}
