package com.vims_v3.service;

import com.vims_v3.model.Accident;
import com.vims_v3.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

/**
 * Created by promod on 10/24/2017.
 */
@Service
public class uncheckedAccidentFinder {

    @Autowired
    public AccidentRepository accidentRepository;

    public List<Accident> checkAccidents() {
        ArrayList<Accident> accidentList = new ArrayList<>();

        Iterable itr = accidentRepository.findAll();
        Iterator it = itr.iterator();

        Accident accident;
        while(it.hasNext())
        {
            accident = (Accident) it.next();
          if(accident.getClaimingManagerId() == null)
          {
              accidentList.add(accident);
          }
        }

        Collections.sort(accidentList, new Comparator<Accident>(){
            public int compare(Accident o1, Accident o2){
                if(o1.getAccidentId() == o2.getAccidentId())
                    return 0;
                return o1.getAccidentId() < o2.getAccidentId() ? -1 : 1;
            }
        });
        return accidentList;
    }
}
