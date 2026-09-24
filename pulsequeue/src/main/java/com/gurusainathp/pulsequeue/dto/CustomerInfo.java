package com.gurusainathp.pulsequeue.dto;

public class CustomerInfo {
    private String customerId;
    private String name;
    private String segment;
    private String country;
    private String state;
    private String city;

    public CustomerInfo(String customerId, String name, String segment, String country, String state, String city) {
        this.customerId = customerId;
        this.name = name;
        this.segment = segment;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getSegment() {
        return segment;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
