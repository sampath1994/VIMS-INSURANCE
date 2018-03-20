package com.vims_v3.service;

import com.vims_v3.dto.EmergencyVeDTO;
import com.vims_v3.model.EmergencyVehicleDriver;
import com.vims_v3.model.InsuredPerson;
import com.vims_v3.repository.EmergencyVehicleRepo;
import com.vims_v3.repository.InsuredPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by promod on 11/18/2017.
 */
@Service
public class GetEmerVehicle {

    @Autowired
    public EmergencyVehicleRepo emergencyVehicleRepo;

    @Autowired
    public InsuredPersonRepo insuredPersonRepo;

    public String findClosestEmerVehicle(EmergencyVeDTO emerVeDTO)
    {
        ArrayList<EmergencyVehicleDriver> driverList = new ArrayList<>();

        Iterable itr = emergencyVehicleRepo.findAll();
        Iterator it = itr.iterator();

        List<Double> distanceList = new ArrayList<>();
        double distance;

        EmergencyVehicleDriver driver;
        while(it.hasNext())
        {
            driver = (EmergencyVehicleDriver) it.next();
            distance = haversine.findDistance(Double.parseDouble(emerVeDTO.getLati()),Double.parseDouble(emerVeDTO.getLongt()),Double.parseDouble(driver.getLatitude()),Double.parseDouble(driver.getLongtitude()));
            distanceList.add(distance);
            driverList.add(driver);
        }

        EmergencyVehicleDriver driver_buff = driverList.get(distanceList.indexOf(Collections.min(distanceList)));

        InsuredPerson person = insuredPersonRepo.findOne(emerVeDTO.getInsuredPersonId());
        List<EmergencyVehicleDriver> DriverLi= new ArrayList<>();
        DriverLi.add(driver_buff);
        person.setDriverList(DriverLi);

        insuredPersonRepo.save(person);

        return "success";

    }

}
