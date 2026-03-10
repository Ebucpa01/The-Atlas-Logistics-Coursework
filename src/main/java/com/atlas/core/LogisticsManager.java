package com.atlas.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.atlas.models.Shipment;
import com.atlas.strategies.ExpressShipping;
import com.atlas.strategies.FragileShipping;
import com.atlas.strategies.StandardShipping;

/*
 * Main class thet manages shipment and performs Analysis.
 */

public class LogisticsManager {

    // list storing all shipments
    private List<Shipment> masterShipmentList;
    // initialize shipment list
    public LogisticsManager() {
        this.masterShipmentList = new ArrayList<>();
    }

    //add shipment to the system 

    public void addShipment(Shipment shipment) {
        masterShipmentList.add(shipment);
    }
    // return all stored shipments
    public List<Shipment> getMasterShipmentList() {
        return masterShipmentList;
    }
    // calculate shipping cost based on strategy
    public double calculateSingleCost(Shipment shipment) {
        String type = shipment.getType();

        if (type.equalsIgnoreCase("Express")) {
            return new ExpressShipping().calculateCost(shipment);
        } else if (type.equalsIgnoreCase("Standard")) {
            return new StandardShipping().calculateCost(shipment);
        } else if (type.equalsIgnoreCase("Fragile")) {
            return new FragileShipping().calculateCost(shipment);
        } else {

            // default to standard if unknown
            return new StandardShipping().calculateCost(shipment);
        }
    }
    // return unique destinations sorted alphabetically
    public List<String> getUniqueDestinationsSorted() {
        return masterShipmentList.stream()
                .map(Shipment::getDestination)
                .distinct()
                .sorted()
                .toList();
    }
    // total weight of shipments for a given city
    public double getTotalWeightForDestination(String city) {
        return masterShipmentList.stream()
                .filter(s -> s.getDestination().equalsIgnoreCase(city))
                .mapToDouble(Shipment::getWeight)
                .sum();
    }
    // shipments with cost above given threshold
    public List<Shipment> getHighValueShipments(double threshold) {
        return masterShipmentList.stream()
                .filter(s -> calculateSingleCost(s) > threshold)
                .collect(Collectors.toList());
    }
    // avarege cost of all shipments
    public double calculateAverageShippingCost() {
        return masterShipmentList.stream()
                .mapToDouble(this::calculateSingleCost)
                .average()
                .orElse(0.0);
    }
    
    public void processShipment(Shipment shipment) {
        validateShipment(shipment);
        masterShipmentList.add(shipment);
    }
    // validate and process shipment 
    private void validateShipment(Shipment shipment) {
        // weight must be valid
        if (shipment.getWeight() < 0 || shipment.getWeight() > 1000) {
            throw new IllegalArgumentException("Weight must be between 0 and 1000 kg");
        }
        // destination must exist
        if (shipment.getDestination() == null || shipment.getDestination().trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }
        // fragile rule
        if (shipment.getType().equalsIgnoreCase("Fragile") && shipment.getWeight() >= 50) {
            throw new IllegalArgumentException("Fragile shipments must be under 50 kg");
        }
    }
}