package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

public class ExpressShipping implements ShippingStrategy {

    @Override
    public double calculateCost(Shipment shipment) {
        // TODO:
        double baseCost = (shipment.getWeight()* 0.5)+ (shipment.getDistance()*0.1);
        double expressCost = baseCost * 1.75;

        return addProcessingFee(expressCost);
    }

