package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

/*
 * Standart shipping calculation 
 * Uses only base formula.
 */
 
public class StandardShipping implements ShippingStrategy{

    @Override
    public double calculateCost (Shipment shipment){

    // basic price formula    
    return (shipment.getWeight() * 0.5) + (shipment.getDistance() * 0.10);
 }
 
}