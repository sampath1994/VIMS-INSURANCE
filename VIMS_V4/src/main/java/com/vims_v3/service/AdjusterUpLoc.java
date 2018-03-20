package com.vims_v3.service;

import com.vims_v3.model.InsuranceAdjuster;
import com.vims_v3.repository.InsuranceAdjusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by promod on 11/5/2017.
 */

@Service
public class AdjusterUpLoc {

    @Autowired
    InsuranceAdjusterRepository insuranceAdjusterRepository;

    public void adjusterUpLoc(int adjId,String latitude,String longtitude,int availability)
    {
        InsuranceAdjuster adjuster_buff = insuranceAdjusterRepository.findOne(adjId);

        adjuster_buff.setLatitude(latitude);
        adjuster_buff.setLongtitude(longtitude);
        adjuster_buff.setAvailability(availability);

        insuranceAdjusterRepository.save(adjuster_buff);
    }
}
