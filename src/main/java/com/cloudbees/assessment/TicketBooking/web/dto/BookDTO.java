package com.cloudbees.assessment.TicketBooking.web.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookDTO {

    @NotNull
    @Min(value = 1)
    private Integer userId;

    @NotNull
    @Min(value = 1)
    private Integer fromId;

    @NotNull
    @Min(value = 1)
    private Integer toId;

    @NotNull
    @Min(value = 1)
    private Integer sectionId;

    @NotNull
    @Min(value = 1)
    private Integer price;

    @NotNull
    @Min(value = 1)
    private Integer seat;

    public BookDTO(Integer userId, Integer fromId, Integer toId, Integer sectionId, Integer price, Integer seat) {
        this.userId = userId;
        this.fromId = fromId;
        this.toId = toId;
        this.sectionId = sectionId;
        this.price = price;
        this.seat = seat;
    }

    protected BookDTO(){}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
