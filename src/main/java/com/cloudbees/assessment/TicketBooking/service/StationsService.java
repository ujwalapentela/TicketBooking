package com.cloudbees.assessment.TicketBooking.service;

import com.cloudbees.assessment.TicketBooking.Tables.Stations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudbees.assessment.TicketBooking.repo.StationsRepository;
import java.util.Optional;

@Service
public class StationsService {

    private StationsRepository stationsRepository;

    @Autowired
    public StationsService(StationsRepository stationsRepository) {
        this.stationsRepository = stationsRepository;
    }

    public Stations insertStation(Stations stations){
        return stationsRepository.save(stations);
    }

    public Iterable<Stations> getStations(){
        return stationsRepository.findAll();
    }

    public Optional<Stations> getById(int id){
        return stationsRepository.findById(id);
    }
}
