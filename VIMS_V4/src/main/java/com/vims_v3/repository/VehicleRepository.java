package com.vims_v3.repository;

import com.vims_v3.model.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends CrudRepository<Vehicle,Integer> {
    @Query(value = "SELECT s FROM Vehicle s WHERE s.licencePlate =?1")
    public Vehicle findByLicense(String licencePlate);
}
