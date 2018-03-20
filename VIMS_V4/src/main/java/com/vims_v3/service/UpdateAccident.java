package com.vims_v3.service;

import com.vims_v3.dto.AccidentInfoDTO;
import com.vims_v3.model.Accident;
import com.vims_v3.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by promod on 11/20/2017.
 */
@Service
public class UpdateAccident {

    @Autowired
    public AccidentRepository accidentRepository;

    public String updateAccidentInfo(AccidentInfoDTO accidentInfoDTO)
    {
       Accident accident = accidentRepository.findOne(accidentInfoDTO.getAccidentId());

       accident.setDamageAmount(accidentInfoDTO.getDamageAmnt());
       accident.setAccidentArea(accidentInfoDTO.getAccidentArea());
       accident.setDescription(accidentInfoDTO.getDescription());

       accidentRepository.save(accident);

       return "success";
    }
}
