package com.cloudbees.assessment.TicketBooking.web.controller;

import com.cloudbees.assessment.TicketBooking.Tables.Sections;
import com.cloudbees.assessment.TicketBooking.service.SectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "sections")
public class SectionsController {

    @Autowired
    private SectionsService sectionsService;

    @GetMapping(path = "/allSections")
    public Iterable<Sections> getAllSections(){
        return sectionsService.getAllSections();
    }

    @GetMapping(path = "/{sectionId}")
    public Sections getSectionById(@PathVariable(value = "sectionId") int sectionId){
        return verify(sectionId);
    }

    public Sections verify(int id){
        return sectionsService.getById(id).orElseThrow(()->
                new NoSuchElementException("Section does not exist"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex){
        return ex.getMessage();
    }
}
