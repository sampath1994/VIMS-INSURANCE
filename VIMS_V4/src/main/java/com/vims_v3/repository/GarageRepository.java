package com.vims_v3.repository;

import com.vims_v3.model.Garage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by promod on 11/12/2017.
 */
@Repository
public interface GarageRepository extends CrudRepository<Garage,Integer>{
}
