package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

/*
 * Express shipping calculation 
 * Uses higher multiplier and includes processing fee.
 */

public class ExpressShipping implements ShippingStrategy {

    @Override
    public double calculateCost(Shipment shipment) {
        // TODO:

        //base cost calculation
        double baseCost = (shipment.getWeight()* 0.5)+ (shipment.getDistance()*0.1);
        //express multiplier
        double expressCost = baseCost * 1.75;
        //add processing fee
        return expressCost + addProcessingFee(expressCost);
    }
}

