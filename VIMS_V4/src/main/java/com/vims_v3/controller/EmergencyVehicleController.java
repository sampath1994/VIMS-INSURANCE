package com.vims_v3.controller;

import com.vims_v3.dto.AccidentDTO;
import com.vims_v3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by promod on 1/26/2018.
 */
@RestController
public class EmergencyVehicleController {
    @Autowired
    AddEmergencyVehicle addEmergencyVehicle;
    @Autowired
    GetLocationfromEmv getLocationfromEmv;
    @Autowired
    SetAvailabiltyEmv setAvailabiltyEmv;
    @Autowired
    EmergencyVPayment emergencyVPayment;
    @Autowired
    EmvPaymentAcceptance emvPaymentAcceptance;




    @RequestMapping("/getLocation")
    public AccidentDTO sendLocToEmv(){

        return getLocationfromEmv.getLocationToAcc();
    }

    @RequestMapping("/emvAvail/{emv}/{lat}/{longt}/{availability}")
    public void availabilityEmv(@PathVariable int emv,@PathVariable String lat,@PathVariable String longt, @PathVariable Integer availability){

        setAvailabiltyEmv.setAvailability(emv,lat,longt,availability);
    }

    @RequestMapping("/getPay/{emvId}/{dis}")
    public int getPayment(@PathVariable int emvId,@PathVariable int dis){

        return emergencyVPayment.addPayment(emvId,dis);
    }

    @RequestMapping("/acceptance/{status}/{emvId}/{dis}")
    public String acceptPayment(@PathVariable String status,@PathVariable int emvId,@PathVariable int dis){

        return emvPaymentAcceptance.setAcceptance(status,emvId,dis);
    }


}
