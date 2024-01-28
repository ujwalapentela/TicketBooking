package com.cloudbees.assessment.TicketBooking.Tables;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Trains {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Stations source;

    @ManyToOne
    private Stations destination;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    public Trains(String name, Stations source, Stations destination, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected Trains(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stations getSource() {
        return source;
    }

    public void setSource(Stations source) {
        this.source = source;
    }

    public Stations getDestination() {
        return destination;
    }

    public void setDestination(Stations destination) {
        this.destination = destination;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Trains{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source=" + source +
                ", destination=" + destination +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
