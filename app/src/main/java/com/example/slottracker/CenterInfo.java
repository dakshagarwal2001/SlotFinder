package com.example.slottracker;

import java.io.Serializable;

public class CenterInfo implements Serializable {
    String name;
    int pin;
    int min_age_limit;
    int slots_available;
    String vaccine;


    public CenterInfo(String name, int pin, int min_age_limit, int slots_available, String vaccine) {
        this.name = name;
        this.pin = pin;
        this.min_age_limit = min_age_limit;
        this.slots_available = slots_available;
        this.vaccine = vaccine;
    }
}

