package com.vims_v3.service;

import com.vims_v3.dto.GarageDTO;
import com.vims_v3.model.Garage;

import java.util.Comparator;

/**
 * Created by promod on 11/14/2017.
 */
public class GarageComparator implements Comparator<GarageDTO> {

    @Override
    public int compare(GarageDTO obj1, GarageDTO obj2) {

        // Sort TreeMap based on name
        //return obj1.getName().compareTo(obj2.getName());

        // Sort TreeMap based on salary
        if(obj1.getGarageId() > obj2.getGarageId()) return 1;
        else if(obj1.getGarageId() < obj2.getGarageId()) return -1;
        else return 0;
    }
}

