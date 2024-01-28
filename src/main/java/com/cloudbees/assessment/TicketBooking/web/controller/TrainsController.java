package com.cloudbees.assessment.TicketBooking.web.controller;

import com.cloudbees.assessment.TicketBooking.Tables.Trains;
import com.cloudbees.assessment.TicketBooking.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "trains")
public class TrainsController {

    @Autowired
    private TrainsService trainsService;

    @GetMapping(path = "/allTrains")
    public Iterable<Trains> getAllTrains() {
        return trainsService.getAllTrains();
    }

    @GetMapping(path = "/{trainId}")
    public Trains getTrainById(@PathVariable(value = "trainId") int trainId) {
        return verify(trainId);
    }

    public Trains verify(int id){
        return trainsService.getById(id).orElseThrow(()->
            new NoSuchElementException("Train does not exist"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex){
        return ex.getMessage();
    }
}
