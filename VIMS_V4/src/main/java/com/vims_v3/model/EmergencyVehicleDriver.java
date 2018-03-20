/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "emergency_vehicle_driver")
public class EmergencyVehicleDriver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "licence_plate")
    private String licencePlate;
    @Column(name = "registered_no")
    private String registeredNo;
    @Id
    @Basic(optional = false)
    @Column(name = "dri_id")
    private Integer id;
    @Column(name = "e_vehicle_owner_id")
    private String eVehicleOwnerId;
    /*@Lob
    @Column(name = "e_vehicle_location")
    private Object evehicleLocation;*/
    @Column(name ="ev_latitude")
    private String latitude;
    @Column(name = "ev_longtitude")
    private String longtitude;
    @Column(name = "availability")
    private int availability;
    @Column(name = "status")
    private int status;
    @Column(name = "acc_id")
    private int accId;




    @OneToMany(mappedBy = "emergencyvId")
    private List<Payment> paymentList;


    @JoinColumn(name = "dri_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;


    @ManyToMany(mappedBy = "driverList")
    private List<InsuredPerson> insuredPeople;

    public List<InsuredPerson> getInsuredPeople() {
        return insuredPeople;
    }

    public void setInsuredPeople(List<InsuredPerson> insuredPeople) {
        this.insuredPeople = insuredPeople;
    }

    public EmergencyVehicleDriver() {
    }

    public EmergencyVehicleDriver(Integer id) {
        this.id = id;
    }

    public EmergencyVehicleDriver(Integer id, String licencePlate) {
        this.id = id;
        this.licencePlate = licencePlate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEVehicleOwnerId() {
        return eVehicleOwnerId;
    }

    public void setEVehicleOwnerId(String eVehicleOwnerId) {
        this.eVehicleOwnerId = eVehicleOwnerId;
    }

   /* public Object getEvehicleLocation() {
        return evehicleLocation;
    }

    public void setEvehicleLocation(Object evehicleLocation) {
        this.evehicleLocation = evehicleLocation;
    }*/

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmergencyVehicleDriver)) {
            return false;
        }
        EmergencyVehicleDriver other = (EmergencyVehicleDriver) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.EmergencyVehicleDriver[ id=" + id + " ]";
    }
    
}
