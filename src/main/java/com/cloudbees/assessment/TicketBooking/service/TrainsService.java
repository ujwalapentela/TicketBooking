package com.cloudbees.assessment.TicketBooking.service;

import com.cloudbees.assessment.TicketBooking.Tables.Trains;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudbees.assessment.TicketBooking.repo.TrainsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainsService {

    private TrainsRepository trainsRepository;

    @Autowired
    public TrainsService(TrainsRepository trainsRepository) {
        this.trainsRepository = trainsRepository;
    }

    public Trains insertTrains(Trains trains){
        return trainsRepository.save(trains);
    }

    public Optional<Trains> getById(int id){
        return trainsRepository.findById(id);
    }

    public Iterable<Trains> getAllTrains(){
        return trainsRepository.findAll();
    }
}
