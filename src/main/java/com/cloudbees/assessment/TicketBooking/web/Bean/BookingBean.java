package com.cloudbees.assessment.TicketBooking.web.Bean;

public class BookingBean {
    private int bookingId;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private int price;
    private int seat;
    private int sectionId;
    private String sectionName;
    private int trainId;
    private String trainName;
    private int fromStationId;
    private String fromStationName;
    private int toStationId;
    private String toStationName;

    public BookingBean(int bookingId, int userId, String firstName, String lastName, String email, int price, int seat, int sectionId, String sectionName, int trainId, String trainName, int fromStationId, String fromStationName, int toStationId, String toStationName) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.price = price;
        this.seat = seat;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.trainId = trainId;
        this.trainName = trainName;
        this.fromStationId = fromStationId;
        this.fromStationName = fromStationName;
        this.toStationId = toStationId;
        this.toStationName = toStationName;
    }

    public BookingBean(){

    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(int fromStationId) {
        this.fromStationId = fromStationId;
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }
}
