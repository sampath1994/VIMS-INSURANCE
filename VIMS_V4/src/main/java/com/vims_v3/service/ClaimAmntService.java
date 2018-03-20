package com.vims_v3.service;

import com.vims_v3.model.Accident;
import com.vims_v3.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by promod on 11/18/2017.
 */
@Service
public class ClaimAmntService {

    @Autowired
    public AccidentRepository accidentRepository;

    public String acceptClaimAmount(Integer accidentId,String acceptance)
    {
        Accident accident = accidentRepository.findOne(accidentId);
        accident.setAmntAccept(acceptance);
        accidentRepository.save(accident);
        return "success";
    }

    public String checkClaimAmount(Integer accidentId)
    {
        Accident accident = accidentRepository.findOne(accidentId);

        if(accident.getDamageAmount() != null)
        {
            return accident.getDamageAmount();
        }else{
            return "No Claim Amount yet!!";
        }

    }
}
