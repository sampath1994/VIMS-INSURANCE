package com.vims_v3.service;

import com.vims_v3.dto.GarageDTO;
import com.vims_v3.model.Garage;
import com.vims_v3.repository.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by promod on 11/12/2017.
 */
@Service
public class ClosestGarage {

    @Autowired
    public GarageRepository garageRepository;


public /*SortedSet<Map.Entry<GarageDTO,Double>>*/List<GarageDTO> findGarages(String latitude,String longtitude)
 {

     Iterable itr = garageRepository.findAll();
     Iterator it = itr.iterator();


     Map<GarageDTO,Double> distanceMap = new TreeMap<GarageDTO,Double>(new GarageComparator());
     Double distance;

     Garage garage;
     GarageDTO garageDTO;
     while(it.hasNext())
     {
         garage = (Garage) it.next();
         garageDTO = new GarageDTO(garage.getGarageId(),garage.getLatitude(),garage.getLongtitude());

         garageDTO.setGarageOwner(garage.getGarageOwner());
         garageDTO.setTelephone(garage.getTelephone());

         distance = haversine.findDistance(Double.parseDouble(latitude),Double.parseDouble(longtitude),Double.parseDouble(garage.getLatitude()),Double.parseDouble(garage.getLongtitude()));
         distanceMap.put(garageDTO,distance);
     }

     List<GarageDTO> garageSortedList = new ArrayList<>();
     //return  sortByValue.entriesSortedByValues(distanceMap).first().getKey().getGarageId();

    SortedSet<Map.Entry<GarageDTO,Double>> buff = sortByValue.entriesSortedByValues(distanceMap);
                Iterator<Map.Entry<GarageDTO,Double>> itra= buff.iterator();
    while(itra.hasNext())
     {

         garageDTO = new GarageDTO();
         Map.Entry<GarageDTO,Double> foo =itra.next();
         garageDTO.setGarageOwner(foo.getKey().getGarageOwner());  ////********/
        garageDTO.setTelephone(foo.getKey().getTelephone());
        garageDTO.setGarageId(foo.getKey().getGarageId());
        garageDTO.setLatitude(foo.getKey().getLatitude());
        garageDTO.setLongtitude(foo.getKey().getLongtitude());
        garageDTO.setDistance(foo.getValue());
        garageSortedList.add(garageDTO);

     }
       return garageSortedList;
 }
}
