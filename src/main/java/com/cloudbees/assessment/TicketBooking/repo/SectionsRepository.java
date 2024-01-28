package com.cloudbees.assessment.TicketBooking.repo;

import com.cloudbees.assessment.TicketBooking.Tables.Sections;
import com.cloudbees.assessment.TicketBooking.Tables.Trains;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface SectionsRepository extends CrudRepository<Sections,Integer> {

    public List<Sections> findByTrainId(Trains trains);
}
