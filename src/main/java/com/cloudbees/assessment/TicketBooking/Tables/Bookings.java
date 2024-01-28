package com.cloudbees.assessment.TicketBooking.Tables;

import jakarta.persistence.*;

@Entity
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Sections sectionId;

    @ManyToOne
    private Users userId;

    @ManyToOne
    private Stations source;

    @ManyToOne
    private Stations destination;

    @Column(name = "price")
    private int price;

    @Column(name = "seat")
    private int seat;

    public Bookings(Sections sectionId, Users userId, Stations source, Stations destination, int price, int seat) {
        this.sectionId = sectionId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.price = price;
        this.seat = seat;
    }

    protected Bookings(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sections getSectionId() {
        return sectionId;
    }

    public void setSectionId(Sections sectionId) {
        this.sectionId = sectionId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", userId=" + userId +
                ", source=" + source +
                ", destination=" + destination +
                ", price=" + price +
                ", seat=" + seat +
                '}';
    }
}
