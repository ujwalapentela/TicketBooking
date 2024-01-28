package com.cloudbees.assessment.TicketBooking.exceptions;

public class AlreadyExistException extends Exception{

    public AlreadyExistException(String message) {
        super(message);
    }
}
