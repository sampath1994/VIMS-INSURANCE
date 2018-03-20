package com.vims_v3.dto;

import java.io.Serializable;

/**
 * Created by promod on 11/20/2017.
 */
public class AccidentInfoDTO implements Serializable{
   private Integer accidentId;
   private String damageAmnt;
   private String accidentArea;
   private String description;

   public AccidentInfoDTO()
    {

    }

    public AccidentInfoDTO(Integer accidentId,String damageAmnt, String accidentArea, String description) {
       this.accidentId = accidentId;
       this.damageAmnt = damageAmnt;
        this.accidentArea = accidentArea;
        this.description = description;
    }

    public Integer getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
    }

    public String getDamageAmnt() {
        return damageAmnt;
    }

    public void setDamageAmnt(String damageAmnt) {
        this.damageAmnt = damageAmnt;
    }

    public String getAccidentArea() {
        return accidentArea;
    }

    public void setAccidentArea(String accidentArea) {
        this.accidentArea = accidentArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
