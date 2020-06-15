package com.Gps.Bean;

public class GpsBean {
    private String hours;
    private int cars;
    private Double rates;

    public GpsBean() {
    }

    public GpsBean(String hours, int cars, Double rates) {
        this.hours = hours;
        this.cars = cars;
        this.rates = rates;
    }



    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public int getCars() {
        return cars;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public Double getRates() {
        return rates;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return String.format("[%s,%d,%.2f]",hours,cars,rates);
    }
}
