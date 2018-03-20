package com.vims_v3.service;

import com.vims_v3.dto.AccidentDTO;
import com.vims_v3.model.Accident;
import com.vims_v3.model.EmergencyVehicleDriver;
import com.vims_v3.repository.AccidentRepository;
import com.vims_v3.repository.EmergencyVehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by promod on 1/26/2018.
 */

@Service
public class GetLocationfromEmv {

    @Autowired
    AccidentRepository accidentRepository;
    @Autowired
    AddEmergencyVehicle findOne;
    @Autowired
    GetEmerVehicle getEmerVehicle;
    @Autowired
    EmergencyVehicleRepo emergencyVehicleRepo;

    public AccidentDTO getLocationToAcc(){

        ArrayList<EmergencyVehicleDriver> emergencyV = new ArrayList<>();

        Iterator iterator = emergencyVehicleRepo.findAll().iterator();

        while (iterator.hasNext()){
            EmergencyVehicleDriver driver = (EmergencyVehicleDriver) iterator.next();
            emergencyV.add(driver);
        }

        EmergencyVehicleDriver eDriver = emergencyV.stream().filter(t -> t.getStatus()==1).findAny().get();

        Accident accident  = accidentRepository.findOne(eDriver.getAccId());

        if (eDriver != null){
            AccidentDTO accidentDTO = new AccidentDTO(accident.getAccidentId(),accident.getLatitude(),accident.getLongtitude());
            eDriver.setStatus(0);
            eDriver.setAvailability(0);
            emergencyVehicleRepo.save(eDriver);
            return accidentDTO;
        }
        else {
            return new AccidentDTO(0,"00.00","00.00");
        }
    }
}
