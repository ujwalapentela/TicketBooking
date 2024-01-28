package com.cloudbees.assessment.TicketBooking.web.controller;

import com.cloudbees.assessment.TicketBooking.Tables.Users;
import com.cloudbees.assessment.TicketBooking.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping(path = "users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(path = "/allUsers")
    public Iterable<Users> getUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping(path = "/{userId}")
    public Users getUserById(@PathVariable(value = "userId") int userId){
        return verify(userId);
    }

    public Users verify(int id){
        return usersService.getById(id).orElseThrow(()->
                new NoSuchElementException("User does not exist"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex){
        return ex.getMessage();
    }
}
