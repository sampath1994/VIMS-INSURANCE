package com.vims_v3.repository;

import com.vims_v3.model.InsuranceAdjuster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by promod on 11/1/2017.
 */
@Repository
public interface InsuranceAdjusterRepository extends CrudRepository<InsuranceAdjuster,Integer>{
}
