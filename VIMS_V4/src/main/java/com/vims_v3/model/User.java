/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "users", schema ="public")
/*@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})*/
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Id
   @Column(name = "user_id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "national_id_no")
    private String nationalIdNo;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Column(name = "role")
    private String role;
    @Column(name ="enabled")
    private Integer enabled;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;


    @ManyToMany(mappedBy = "userList")
    private List<Role> roleList;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private InsuranceAdjuster insuranceAdjuster;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private EmergencyVehicleDriver emergencyVehicleDriver;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private ClaimAssessingManager claimAssessingManager;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private InsuredPerson insuredPerson;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String userPassword) {
        this.id = id;
        this.userPassword = userPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNationalIdNo() {
        return nationalIdNo;
    }

    public void setNationalIdNo(String nationalIdNo) {
        this.nationalIdNo = nationalIdNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public InsuranceAdjuster getInsuranceAdjuster() {
        return insuranceAdjuster;
    }

    public void setInsuranceAdjuster(InsuranceAdjuster insuranceAdjuster) {
        this.insuranceAdjuster = insuranceAdjuster;
    }

    public EmergencyVehicleDriver getEmergencyVehicleDriver() {
        return emergencyVehicleDriver;
    }

    public void setEmergencyVehicleDriver(EmergencyVehicleDriver emergencyVehicleDriver) {
        this.emergencyVehicleDriver = emergencyVehicleDriver;
    }

    public ClaimAssessingManager getClaimAssessingManager() {
        return claimAssessingManager;
    }

    public void setClaimAssessingManager(ClaimAssessingManager claimAssessingManager) {
        this.claimAssessingManager = claimAssessingManager;
    }

    public InsuredPerson getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(InsuredPerson insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.User[ id=" + id + " ]";
    }
    
}
