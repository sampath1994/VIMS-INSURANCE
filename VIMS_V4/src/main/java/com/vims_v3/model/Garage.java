/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "garage")
@NamedQueries({
    @NamedQuery(name = "Garage.findAll", query = "SELECT g FROM Garage g")})
public class Garage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)  //updated using ID of User table...so needn't it...
    @Basic(optional = false)
    @Column(name = "garage_id")
    private Integer garageId;
  /*  @Lob
    @Column(name = "garage_gps_location")
    private Object garageGPSLocation;*/
    @Column(name = "garage_owner")
    private String garageOwner;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "registered_no")
    private String registeredNo;
    @Column(name = "address")
    private String address;
    @Column(name = "g_latitude")
    private String latitude;
    @Column(name = "g_longtitude")
    private String longtitude;
    @Column(name = "count")
    private Integer count;
    @Column(name = "star")
    private Double star;

    @OneToMany(mappedBy = "garageId")
    private List<Payment> paymentList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "garage")
    private List<GarageHistory> garageHistoryList;

    public Garage() {
    }

    public Garage(Integer garageId) {
        this.garageId = garageId;
    }

    public Integer getGarageId() {
        return garageId;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

    /*public Object getGarageGPSLocation() {
        return garageGPSLocation;
    }

    public void setGarageGPSLocation(Object garageGPSLocation) {
        this.garageGPSLocation = garageGPSLocation;
    }*/

    public String getGarageOwner() {
        return garageOwner;
    }

    public void setGarageOwner(String garageOwner) {
        this.garageOwner = garageOwner;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegisteredNo() {
        return registeredNo;
    }

    public void setRegisteredNo(String registeredNo) {
        this.registeredNo = registeredNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<GarageHistory> getGarageHistoryList() {
        return garageHistoryList;
    }

    public void setGarageHistoryList(List<GarageHistory> garageHistoryList) {
        this.garageHistoryList = garageHistoryList;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (garageId != null ? garageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garage)) {
            return false;
        }
        Garage other = (Garage) object;
        if ((this.garageId == null && other.garageId != null) || (this.garageId != null && !this.garageId.equals(other.garageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Garage[ garageId=" + garageId + " ]";
    }


}
