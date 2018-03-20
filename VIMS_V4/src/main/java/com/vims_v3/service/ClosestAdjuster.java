package com.vims_v3.service;


import com.vims_v3.model.Accident;
import com.vims_v3.model.InsuranceAdjuster;
import com.vims_v3.repository.AccidentRepository;
import com.vims_v3.repository.InsuranceAdjusterRepository;
import com.vims_v3.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by promod on 11/1/2017.
 */
@Service
public class ClosestAdjuster {

    @Autowired
    public InsuranceAdjusterRepository insuranceAdjusterRepository;

    @Autowired
    public AccidentRepository accidentRepository;

    public InsuranceAdjuster findClosestAdjuster(int accidentId)
    {
        ArrayList<InsuranceAdjuster> adjusterList = new ArrayList<>();

        Iterable itr = insuranceAdjusterRepository.findAll();
        Iterator it = itr.iterator();

        List<Double> distanceList = new ArrayList<>();
        double distance;

        Accident accident_buff =  accidentRepository.findOne(accidentId);


        Integer adjCount =0;
        InsuranceAdjuster adjuster;
        while(it.hasNext())
        {
            adjuster = (InsuranceAdjuster) it.next();
            if(adjuster.getAvailability()==1) {           //added later
                distance = haversine.findDistance(Double.parseDouble(accident_buff.getLatitude()), Double.parseDouble(accident_buff.getLongtitude()), Double.parseDouble(adjuster.getLatitude()), Double.parseDouble(adjuster.getLongtitude()));
                distanceList.add(distance);
                adjusterList.add(adjuster);
                adjCount++;
            }
        }

            if(adjCount != 0) {

                /**new code*/
                accident_buff.setCoverage(true);
                accidentRepository.save(accident_buff);
                /*******/
                InsuranceAdjuster adjuster_buffer = adjusterList.get(distanceList.indexOf(Collections.min(distanceList)));

                adjuster_buffer.setAvailability(0);   //added later

                adjuster_buffer.getAccidentList().add(accident_buff);

                accident_buff.setInsuranceAjusterId(adjuster_buffer);

                return insuranceAdjusterRepository.save(adjuster_buffer);
            }else{

                return new InsuranceAdjuster(-1);
            }
    }

}
