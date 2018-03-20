/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vims_v3.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Udeesha
 */
@Entity
@Table(name = "payment")
/*@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")})*/
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bill_id")
    private Integer billId;
    @Column(name = "payment_amount")
    private Integer paymentamount;
    @Column(name = "acc_id")
    private Integer AccidentId;
    @Column(name ="partcost")
    private String partcost;
    @Column(name ="labour")
    private String labour;
    @Column(name ="comment")
    private String comment;

    @JoinColumn(name = "emergency_v_id", referencedColumnName = "dri_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private EmergencyVehicleDriver emergencyvId;


    @JoinColumn(name = "garage_id", referencedColumnName = "garage_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Garage garageId;

    public Payment() {
    }

    public Payment(Integer billId) {
        this.billId = billId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getPaymentamount() {
        return paymentamount;
    }

    public void setPaymentamount(Integer paymentamount) {
        this.paymentamount = paymentamount;
    }

    public EmergencyVehicleDriver getEmergencyvId() {
        return emergencyvId;
    }

    public void setEmergencyvId(EmergencyVehicleDriver emergencyvId) {
        this.emergencyvId = emergencyvId;
    }

    public Garage getGarageId() {
        return garageId;
    }

    public void setGarageId(Garage garageId) {
        this.garageId = garageId;
    }

    public Integer getAccidentId() {
        return AccidentId;
    }

    public void setAccidentId(Integer accidentId) {
        AccidentId = accidentId;
    }

    public String getPartcost() {
        return partcost;
    }

    public void setPartcost(String partcost) {
        this.partcost = partcost;
    }

    public String getLabour() {
        return labour;
    }

    public void setLabour(String labour) {
        this.labour = labour;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billId != null ? billId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Payment[ billId=" + billId + " ]";
    }
    
}
