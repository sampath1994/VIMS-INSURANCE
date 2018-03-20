package com.vims_v3.service;
import com.vims_v3.model.Accident;
import com.vims_v3.model.Vehicle;
import com.vims_v3.repository.AccidentRepository;
import com.vims_v3.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Service
public class ReportService {

    @Autowired
    public AccidentRepository accidentRepository;

    @Autowired
    public VehicleRepository vehicleRepository;

    public Integer saveAccident(String license,String lat,String longt) {

        Accident accident = new Accident();
        accident.setLatitude(lat);
        accident.setLongtitude(longt);
        accident.setCoverage(false);
       /* accident.setClaimingManagerId(null);*/
        Date date = new Date();
        accident.setDate(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        accident.setDatetime(dateFormat.format(date));
       /* accident.setDamageAmount("10000LKR");*/

       /* accident.setVehicleList(null);*/
       /* accident.setAccidentGPSLocation(null);*/

      /*Here the Vehicle is choosen with given lisence plate no.*/
      /*2nd comment for test*/
        Vehicle savedVehicle = vehicleRepository.findByLicense(license);

           if(savedVehicle != null) {
            ArrayList<Vehicle> buffer = new ArrayList<Vehicle>();
            buffer.add(savedVehicle);

            accident.setVehicleList(buffer);

            Accident createdAccident = accidentRepository.save(accident);

            return createdAccident.getAccidentId();
           }
           else {
               return -1;
           }
           }
}
