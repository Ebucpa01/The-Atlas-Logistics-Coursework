package com.atlas.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.atlas.models.Shipment;
import com.atlas.strategies.ExpressShipping;
import com.atlas.strategies.FragileShipping;
import com.atlas.strategies.StandardShipping;

public class LogisticsManager {

    private List<Shipment> masterShipmentList;

    public LogisticsManager() {
        this.masterShipmentList = new ArrayList<>();
    }

    public void addShipment(Shipment shipment) {
        masterShipmentList.add(shipment);
    }

    public List<Shipment> getMasterShipmentList() {
        return masterShipmentList;
    }

    public double calculateSingleCost(Shipment shipment) {
        String type = shipment.getType();

        if (type.equalsIgnoreCase("Express")) {
            return new ExpressShipping().calculateCost(shipment);
        } else if (type.equalsIgnoreCase("Standard")) {
            return new StandardShipping().calculateCost(shipment);
        } else if (type.equalsIgnoreCase("Fragile")) {
            return new FragileShipping().calculateCost(shipment);
        } else {
            return new StandardShipping().calculateCost(shipment);
        }
    }

    public List<String> getUniqueDestinationsSorted() {
        return masterShipmentList.stream()
                .map(Shipment::getDestination)
                .distinct()
                .sorted()
                .toList();
    }

    public double getTotalWeightForDestination(String city) {
        return masterShipmentList.stream()
                .filter(s -> s.getDestination().equalsIgnoreCase(city))
                .mapToDouble(Shipment::getWeight)
                .sum();
    }

    public List<Shipment> getHighValueShipments(double threshold) {
        return masterShipmentList.stream()
                .filter(s -> calculateSingleCost(s) > threshold)
                .collect(Collectors.toList());
    }

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

    private void validateShipment(Shipment shipment) {

        if (shipment.getWeight() < 0 || shipment.getWeight() > 1000) {
            throw new IllegalArgumentException("Weight must be between 0 and 1000 kg");
        }

        if (shipment.getDestination() == null || shipment.getDestination().trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }

        if (shipment.getType().equalsIgnoreCase("Fragile") && shipment.getWeight() >= 50) {
            throw new IllegalArgumentException("Fragile shipments must be under 50 kg");
        }
    }
}