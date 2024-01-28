package com.cloudbees.assessment.TicketBooking.web.controller;

import com.cloudbees.assessment.TicketBooking.Tables.Prices;
import com.cloudbees.assessment.TicketBooking.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "prices")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @GetMapping(path = "/allPrices")
    public Iterable<Prices> getAllPrices(){
        return pricesService.getAllPrices();
    }

    @GetMapping(path = "/{priceId}")
    public Prices getPriceById(@PathVariable(value = "priceId") int priceId){
        return verify(priceId);
    }

    public Prices verify(int id){
        return pricesService.getById(id).orElseThrow(()->
                new NoSuchElementException("Price does not exist"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex){
        return ex.getMessage();
    }
}
