/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "accident")
public class Accident implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accident_id")
    private Integer accidentId;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
   /* @Lob
    @Column(name = "Accident_GPS_Location")
    private Object accidentGPSLocation;*/
    @Column(name = "damage_amount")
    private String damageAmount;
    @Column(name = "accident_area")
    private String accidentArea;
    @Column(name = "report_no")
    private String reportNo;
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longtitude")
    private String longtitude;
    @Column(name = "coverage")
    private boolean coverage;
    @Column(name ="amnt_accept")
    private String amntAccept;
    @Column(name = "description")
    private String description;
    @Column(name = "paid")
    private String paid;
    @Column(name="datetime")
    private String datetime;


    @JoinTable(name = "accident_history", joinColumns = {
        @JoinColumn(name = "accident_id", referencedColumnName = "accident_id")}, inverseJoinColumns = {
        @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Vehicle> vehicleList;


    @JoinColumn(name = "claiming_manager_id", referencedColumnName = "mng_id")
    @ManyToOne
    private ClaimAssessingManager claimingManagerId;


    @JoinColumn(name = "insurance_adjuster_id", referencedColumnName = "adj_id")
    @ManyToOne
    private InsuranceAdjuster insuranceAjusterId;



    public Accident() {
    }

    public Accident(Integer accidentId) {
        this.accidentId = accidentId;
    }

    public Integer getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

  /*  public Object getAccidentGPSLocation() {
        return accidentGPSLocation;
    }

    public void setAccidentGPSLocation(Object accidentGPSLocation) {
        this.accidentGPSLocation = accidentGPSLocation;
    } */

    public String getDamageAmount() {
        return damageAmount;
    }

    public void setDamageAmount(String damageAmount) {
        this.damageAmount = damageAmount;
    }

    public String getAccidentArea() {
        return accidentArea;
    }

    public void setAccidentArea(String accidentArea) {
        this.accidentArea = accidentArea;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public ClaimAssessingManager getClaimingManagerId() {
        return claimingManagerId;
    }

    public void setClaimingManagerId(ClaimAssessingManager claimingManagerId) {
        this.claimingManagerId = claimingManagerId;
    }

    public InsuranceAdjuster getInsuranceAjusterId() {
        return insuranceAjusterId;
    }

    public void setInsuranceAjusterId(InsuranceAdjuster insuranceAjusterId) {
        this.insuranceAjusterId = insuranceAjusterId;
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

    public boolean isCoverage() {
        return coverage;
    }

    public void setCoverage(boolean coverage) {
        this.coverage = coverage;
    }

    public String getAmntAccept() {
        return amntAccept;
    }

    public void setAmntAccept(String amntAccept) {
        this.amntAccept = amntAccept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accidentId != null ? accidentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accident)) {
            return false;
        }
        Accident other = (Accident) object;
        if ((this.accidentId == null && other.accidentId != null) || (this.accidentId != null && !this.accidentId.equals(other.accidentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Accident[ accidentId=" + accidentId + " ]";
    }
    
}
