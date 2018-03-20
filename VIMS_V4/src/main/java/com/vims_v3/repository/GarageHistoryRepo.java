package com.vims_v3.repository;

import com.vims_v3.model.Garage;
import com.vims_v3.model.GarageHistory;
import com.vims_v3.model.GarageHistoryPK;
import com.vims_v3.model.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by promod on 11/18/2017.
 */
@Repository
public interface GarageHistoryRepo extends CrudRepository<GarageHistory,GarageHistoryPK>{
    @Query(value = "SELECT g FROM GarageHistory g WHERE g.garage = ?1 and g.checked = null")
    public List<GarageHistory> findVehiclesByGarage(Garage garage);

    @Query(value = "SELECT g FROM GarageHistory g WHERE g.garage = ?1 and g.vehicle = ?2")
    public GarageHistory findHisByVehicleAndGarage(Garage garage, Vehicle vehicle);
}
