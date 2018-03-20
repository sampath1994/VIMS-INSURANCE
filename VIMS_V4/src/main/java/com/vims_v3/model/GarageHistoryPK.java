/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Udeesha
 */
@Embeddable
public class GarageHistoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "garage_id")
    private int garageId;
    @Basic(optional = false)
    @Column(name = "vehicle_id")
    private int vehicleId;

    public GarageHistoryPK() {
    }

    public GarageHistoryPK(int garageId, int vehicleId) {
        this.garageId = garageId;
        this.vehicleId = vehicleId;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) garageId;
        hash += (int) vehicleId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GarageHistoryPK)) {
            return false;
        }
        GarageHistoryPK other = (GarageHistoryPK) object;
        if (this.garageId != other.garageId) {
            return false;
        }
        if (this.vehicleId != other.vehicleId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.GarageHistoryPK[ garageId=" + garageId + ", vehicleId=" + vehicleId + " ]";
    }
    
}
