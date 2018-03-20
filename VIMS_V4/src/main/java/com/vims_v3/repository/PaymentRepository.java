package com.vims_v3.repository;

import com.vims_v3.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by promod on 11/29/2017.
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment,Integer> {
    @Query(value = "SELECT p FROM Payment p WHERE p.AccidentId =?1")
    public List<Payment> findBillsByAccident(Integer AccidentId);
}
