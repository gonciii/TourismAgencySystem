package entity;

import java.time.LocalDate;

public class Reservation {

    private int id;
    private String customer_name;
    private Hotel hotel;
    private LocalDate strt_date;
    private LocalDate fnsh_date;
    private int adult_number;
    private int child_number;
    private int total_price;

    public Reservation(int id, String customer_name, Hotel hotel, LocalDate strt_date, LocalDate fnsh_date, int adult_number, int child_number, int total_price) {
        this.id = id;
        this.customer_name = customer_name;
        this.hotel = hotel;
        this.strt_date = strt_date;
        this.fnsh_date = fnsh_date;
        this.adult_number = adult_number;
        this.child_number = child_number;
        this.total_price = total_price;
    }
    public Reservation(String customer_name, Hotel hotel, LocalDate strt_date, LocalDate fnsh_date, int adult_number, int child_number, int total_price) {
        this.customer_name = customer_name;
        this.hotel = hotel;
        this.strt_date = strt_date;
        this.fnsh_date = fnsh_date;
        this.adult_number = adult_number;
        this.child_number = child_number;
        this.total_price = total_price;
    }

    public Reservation() {

    }

    public Reservation(String customerName, Hotel selectedOtel, LocalDate strtDate, LocalDate fnshDate, String adultNumber, String childNumber) {
        this.customer_name = customer_name;
        this.hotel = hotel;
        this.strt_date = strt_date;
        this.fnsh_date = fnsh_date;
        this.adult_number = adult_number;
        this.child_number = child_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Hotel getOtel() {
        return hotel;
    }

    public void setOtel(Hotel otel) {
        this.hotel= otel;
    }

    public LocalDate getStrt_date() {
        return strt_date;
    }

    public void setStrt_date(LocalDate strt_date) {
        this.strt_date = strt_date;
    }

    public LocalDate getFnsh_date() {
        return fnsh_date;
    }

    public void setFnsh_date(LocalDate fnsh_date) {
        this.fnsh_date = fnsh_date;
    }

    public int getAdult_number() {
        return adult_number;
    }

    public void setAdult_number(int adult_number) {
        this.adult_number = adult_number;
    }

    public int getChild_number() {
        return child_number;
    }

    public void setChild_number(int child_number) {
        this.child_number = child_number;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        total_price = total_price + (hotel.getRoomtype().getPrice() * adult_number) + ((hotel.getRoomtype().getPrice() * child_number)/2);
        this.total_price = total_price;
    }


}
