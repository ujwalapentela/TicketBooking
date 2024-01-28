package com.cloudbees.assessment.TicketBooking.service;

import com.cloudbees.assessment.TicketBooking.Tables.Sections;
import com.cloudbees.assessment.TicketBooking.Tables.Trains;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudbees.assessment.TicketBooking.repo.SectionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionsService {

    private SectionsRepository sectionsRepository;

    @Autowired
    public SectionsService(SectionsRepository sectionsRepository) {
        this.sectionsRepository = sectionsRepository;
    }

    public Sections insertSection(Sections sections){
        return sectionsRepository.save(sections);
    }

    public Optional<Sections> getById(int id){
        return sectionsRepository.findById(id);
    }

    public Iterable<Sections> getAllSections(){
        return sectionsRepository.findAll();
    }

    public List<Sections> getSectionsInTrain(Trains trains) {
        return sectionsRepository.findByTrainId(trains);
    }
}
