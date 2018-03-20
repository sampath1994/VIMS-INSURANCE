/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "garage_history")
@NamedQueries({
    @NamedQuery(name = "GarageHistory.findAll", query = "SELECT g FROM GarageHistory g")})
public class GarageHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GarageHistoryPK garageHistoryPK;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Column(name = "bill_id")
    private String billId;
    @Column(name = "checked")
    private String checked;

    @JoinColumn(name = "garage_id", referencedColumnName = "garage_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Garage garage;


    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vehicle vehicle;

    public GarageHistory() {
    }

    public GarageHistory(GarageHistoryPK garageHistoryPK) {
        this.garageHistoryPK = garageHistoryPK;
    }

    public GarageHistory(int garageId, int vehicleId) {
        this.garageHistoryPK = new GarageHistoryPK(garageId, vehicleId);
    }

    public GarageHistoryPK getGarageHistoryPK() {
        return garageHistoryPK;
    }

    public void setGarageHistoryPK(GarageHistoryPK garageHistoryPK) {
        this.garageHistoryPK = garageHistoryPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (garageHistoryPK != null ? garageHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GarageHistory)) {
            return false;
        }
        GarageHistory other = (GarageHistory) object;
        if ((this.garageHistoryPK == null && other.garageHistoryPK != null) || (this.garageHistoryPK != null && !this.garageHistoryPK.equals(other.garageHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.GarageHistory[ garageHistoryPK=" + garageHistoryPK + " ]";
    }
    
}
