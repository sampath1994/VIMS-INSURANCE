package com.vims_v3.repository;


import com.vims_v3.model.Accident;
import com.vims_v3.model.ClaimAssessingManager;
import com.vims_v3.model.InsuranceAdjuster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by promod on 10/11/2017.
 */
@Repository
public interface AccidentRepository extends CrudRepository<Accident,Integer> {
    @Query(value = "SELECT a FROM Accident a WHERE a.insuranceAjusterId = ?1 and a.coverage = true")
    public Accident findAccidentByAdjuster(InsuranceAdjuster insuranceAjusterId);

    @Query(value = "SELECT a FROM Accident a WHERE a.claimingManagerId = ?1")
    public List<Accident> findAccidentByClaimManager(ClaimAssessingManager claimingManage rId);

    @Query(value = "SELECT a FROM Accident a WHERE a.claimingManagerId = ?1 and a.paid = null")
    public List<Accident> findAccidentByClaimManagerAndNotPaid(ClaimAssessingManager claimingManagerId);
}



//Important==== Can't save to Accident unless "coverage" boolean value is set to something....