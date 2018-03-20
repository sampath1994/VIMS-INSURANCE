package com.vims_v3.service;

import com.vims_v3.model.Garage;
import com.vims_v3.repository.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by promod on 1/27/2018.
 */
@Service
public class Rate {
    @Autowired
    GarageRepository garageRepository;
    public String RateGarage(Integer gId,Integer star)
    {
       Garage gar = garageRepository.findOne(gId);
       if(gar.getStar() == null)
       {
           gar.setStar(star.doubleValue());
           gar.setCount(1);
       }else{
           double newStar = (gar.getStar()*gar.getCount() + star)/(gar.getCount()+1);
           gar.setCount(gar.getCount()+1);
           gar.setStar(newStar);
       }

       garageRepository.save(gar);
       return "success";
    }
}
