package com.vims_v3.dto;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by promod on 10/10/2017.
 */

public class VehicleDTO {
    private String vehicleType;
    private int vehicleId;
    private String engineNo;
    private String licencePlate;
    private String registeredNo;
    private Date registeredDate;
    private String vehicleStatus;
    private String registeredArea;
    private int ownerId;
   /* private Collection<AccidentHistoryEntity> accidentHistoriesByVehicleId;*/
   /* private Collection<GarageHistoryEntity> garageHistoriesByVehicleId;*/
  /*  private InsuredPersonEntity insuredPersonByOwnerId;*/


    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }


    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }


    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }


    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }


    public String getRegisteredNo() {
        return registeredNo;
    }

    public void setRegisteredNo(String registeredNo) {
        this.registeredNo = registeredNo;
    }


    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }


    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }


    public String getRegisteredArea() {
        return registeredArea;
    }

    public void setRegisteredArea(String registeredArea) {
        this.registeredArea = registeredArea;
    }


    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleDTO that = (VehicleDTO) o;

        if (vehicleId != that.vehicleId) return false;
        if (ownerId != that.ownerId) return false;
        if (vehicleType != null ? !vehicleType.equals(that.vehicleType) : that.vehicleType != null) return false;
        if (engineNo != null ? !engineNo.equals(that.engineNo) : that.engineNo != null) return false;
        if (licencePlate != null ? !licencePlate.equals(that.licencePlate) : that.licencePlate != null) return false;
        if (registeredNo != null ? !registeredNo.equals(that.registeredNo) : that.registeredNo != null) return false;
        if (registeredDate != null ? !registeredDate.equals(that.registeredDate) : that.registeredDate != null)
            return false;
        if (vehicleStatus != null ? !vehicleStatus.equals(that.vehicleStatus) : that.vehicleStatus != null)
            return false;
        if (registeredArea != null ? !registeredArea.equals(that.registeredArea) : that.registeredArea != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vehicleType != null ? vehicleType.hashCode() : 0;
        result = 31 * result + vehicleId;
        result = 31 * result + (engineNo != null ? engineNo.hashCode() : 0);
        result = 31 * result + (licencePlate != null ? licencePlate.hashCode() : 0);
        result = 31 * result + (registeredNo != null ? registeredNo.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (vehicleStatus != null ? vehicleStatus.hashCode() : 0);
        result = 31 * result + (registeredArea != null ? registeredArea.hashCode() : 0);
        result = 31 * result + ownerId;
        return result;
    }


 /*   public Collection<AccidentHistoryEntity> getAccidentHistoriesByVehicleId() {
        return accidentHistoriesByVehicleId;
    }

    public void setAccidentHistoriesByVehicleId(Collection<AccidentHistoryEntity> accidentHistoriesByVehicleId) {
        this.accidentHistoriesByVehicleId = accidentHistoriesByVehicleId;
    }


    /*public Collection<GarageHistoryEntity> getGarageHistoriesByVehicleId() {
        return garageHistoriesByVehicleId;
    }

    public void setGarageHistoriesByVehicleId(Collection<GarageHistoryEntity> garageHistoriesByVehicleId) {
        this.garageHistoriesByVehicleId = garageHistoriesByVehicleId;
    }*/

  /*  public InsuredPersonEntity getInsuredPersonByOwnerId() {
        return insuredPersonByOwnerId;
    }

    public void setInsuredPersonByOwnerId(InsuredPersonEntity insuredPersonByOwnerId) {
        this.insuredPersonByOwnerId = insuredPersonByOwnerId;
    }*/
}
