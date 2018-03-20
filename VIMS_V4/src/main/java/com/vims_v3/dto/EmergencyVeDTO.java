package com.vims_v3.dto;

import java.io.Serializable;

/**
 * Created by promod on 11/18/2017.
 */
public class EmergencyVeDTO implements Serializable {
    private Integer insuredPersonId;
    private String lati;
    private  String longt;
    private int availability;

    public EmergencyVeDTO()
    {

    }
    public Integer getInsuredPersonId() {
        return insuredPersonId;
    }

    public String getLati() {
        return lati;
    }

    public String getLongt() {
        return longt;
    }

    public void setInsuredPersonId(Integer insuredPersonId) {
        this.insuredPersonId = insuredPersonId;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public void setLongt(String longt) {
        this.longt = longt;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public EmergencyVeDTO(Integer insuredPersonId, String lati, String longt) {
        this.insuredPersonId = insuredPersonId;
        this.lati = lati;
        this.longt = longt;
    }
}
