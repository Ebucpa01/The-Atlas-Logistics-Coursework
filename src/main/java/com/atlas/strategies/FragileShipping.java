package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

/*
 * Fragile shipping stategy.
 * Applies extra cost and weight restriction.
 */

public class FragileShipping implements ShippingStrategy {
    @Override

    public double calculateCost(Shipment shipment){

        // fragile items cannot exceed 50kg
        if (shipment.getWeight() >= 50 ) {
            throw new IllegalArgumentException("Fragile weight must be under 50kg");
        }
        //base cost
        double baseCost = (shipment.getWeight()* 0.50) + (shipment.getDistance()*0.10);
        // fragile multiplier and surcharge
        return (baseCost * 1.2) + 20.0;
    }
}