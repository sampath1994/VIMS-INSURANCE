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
@Table(name = "insured_person")
/*@NamedQueries({
    @NamedQuery(name = "InsuredPerson.findAll", query = "SELECT i FROM InsuredPerson i")})*/
public class InsuredPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ins_id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "insured_person_id")
    private String insuredPersonId;
    @Column(name = "insured_date")
    @Temporal(TemporalType.DATE)
    private Date insuredDate;
    @Basic(optional = false)

   /* @Column(name = "emergency_v_owner_id")
    private String emergencyVOwnerId;*/


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private List<Vehicle> vehicleList;

  /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "insuredPerson")
    private List<ContactHistory> contactHistoryList;*/


    @JoinTable(name = "contact_history", joinColumns = {
            @JoinColumn(name = "insured_id", referencedColumnName = "ins_id")}, inverseJoinColumns = {
            @JoinColumn(name = "driver_id", referencedColumnName = "dri_id")})
    @ManyToMany
    private List<EmergencyVehicleDriver> driverList;



    @JoinColumn(name = "ins_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public InsuredPerson() {
    }

    public InsuredPerson(Integer id) {
        this.id = id;
    }

    public InsuredPerson(Integer id, String insuredPersonId, String emergencyVOwnerId) {
        this.id = id;
        this.insuredPersonId = insuredPersonId;
       /* this.emergencyVOwnerId = emergencyVOwnerId;*/
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInsuredPersonId() {
        return insuredPersonId;
    }

    public void setInsuredPersonId(String insuredPersonId) {
        this.insuredPersonId = insuredPersonId;
    }

    public Date getInsuredDate() {
        return insuredDate;
    }

    public void setInsuredDate(Date insuredDate) {
        this.insuredDate = insuredDate;
    }

   /* public String getEmergencyVOwnerId() {
        return emergencyVOwnerId;
    }*/

   /* public void setEmergencyVOwnerId(String emergencyVOwnerId) {
        this.emergencyVOwnerId = emergencyVOwnerId;
    }*/

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

   /* public List<ContactHistory> getContactHistoryList() {
        return contactHistoryList;
    }

    public void setContactHistoryList(List<ContactHistory> contactHistoryList) {
        this.contactHistoryList = contactHistoryList;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<EmergencyVehicleDriver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<EmergencyVehicleDriver> driverList) {
        this.driverList = driverList;
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
        if (!(object instanceof InsuredPerson)) {
            return false;
        }
        InsuredPerson other = (InsuredPerson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.InsuredPerson[ id=" + id + " ]";
    }
    
}
