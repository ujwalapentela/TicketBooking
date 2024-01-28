package com.cloudbees.assessment.TicketBooking.web.controller;


import com.cloudbees.assessment.TicketBooking.Tables.*;
import com.cloudbees.assessment.TicketBooking.TicketBookingApplication;
import com.cloudbees.assessment.TicketBooking.exceptions.AlreadyExistException;
import com.cloudbees.assessment.TicketBooking.service.BookingsService;
import com.cloudbees.assessment.TicketBooking.web.dto.BookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingsController.class)
class BookingsControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketBookingApplication ticketBookingApplication;
   /* @MockBean
    private StationsService stationsService;
    @MockBean
    private UsersService usersService;
    @MockBean
    private PricesService pricesService;
    @MockBean
    private TrainsService trainsService;
    @MockBean
    private SectionsService sectionsService;*/
    @MockBean
    private BookingsService bookingsService;

    private static Stations st1,st2;
    private static Users u1,u2,u3,u4,u5;
    private static Prices p1;
    private static Trains t1;
    private static Sections s1,s2;

    @BeforeAll
    public static void initialize() throws Exception{

        st1 =  new Stations("testStation1","TST1");
        st1.setId(1);
        st2 =  new Stations("testStation2","TST2");
        st2.setId(2);

        u1 = new Users("testFN1","testLN1","test1@gmail.com","");
        u1.setId(1);
        u2 = new Users("testFN2","testLN2","test2@gmail.com","");
        u2.setId(2);
        u3 = new Users("testFN3","testLN3","test3@gmail.com","");
        u3.setId(3);
        u4 = new Users("testFN4","testLN4","test4@gmail.com","");
        u4.setId(4);
        u5 = new Users("testFN5","testLN5","test5@gmail.com","");
        u5.setId(5);

        p1 = new Prices(st1,st2,20);
        p1.setId(1);

        t1 = new Trains("testTrain1",st1,st2, LocalDateTime.now(),LocalDateTime.now().plusDays(1));
        t1.setId(1);

        s1 = new Sections(t1,"testSection1",5);
        s1.setId(1);
        s2 = new Sections(t1,"testSection2",5);
        s2.setId(2);

    }

    @Test
    void shouldGetAllBookingDetails() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        Bookings b2 = new Bookings(s2,u3,st1,st2,20,3);
        b2.setId(2);
        List<Bookings> bookingsList = new ArrayList<>();
        bookingsList.add(b1);
        bookingsList.add(b2);
        Mockito.when(bookingsService.getAllBookings()).thenReturn(bookingsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/allBookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].bookingId").value(1))
                .andExpect(jsonPath("$[0].email").value(u1.getEmail()))
                .andExpect(jsonPath("$[0].sectionId").value(s1.getId()))
                .andExpect(jsonPath("$[0].seat").value(1))
                .andExpect(jsonPath("$[1].bookingId").value(2))
                .andExpect(jsonPath("$[1].email").value(u3.getEmail()))
                .andExpect(jsonPath("$[1].sectionId").value(s2.getId()))
                .andExpect(jsonPath("$[1].seat").value(3))
                .andDo(print());

    }
    @Test
    void shouldGetBookingDetailsOfAId() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        Mockito.when(bookingsService.getById(1)).thenReturn(Optional.of(b1));

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.bookingId").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.email").value(u1.getEmail()))
                .andDo(print());
    }
    @Test
    void shouldGet404NotFoundWhenNoBookingPresent() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        Mockito.when(bookingsService.getById(2)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/2"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldGet404NotFoundWhenNoBookingForGivenUser() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        Mockito.when(bookingsService.getUserBookings(5)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/user/5"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldGetBookingDetailsOfAUser() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        Mockito.when(bookingsService.getUserBookings(u1.getId())).thenReturn(b1);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.bookingId").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.email").value(u1.getEmail()))
                .andDo(print());
    }

    @Test
    void shouldGetSeatsDetailsInSection() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        List<Bookings> bookingsList = new ArrayList<>();
        bookingsList.add(b1);
        Iterable<Bookings> bookingsIterable = bookingsList;
        Mockito.when(bookingsService.bookingsInASection(s1.getId())).thenReturn(bookingsIterable);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/section/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].bookingId").value(1))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].email").value(u1.getEmail()))
                .andDo(print());
    }

    @Test
    void shouldGetEmptyListWhenNoBookingsInSection() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        List<Bookings> bookingsList = new ArrayList<>();
//        bookingsList.add(b1);
        Iterable<Bookings> bookingsIterable = bookingsList;
        Mockito.when(bookingsService.bookingsInASection(s2.getId())).thenReturn(bookingsIterable);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/section/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").value(bookingsList))
                .andDo(print());
    }

    @Test
    void shouldGetAvailableSeatsInSection() throws Exception {
        Bookings b1  = new Bookings(s1,u1,st1,st2,20,1);
        b1.setId(1);
        Map<Integer,String> seatsInfo = new HashMap<>();
        seatsInfo.put(1,"Booked");
        seatsInfo.put(2,"Available");
        seatsInfo.put(3,"Available");
        seatsInfo.put(4,"Available");
        seatsInfo.put(5,"Available");

        Mockito.when(bookingsService.getSeatsInfoInASection(s1.getId())).thenReturn(seatsInfo);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/section/1/seats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.1").value("Booked"))
                .andExpect(jsonPath("$.2").value("Available"))
                .andExpect(jsonPath("$.3").value("Available"))
                .andExpect(jsonPath("$.4").value("Available"))
                .andExpect(jsonPath("$.5").value("Available"))

                .andDo(print());
    }

    @Test
    void shouldReturn201Created() throws Exception {
        Bookings bookings  = new Bookings(s1,u1,st1,st2,20,1);
        bookings.setId(1);
        BookDTO bookDTO = new BookDTO(1,1,2,1,20,1);
        Mockito.when(bookingsService.insertBookingsWithIds(any())).thenReturn(bookings);
        String requestBody = objectMapper.writeValueAsString(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/bookings/book")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(bookings.getId()))
                .andExpect(jsonPath("$.sectionId").value(bookDTO.getSectionId()))
                .andExpect(jsonPath("$.userId").value(bookDTO.getUserId()))
                .andExpect(jsonPath("$.fromStationId").value(bookDTO.getFromId()))
                .andExpect(jsonPath("$.toStationId").value(bookDTO.getToId()))
                .andExpect(jsonPath("$.price").value(bookDTO.getPrice()))
                .andExpect(jsonPath("$.seat").value(bookDTO.getSeat()))
                .andDo(print());
    }

    @Test
    void shouldReturnAlreadyReportedWhenBookingForExistingData() throws Exception {
        Bookings bookings  = new Bookings(s1,u1,st1,st2,20,1);
        bookings.setId(1);
        BookDTO bookDTO = new BookDTO(1,1,2,1,20,1);
        Mockito.when(bookingsService.insertBookingsWithIds(any())).thenThrow(AlreadyExistException.class);
        String requestBody = objectMapper.writeValueAsString(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/bookings/book")
                        .contentType("application/json")
                        .content(requestBody))
                        .andExpect(status().isAlreadyReported())
                        .andDo(print());
    }

    @Test
    void shouldReturn400BadRequestWhenValuesAreMissing() throws Exception {
        BookDTO bookDTO = new BookDTO(0,0,0,0,0,0);
        String requestBody = objectMapper.writeValueAsString(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/bookings/book")
                        .contentType("application/json")
                        .content(requestBody))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
    }

    @Test
    void shouldReturn200WhenDeleted() throws Exception {
        Bookings bookings  = new Bookings(s1,u1,st1,st2,20,1);
        bookings.setId(1);
        Mockito.doNothing().when(bookingsService).cancelBooking(bookings.getId());
        mockMvc.perform(MockMvcRequestBuilders.delete("/bookings/cancel/1"))
                .andExpect(status().isAccepted())
                .andDo(print());
    }

    @Test
    void shouldReturn404WhenThereIsNoBooking() throws Exception {
        Bookings bookings  = new Bookings(s1,u1,st1,st2,20,1);
        bookings.setId(1);
        Mockito.doThrow(NoSuchElementException.class).when(bookingsService).cancelBooking(2);
        mockMvc.perform(MockMvcRequestBuilders.delete("/bookings/cancel/2"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldChangeSeat () throws Exception {
        Bookings bookings  = new Bookings(s1,u1,st1,st2,20,5);
        bookings.setId(1);
        Mockito.when(bookingsService.changeSeatForUser(1,1,5)).thenReturn(bookings);
        mockMvc.perform(MockMvcRequestBuilders.put("/bookings/updateSeat")
                .header("bookingId",1)
                .header("sectionId",1)
                .header("seat",5))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.seat").value(5))
                .andExpect(jsonPath("$.bookingId").value(1))
                .andExpect(jsonPath("$.sectionId").value(1));
    }

    @Test
    void shouldReturnAlreadyReportedStatusWhenSeatIsNotAvailableOrBooked() throws Exception {
        Bookings bookings  = new Bookings(s1,u1,st1,st2,20,5);
        bookings.setId(1);
        Mockito.when(bookingsService.changeSeatForUser(1,1,5)).thenThrow(AlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/bookings/updateSeat")
                        .header("bookingId",1)
                        .header("sectionId",1)
                        .header("seat",5))
                .andExpect(status().isAlreadyReported());
    }

    @Test
    void shouldReturn404NotFoundStatusWhenThereIsNoBooking() throws Exception {
        Bookings bookings  = new Bookings(s1,u1,st1,st2,20,5);
        bookings.setId(1);
        Mockito.when(bookingsService.changeSeatForUser(1,1,5)).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/bookings/updateSeat")
                        .header("bookingId",1)
                        .header("sectionId",1)
                        .header("seat",5))
                .andExpect(status().isNotFound());
    }
}
