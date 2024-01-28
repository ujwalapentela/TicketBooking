package com.cloudbees.assessment.TicketBooking.service;

import com.cloudbees.assessment.TicketBooking.Tables.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudbees.assessment.TicketBooking.repo.PricesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PricesService {

    private PricesRepository pricesRepository;

    @Autowired
    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public Prices insertPrices(Prices prices){
        return pricesRepository.save(prices);
    }

    public Optional<Prices> getById(int id){
        return pricesRepository.findById(id);
    }

    public Iterable<Prices> getAllPrices(){
        return pricesRepository.findAll();
    }
}
