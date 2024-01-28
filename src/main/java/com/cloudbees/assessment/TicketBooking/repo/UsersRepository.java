package com.cloudbees.assessment.TicketBooking.repo;

import com.cloudbees.assessment.TicketBooking.Tables.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface UsersRepository extends CrudRepository<Users,Integer>{

}
