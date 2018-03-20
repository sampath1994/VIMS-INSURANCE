package com.vims_v3.service;

import com.vims_v3.model.Payment;
import com.vims_v3.repository.EmergencyVehicleRepo;
import com.vims_v3.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by promod on 1/26/2018.
 */
@Service
public class EmvPaymentAcceptance {
    @Autowired
    EmergencyVehicleRepo emergencyVehicleRepo;
    @Autowired
    EmergencyVPayment emergencyVPayment;
    @Autowired
    PaymentRepository paymentRepository;

    public String setAcceptance(String status,int emvId,int distance){

        if (status.equals("yes")){
            Payment payment = new Payment();

            payment.setBillId(generateBillId(emvId));
            payment.setEmergencyvId(emergencyVehicleRepo.findOne(emvId));
            payment.setPaymentamount(emergencyVPayment.addPayment(emvId,distance));

            paymentRepository.save(payment);

            return "successfully added your payment";
        }
        else {
            return null;
        }

    }
    private int generateBillId(int vid){
        DateFormat dateFormat = new SimpleDateFormat("ddMMHH");
        Date date = new Date();
        Integer a = vid;
        String b = a.toString();
        String c = dateFormat.format(date).toString();
        String d = b+c;
        int billId = Integer.parseInt(d);
        return billId;
    }
}
