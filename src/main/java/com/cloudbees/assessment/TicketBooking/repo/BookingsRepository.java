package com.cloudbees.assessment.TicketBooking.repo;

import com.cloudbees.assessment.TicketBooking.Tables.Bookings;
import com.cloudbees.assessment.TicketBooking.Tables.Sections;
import com.cloudbees.assessment.TicketBooking.Tables.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface BookingsRepository extends CrudRepository<Bookings,Integer> {
    public Bookings findBySectionIdAndUserId(Sections sections, Users users);
    public Bookings findBySectionIdAndSeat(Sections sections,int seat);
    public Iterable<Bookings> findBySectionId(Sections sections);
    public Bookings findByUserId(Users users);
}
