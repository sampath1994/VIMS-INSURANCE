package com.vims_v3.dto;


/**
 * Created by promod on 10/10/2017.
 */

public class AccidentHistoryDTO {
    private int accidentId;
    private int vehicleId;


    public int getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(int accidentId) {
        this.accidentId = accidentId;
    }


    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccidentHistoryDTO that = (AccidentHistoryDTO) o;

        if (accidentId != that.accidentId) return false;
        if (vehicleId != that.vehicleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accidentId;
        result = 31 * result + vehicleId;
        return result;
    }



}
