package com.vims_v3.service;

import com.vims_v3.model.Accident;
import com.vims_v3.repository.AccidentRepository;
import groovy.transform.AutoClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by promod on 1/26/2018.
 */
@Service
public class coverageService {
    @Autowired
    AccidentRepository accidentRepository;

    public String covering(Integer accId,Integer cov)
    {
        Accident acc = accidentRepository.findOne(accId);
        if(cov == 1) {
            acc.setCoverage(false);
            accidentRepository.save(acc);
            return "success";
        }else{
            return "unsuccess";
        }
    }
}
