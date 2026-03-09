package com.atlas.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Shipment {

    private String name;
    private double weight;
    private double distance;
    private String destination;
    private String type;

}