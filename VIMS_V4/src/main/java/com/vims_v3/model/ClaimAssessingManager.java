/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "claim_assessing_manager")
public class ClaimAssessingManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "manager_id")
    private String managerId;
    @Id
    @Basic(optional = false)
    @Column(name = "mng_id")
    private Integer id;
    @Column(name = "claim_report")
    private String claimreport;


    @OneToMany(mappedBy = "claimingManagerId")
    private List<Accident> accidentList;


    @JoinColumn(name = "mng_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public ClaimAssessingManager() {
    }

    public ClaimAssessingManager(Integer id) {
        this.id = id;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaimreport() {
        return claimreport;
    }

    public void setClaimreport(String claimreport) {
        this.claimreport = claimreport;
    }

    public List<Accident> getAccidentList() {
        return accidentList;
    }

    public void setAccidentList(List<Accident> accidentList) {
        this.accidentList = accidentList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof ClaimAssessingManager)) {
            return false;
        }
        ClaimAssessingManager other = (ClaimAssessingManager) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.ClaimAssessingManager[ id=" + id + " ]";
    }
    
}
