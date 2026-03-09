package com.atlas.core;

import java.util.ArrayList;
import java.util.List;

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
            throw new IllegalArgumentException("Unknown shipment type: " + type);
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
}