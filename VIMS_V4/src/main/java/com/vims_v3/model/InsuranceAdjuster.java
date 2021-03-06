/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "insurance_adjuster")
/*@NamedQueries({
    @NamedQuery(name = "InsuranceAdjuster.findAll", query = "SELECT i FROM InsuranceAdjuster i")})*/
public class InsuranceAdjuster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "adjuster_id")
    private String adjusterId;
    @Column(name = "working_hours")
    private String workingHours;
   /* @Lob
    @Column(name = "adjuster_location")
    private Object adjusterLocation;*/
    @Id
    @Basic(optional = false)
    @Column(name = "adj_id")
    private Integer id;

    @Column(name = "adj_latitude")
    private String latitude;
    @Column(name = "adj_longtitude")
    private String longtitude;

    @Column(name = "availability")
    private Integer availability;


    @JoinColumn(name = "adj_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    @JsonIgnoreProperties("insuranceAdjuster")
    private User user;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "insuranceAjusterId")
    private List<Accident> accidentList;

    public InsuranceAdjuster() {
    }

    public InsuranceAdjuster(Integer id) {
        this.id = id;
    }

    public String getAdjusterId() {
        return adjusterId;
    }

    public void setAdjusterId(String adjusterId) {
        this.adjusterId = adjusterId;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    /*public Object getAdjusterLocation() {
        return adjusterLocation;
    }*/

  /*  public void setAdjusterLocation(Object adjusterLocation) {
        this.adjusterLocation = adjusterLocation;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Accident> getAccidentList() {
        return accidentList;
    }

    public void setAccidentList(List<Accident> accidentList) {
        this.accidentList = accidentList;
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

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
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
        if (!(object instanceof InsuranceAdjuster)) {
            return false;
        }
        InsuranceAdjuster other = (InsuranceAdjuster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.InsuranceAdjuster[ id=" + id + " ]";
    }
    
}
