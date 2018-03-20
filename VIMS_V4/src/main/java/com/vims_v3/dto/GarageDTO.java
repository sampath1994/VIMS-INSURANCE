package com.vims_v3.dto;

import java.io.Serializable;

/**
 * Created by promod on 11/15/2017.
 */
public class GarageDTO implements Serializable {

    private Integer garageId;
    private String garageOwner;
    private String telephone;
    private String registeredNo;
    private String address;
    private String latitude;
    private String longtitude;
    private Double distance;

    public GarageDTO()
    {

    }

    public GarageDTO(Integer id,String lat,String longt)
    {
        this.garageId = id;
        this.latitude = lat;
        this.longtitude = longt;
    }
    public Integer getGarageId() {
        return garageId;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GarageDTO garageDTO = (GarageDTO) o;

        return garageId != null ? garageId.equals(garageDTO.garageId) : garageDTO.garageId == null;
    }

    @Override
    public int hashCode() {
        return garageId != null ? garageId.hashCode() : 0;
    }
}
