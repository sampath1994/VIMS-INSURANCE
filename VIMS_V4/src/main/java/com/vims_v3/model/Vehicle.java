/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "vehicle")
/*@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v")})*/
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "vehicle_type")
    private String vehicleType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    @Column(name = "engine_no")
    private String engineNo;
    @Column(name = "licence_plate")
    private String licencePlate;
    @Column(name = "registered_no")
    private String registeredNo;
    @Column(name = "registered_date")
    @Temporal(TemporalType.DATE)
    private Date registereddate;
    @Column(name = "vehicle_status")
    private String vehicleStatus;
    @Column(name = "registered_area")
    private String registeredArea;

    @ManyToMany(mappedBy = "vehicleList",fetch =FetchType.EAGER)
    private List<Accident> accidentList;


    @JoinColumn(name = "owner_id", referencedColumnName = "ins_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private InsuredPerson ownerId;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<GarageHistory> garageHistoryList;

    public Vehicle() {
    }

    public Vehicle(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
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

    public Date getRegistereddate() {
        return registereddate;
    }

    public void setRegistereddate(Date registereddate) {
        this.registereddate = registereddate;
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

    public List<Accident> getAccidentList() {
        return accidentList;
    }

    public void setAccidentList(List<Accident> accidentList) {
        this.accidentList = accidentList;
    }

    public InsuredPerson getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(InsuredPerson ownerId) {
        this.ownerId = ownerId;
    }

    public List<GarageHistory> getGarageHistoryList() {
        return garageHistoryList;
    }

    public void setGarageHistoryList(List<GarageHistory> garageHistoryList) {
        this.garageHistoryList = garageHistoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehicleId != null ? vehicleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.vehicleId == null && other.vehicleId != null) || (this.vehicleId != null && !this.vehicleId.equals(other.vehicleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Vehicle[ vehicleId=" + vehicleId + " ]";
    }
    
}
