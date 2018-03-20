package com.vims_v3.controller;

import com.vims_v3.dto.AccidentDTO;
import com.vims_v3.dto.AccidentInfoDTO;
import com.vims_v3.dto.EmergencyVeDTO;
import com.vims_v3.dto.GarageDTO;
import com.vims_v3.model.Accident;
import com.vims_v3.model.Garage;
import com.vims_v3.model.InsuranceAdjuster;
import com.vims_v3.model.User;
import com.vims_v3.repository.AccidentRepository;
import com.vims_v3.repository.GarageRepository;
import com.vims_v3.repository.UsersRepo;
import com.vims_v3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;
                                                          //Insured Person use this.
    @RequestMapping("/report/{license}/{lat}/{longt}")
    public Integer reportAccident(@PathVariable String license,@PathVariable String lat,@PathVariable String longt){

        return reportService.saveAccident(license,lat,longt);
    }


    @Autowired
    private ClosestAdjuster closestAdjuster;
                                                         //Assesing Manager use this.
  /*  @RequestMapping("/getAdjuster/{lat}/{longt}/{accId}")
    public InsuranceAdjuster findClosestAdjuster(@PathVariable String lat, @PathVariable String longt,@PathVariable int accId)
    {
        return closestAdjuster.findClosestAdjuster(lat,longt,accId);
    }*/


    @Autowired
    private AdjusterUpLoc adjusterUpLoc;
                                                         //Insurance Adjuster use this
    @RequestMapping("/adjusterUpLoc/{adjId}/{lat}/{longt}/{availability}")
    public void UpdateAdjusterLoc(@PathVariable Integer adjId,@PathVariable String lat, @PathVariable String longt,@PathVariable Integer availability)
    {
        adjusterUpLoc.adjusterUpLoc(adjId,lat,longt,availability);
    }


    @Autowired
    private ClosestGarage closestGarage;
                                                      //Insured Person use this.
    @RequestMapping(value = "/getGarage/{lat}/{longt}/end",produces = MediaType.APPLICATION_JSON_VALUE)
    public /*SortedSet<Map.Entry<GarageDTO,Double>>*/List<GarageDTO> getGarageList(@PathVariable String lat, @PathVariable String longt)
    {
        return closestGarage.findGarages(lat,longt);
    }

    @Autowired
    private SentToGarage sentToGarage;              //Insured Person use this

    @RequestMapping("/SentToGarage/{GarageId}/{licence}")
    public String setVehicleToGrage(@PathVariable Integer GarageId,@PathVariable String licence)
    {
        return sentToGarage.setVehicleToGarage(GarageId,licence);
    }

    @Autowired
    private GetAccidentLoc getAccidentLoc;

    @RequestMapping("/getDirection/{adjId}")                      // Insurance Adjuster use this
    public AccidentDTO getDirectionToAccident(@PathVariable Integer adjId)
    {
        return getAccidentLoc.getDirectionAccLoc(adjId);
    }

    @Autowired
    private GetEmerVehicle getEmerVehicle;
                                                                                //Insured Person use this
   /* @RequestMapping(value = "/getEmergencyVehicle", method = RequestMethod.POST)
    public String getEmergencyVehicle(@RequestBody EmergencyVeDTO emergencyVeDTO)
    {
       return getEmerVehicle.findClosestEmerVehicle(emergencyVeDTO);
    }*/

  /*  @RequestMapping("/getEmergencyVehicle/{accId}/{lat}/{longt}/end")
    public String getEmergencyVehicle(@PathVariable Integer accId,@PathVariable String lat,@PathVariable String longt)
    {
        EmergencyVeDTO emergencyVeDTO = new EmergencyVeDTO(accId,lat,longt);
        return getEmerVehicle.findClosestEmerVehicle(emergencyVeDTO);
    }*/
    @Autowired
    AddEmergencyVehicle addEmergencyVehicle;

    @RequestMapping("/getEmergencyVehicle/{accId}/{lati}/{longt}/end")
    public String getEmergencyVehicle(@PathVariable int accId,@PathVariable String lati,@PathVariable String longt){

        return addEmergencyVehicle.findClosestEmv(accId,lati,longt);
    }

    @Autowired
    private ClaimAmntService claimAmntService;

    @RequestMapping("/claimAccept/{accId}/{acceptance}")                             //Insured Person use this
    public String SetAcceptance(@PathVariable Integer accId,@PathVariable String acceptance)
    {
        return claimAmntService.acceptClaimAmount(accId,acceptance);
    }

    @RequestMapping("/checkClaim/{accId}")                                         //Insured Person use this
    public String checkClaimAmount(@PathVariable Integer accId)
    {
        return claimAmntService.checkClaimAmount(accId);
    }

    @Autowired
    private UpdateAccident updateAccident;

  /*  @RequestMapping(value = "/updateAccident",method = RequestMethod.POST)          // Insurance Adjuster use this
    public String updateAccidentInformation(@RequestBody AccidentInfoDTO accidentInfoDTO)
    {
       return updateAccident.updateAccidentInfo(accidentInfoDTO);
    }*/

    @RequestMapping("/updateAccident/{accId}/{damageAmnt}/{accidentArea}/{description}/end")          // Insurance Adjuster use this
    public String updateAccidentInformation(@PathVariable Integer accId,@PathVariable String damageAmnt,@PathVariable String accidentArea,@PathVariable String description )
    {
        AccidentInfoDTO accidentInfoDTO = new AccidentInfoDTO(accId,damageAmnt,accidentArea,description);
        return updateAccident.updateAccidentInfo(accidentInfoDTO);
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }


    @Autowired
    UsersRepo usersRepo;

    @RequestMapping("/getId/{user}")
    public Integer getIdfromUsername(@PathVariable String user){

      return usersRepo.findUserByUsername(user).getId();
    }


    @RequestMapping("/logIn/{username}/{password}")
    public Integer LogIn(@PathVariable String username,@PathVariable String password)
    {
        User usr = usersRepo.findUserByUsername(username);
        if(usr != null)
        {
            if(usr.getUserPassword().equals(password))
            {
                return usr.getId();
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    @Autowired
    coverageService covser;

    @RequestMapping("/setCoverage/{accId}/{coverage}")
    public String SetCoverageTrue(@PathVariable Integer accId,@PathVariable Integer coverage){

        return covser.covering(accId,coverage);
    }


    @Autowired
    Rate rate;

    @RequestMapping("/Rate/{gId}/{star}")
    public String SetGrate(@PathVariable Integer gId,@PathVariable Integer star){

       return rate.RateGarage(gId,star);
    }

    @Autowired
    GarageRepository grepo;

    @RequestMapping("/getRate/{gId}")
    public Integer getGRate(@PathVariable Integer gId){

       Garage garage =  grepo.findOne(gId);
       if(garage.getStar()!=null) {
           return garage.getStar().intValue();
       }else{
           return -1;
       }
    }

}
