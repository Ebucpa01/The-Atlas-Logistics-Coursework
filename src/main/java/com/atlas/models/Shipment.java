package com.atlas.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* 
 * Represents a shipment in the system.
 * Lombok generates constructor and getters.
 * /
@Getter
@AllArgsConstructor
public class Shipment {

    private String name;
    private double weight;
    private double distance;
    private String destination;
    private String type;

}