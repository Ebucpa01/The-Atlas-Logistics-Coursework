package com.atlas.core;

import java.util.List;
import com.atlas.models.Shipment;

//import project classes from other packages
import com.atlas.models.Shipment;
import com.atlas.strategies.FragileShipping;
import com.atlas.strategies.StandartShipping;
import com.atlas.strategies.ExpressShipping;

// Some boilerplate code needs to be generated.

public class LogisticsManager {
    private List<Shipment> masterShipmentList;

    public class LogisticsManager{
        //Master list that stores all shipment in the system
        private List<Shipment> masterShipmentList;

        public LogisticManager(){
            this.masterShipmentList = new ArrayList<>();
        }

        //adds a new shipment to the master shipment list

        public void addShipment(Shipment shipment){
            masterShipmentList.add(shipment);
        }

        // returns the list of shipment
        public List<Shipment> getMasterShipmentList(){
            return masterShipmentList;
        }

        /* The method checks the shipment type and select 
         *the right shipping strategy to calculate the price.
         */

        public double calculateSingleCost(Shipment shipment){
          String type = shipment.getType();
        
            //express shipping straregy
            if (type.equalsIgnoreCase("Express")){
                return new ExpressShipping().calculateCost(shipment)
            
            //standard shipping strategy
            } else if (type.equalsIgnoreCase("Standard")){
                return new StandartShipping().calculateCost(shipment);

            //fragile shipping strategy 
            }else if(type.equalsIgnoreCase("Fragile")){
                retur ner FragileShipping().calculateCost(shipment);

            //if the type is unknown throw an error    
            }else{
                throw new IllegalArgumentException("Unknown shipment type ");
            }
            }
        }
    }

    /**
     * MLO8: Use of Streams to filter and aggregate data.
     * Find the total weight of all shipments for a specific destination.
     */
    public double getTotalWeightForDestination(String city) {
        //TODO
        return 0.0;
    }
}
