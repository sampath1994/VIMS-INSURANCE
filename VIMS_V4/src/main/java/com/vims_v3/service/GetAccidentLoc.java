package com.vims_v3.service;

import com.vims_v3.dto.AccidentDTO;
import com.vims_v3.model.Accident;
import com.vims_v3.model.InsuranceAdjuster;
import com.vims_v3.repository.AccidentRepository;
import com.vims_v3.repository.InsuranceAdjusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by promod on 11/18/2017.
 */
@Service
public class GetAccidentLoc {

    @Autowired
    public AccidentRepository accidentRepository;

    @Autowired
    public InsuranceAdjusterRepository insuranceAdjusterRepository;

    public AccidentDTO getDirectionAccLoc(Integer adjId)
    {
        InsuranceAdjuster insuranceAdjuster = insuranceAdjusterRepository.findOne(adjId);

        Accident accident = accidentRepository.findAccidentByAdjuster(insuranceAdjuster);
       if(accident != null) {
           AccidentDTO accidentDTO = new AccidentDTO(accident.getAccidentId(), accident.getLatitude(), accident.getLongtitude());
           return accidentDTO;
       }else
       {
           return  new AccidentDTO(0,"NO","No");
       }
       }
}
