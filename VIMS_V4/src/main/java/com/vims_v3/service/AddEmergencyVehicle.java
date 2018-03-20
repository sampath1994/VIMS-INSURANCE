package com.vims_v3.service;

import com.vims_v3.model.Accident;
import com.vims_v3.model.EmergencyVehicleDriver;
import com.vims_v3.repository.AccidentRepository;
import com.vims_v3.repository.EmergencyVehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Created by promod on 1/26/2018.
 */

@Service
public class AddEmergencyVehicle {
    @Autowired
    AccidentRepository accidentRepository;
    @Autowired
    EmergencyVehicleRepo emergencyVehicleRepo;

    public String findClosestEmv(int accId,String lati,String longt){
        ArrayList<EmergencyVehicleDriver> drivers = new ArrayList<>();
        EmergencyVehicleDriver emergencyVehicleDriver;
        Accident accident  = accidentRepository.findOne(accId);

        Iterator iterator = emergencyVehicleRepo.findAll().iterator();

        double distance;
        ArrayList<Double> distanceList = new ArrayList<>();

        while (iterator.hasNext()){

            emergencyVehicleDriver = (EmergencyVehicleDriver) iterator.next();
            if (emergencyVehicleDriver.getAvailability()==1) {

                distance = haversine.findDistance(Double.parseDouble(longt), Double.parseDouble(lati), Double.parseDouble(emergencyVehicleDriver.getLongtitude()), Double.parseDouble(emergencyVehicleDriver.getLatitude()));
                distanceList.add(distance);
                drivers.add(emergencyVehicleDriver);
            }
        }

       // drivers.stream().filter(t ->t.getAvailability()==1).collect(Collectors.toList());
        if (!distanceList.isEmpty()) {
            int closestEMV = distanceList.indexOf(Collections.min(distanceList));

            // Iterator itr = drivers.iterator();

            EmergencyVehicleDriver closetEmVehicle = drivers.get(closestEMV);
            closetEmVehicle.setStatus(1);
            closetEmVehicle.setAccId(accId);
            emergencyVehicleRepo.save(closetEmVehicle);

            return "Informed successfully ";
        }
        else {
            return "Sorry..there is no available emergency vehicle";
        }


    }
}
