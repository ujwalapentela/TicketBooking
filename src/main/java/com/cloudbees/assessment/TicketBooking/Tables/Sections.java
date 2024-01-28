package com.cloudbees.assessment.TicketBooking.Tables;

import jakarta.persistence.*;

@Entity
public class Sections {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Trains trainId;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    public Sections(Trains trainId, String name, int capacity) {
        this.trainId = trainId;
        this.name = name;
        this.capacity = capacity;
    }

    protected Sections(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trains getTrainId() {
        return trainId;
    }

    public void setTrainId(Trains trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Sections{" +
                "id=" + id +
                ", trainId=" + trainId +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
