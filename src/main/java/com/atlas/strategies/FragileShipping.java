package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

public class FragileShipping implements ShippingStrategy {
    @Override

    public double calculateCost(Shipment shipment){
        if (shipment.getWeight() >= 50 ) {
            throw new IllegalArgumentException("Fragile weight must be under 50kg");
        }
        double baseCost = (shipment.getWeight()* 0.50) + (shipment.getDistance()*0.10);
        return (baseCost * 1.2) + 20.0;
    }
}