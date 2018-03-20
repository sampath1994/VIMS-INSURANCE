package com.vims_v3.service;

import com.vims_v3.model.Garage;
import com.vims_v3.model.GarageHistory;
import com.vims_v3.model.Vehicle;
import com.vims_v3.repository.GarageHistoryRepo;
import com.vims_v3.repository.GarageRepository;
import com.vims_v3.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by promod on 11/18/2017.
 */
@Service
public class SentToGarage {

    @Autowired
    public VehicleRepository vehicleRepository;

    @Autowired
    public GarageRepository garageRepository;

    @Autowired
    public GarageHistoryRepo garageHistoryRepo;

    public String setVehicleToGarage(Integer GarageId,String licencePlate)
    {
       Vehicle vehicle =  vehicleRepository.findByLicense(licencePlate);
       Garage garage = garageRepository.findOne(GarageId);

        List<GarageHistory> garageHistoryList = new ArrayList<>();
        GarageHistory garageHistory =new GarageHistory(garage.getGarageId(),vehicle.getVehicleId());
        garageHistory.setDate(new Date());
        garageHistory.setTime(new Date());
        garageHistoryList.add(garageHistory);
       garage.setGarageHistoryList(garageHistoryList);
       vehicle.setGarageHistoryList(garageHistoryList);

       vehicleRepository.save(vehicle);
       garageRepository.save(garage);

       return "success";
    }

}
