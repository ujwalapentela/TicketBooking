package com.cloudbees.assessment.TicketBooking.service;

import com.cloudbees.assessment.TicketBooking.Tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudbees.assessment.TicketBooking.repo.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users insertUser(Users user){
        return usersRepository.save(user);
    }

    public Optional<Users> getById(int id){
        return usersRepository.findById(id);
    }

    public Iterable<Users> getAllUsers(){
        return usersRepository.findAll();
    }

}
