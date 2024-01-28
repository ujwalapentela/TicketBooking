package com.cloudbees.assessment.TicketBooking.Tables;

import jakarta.persistence.*;;

@Entity
public class Prices {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Stations source;

    @ManyToOne
    private Stations destination;

    @Column(name = "price")
    private int price;

    public Prices(Stations source, Stations destination, int price) {
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    protected Prices(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Prices{" +
                "id=" + id +
                ", source=" + source +
                ", destination=" + destination +
                ", price=" + price +
                '}';
    }
}
