package com.cloudbees.assessment.TicketBooking.web.controller;


import com.cloudbees.assessment.TicketBooking.Tables.Stations;
import com.cloudbees.assessment.TicketBooking.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "stations")
public class StationsController {

    @Autowired
    private StationsService stationsService;

    @GetMapping(path = "/allStations")
    public Iterable<Stations> getAllStations(){
        return stationsService.getStations();
    }

    @GetMapping(path = "/{stationId}")
    public Stations getStationsById(@PathVariable(value = "stationId") int stationId){
        return verify(stationId);
    }

    public Stations verify(int id){
        return stationsService.getById(id).orElseThrow(()->
                new NoSuchElementException("Station does not exist"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex){
        return ex.getMessage();
    }
}
