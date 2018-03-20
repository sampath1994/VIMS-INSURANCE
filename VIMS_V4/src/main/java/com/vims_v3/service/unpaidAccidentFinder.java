package com.vims_v3.service;

import com.vims_v3.model.Accident;
import com.vims_v3.model.ClaimAssessingManager;
import com.vims_v3.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by promod on 11/29/2017.
 */
@Service
public class unpaidAccidentFinder {
    @Autowired
    public AccidentRepository accidentRepository;

    public List<Accident> checkAccidents(ClaimAssessingManager mng) {
       /* ArrayList<Accident> accidentList = new ArrayList<>();

       Iterable itr = accidentRepository.findAll();
        Iterator it = itr.iterator();

        Accident accident;
        while(it.hasNext())
        {
            accident = (Accident) it.next();
            if(accident.getPaid() == null && accident.getClaimingManagerId().getId().equals(mngId))   //accident.getPaid() == null && accident.getClaimingmanager() != null
            {
                accidentList.add(accident);
            }
        }*/
      List<Accident> accidentList;
     accidentList = accidentRepository.findAccidentByClaimManagerAndNotPaid(mng);
        return accidentList;
    }
}
