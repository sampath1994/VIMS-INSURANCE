package com.vims_v3.service;

import com.vims_v3.repository.EmergencyVehicleRepo;
import com.vims_v3.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by promod on 1/26/2018.
 */
@Service
public class EmergencyVPayment {
    final static int costPerKm1 = 150;
    final static int costPerKm2 = 200;

    @Autowired
    EmergencyVehicleRepo emergencyVehicleRepo;
    @Autowired
    PaymentRepository paymentRepository;

    public int addPayment(int emvId,int distance){

        int cost;

        if (distance<=2) {
            cost = distance * costPerKm1;
        }else {
            cost = distance * costPerKm2;
        }

        return cost;
    }
}
