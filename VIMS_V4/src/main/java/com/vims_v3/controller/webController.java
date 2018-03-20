package com.vims_v3.controller;

import com.vims_v3.dto.GarageDTO;
import com.vims_v3.model.*;
import com.vims_v3.repository.*;
import com.vims_v3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by promod on 11/23/2017.
 */
@Controller
public class webController {

    @Autowired
    private uncheckedAccidentFinder accidentFinder;

    @RequestMapping(value="/accidentL", method= RequestMethod.GET)
    public String managerForm(Model model)
    {
        model.addAttribute("accidents",accidentFinder.checkAccidents());
        return "accidents";
    }


    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String managerForm()
    {

        return "index";
    }

    @RequestMapping(value="/sidebar", method= RequestMethod.GET)
    public String SideBar()
    {

        return "ManagerHomeSidebar";
    }

    @Autowired
    AccidentRepository accidentRepository;

    @RequestMapping(value="/load2", method=RequestMethod.GET)
    public String getAccidentDetails(@RequestParam("id") Integer id,@RequestParam("user") String user2,Model model) {

      Accident accident = accidentRepository.findOne(id);
       Vehicle vehicle =  accident.getVehicleList().get(0);
        InsuredPerson insuredPerson = vehicle.getOwnerId();
        User user = usersRepo.findOne(insuredPerson.getId());

        User userE = usersRepo.findUserByUsername(user2);
        ClaimAssessingManager mng = claimManagerRepo.findOne(userE.getId());
        accident.setClaimingManagerId(mng);
        accidentRepository.save(accident);

        model.addAttribute("accident", accident);
        model.addAttribute("vehicle",vehicle);
        model.addAttribute("owner",insuredPerson);
        model.addAttribute("ownerinfo",user);

        return "AccidentDetails";
    }

    @RequestMapping(value="/load", method=RequestMethod.GET)
    public String getAccidentDetails(@RequestParam("id") Integer id,Model model) {

        Accident accident = accidentRepository.findOne(id);
        Vehicle vehicle =  accident.getVehicleList().get(0);
        InsuredPerson insuredPerson = vehicle.getOwnerId();
        User user = usersRepo.findOne(insuredPerson.getId());

        model.addAttribute("accident", accident);
        model.addAttribute("vehicle",vehicle);
        model.addAttribute("owner",insuredPerson);
        model.addAttribute("ownerinfo",user);

        return "AccidentDetails";
    }


    @RequestMapping(value="/load3", method=RequestMethod.GET)
    public String getAccidentDetailsInRestrict(@RequestParam("id") Integer id,Model model) {

        Accident accident = accidentRepository.findOne(id);
        Vehicle vehicle =  accident.getVehicleList().get(0);
        InsuredPerson insuredPerson = vehicle.getOwnerId();
        User user = usersRepo.findOne(insuredPerson.getId());

        model.addAttribute("accident", accident);
        model.addAttribute("vehicle",vehicle);
        model.addAttribute("owner",insuredPerson);
        model.addAttribute("ownerinfo",user);

        return "AccidentDeRestrict";
    }


    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String DamageAmountUpdate(@ModelAttribute Accident accident, Model model) {

        model.addAttribute("accident", accident);

        Accident acc = accidentRepository.findOne(accident.getAccidentId());
        acc.setDamageAmount(accident.getDamageAmount());
        accidentRepository.save(acc);
        return "DamageAmntSuccess";

    }


    @RequestMapping(value="/message", method=RequestMethod.GET)
    public String getNewMessages(@RequestParam("id") Integer id, Model model) {

        Accident accident = accidentRepository.findOne(id);

        model.addAttribute("accident", accident);

        return "messages";
    }


    @Autowired
    private ClosestAdjuster closestAdjuster;

    @RequestMapping(value="/getAdjuster", method=RequestMethod.GET)
    public String getNewAdjuster(@RequestParam("id") Integer id, Model model) {

        InsuranceAdjuster adjuster = closestAdjuster.findClosestAdjuster(id);

        if(adjuster.getId() != -1) {
            model.addAttribute("adjuster", adjuster);

            return "AdjusterSent";
        }else{
            return "unavailable";
        }
    }


    @Autowired
    private unpaidAccidentFinder unpaid;

    @RequestMapping(value="/unpaidL", method= RequestMethod.GET)
    public String unpaidList(@RequestParam("user") String user,Model model)
    {
        User userE = usersRepo.findUserByUsername(user);
        ClaimAssessingManager mng = claimManagerRepo.findOne(userE.getId());

        List<Accident> accList = unpaid.checkAccidents(mng);

        Collections.sort(accList, new Comparator<Accident>(){            /****sort**/
        public int compare(Accident o1, Accident o2){
            if(o1.getAccidentId() == o2.getAccidentId())
                return 0;
            return o1.getAccidentId() < o2.getAccidentId() ? -1 : 1;
        }
        });

        model.addAttribute("accidents",accList);
        return "unpaid";
    }


    @RequestMapping(value="/updatePayment", method=RequestMethod.POST)
    public String updatePaymentStatus(@ModelAttribute Accident accident2, Model model) {

        Accident accident = accidentRepository.findOne(accident2.getAccidentId());
        accident.setPaid(accident2.getPaid());
        accidentRepository.save(accident);
        model.addAttribute("accident", accident);

        return "claimPaid";
    }


    @RequestMapping(value="/BillFindform", method= RequestMethod.GET)
    public String billFindingForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "billForm";
    }

    @Autowired
    PaymentRepository paymentRepository;

    @RequestMapping(value="/findBills", method=RequestMethod.POST)
    public String getBillDetails(@ModelAttribute Payment payment, Model model) {

        model.addAttribute("payments",paymentRepository.findBillsByAccident(payment.getAccidentId()));
        return "bills";

    }

    @Autowired
    GarageRepository garageRepository;

    @RequestMapping(value="/Garage", method=RequestMethod.GET)
    public String findGarageInfo(@RequestParam("id") Integer id, Model model) {

       Garage garage = garageRepository.findOne(id);

        model.addAttribute("garage", garage);

        return "garageInfo";
    }


    @RequestMapping(value="/main", method= RequestMethod.GET)
    public String HomePage()
    {

        return "Main";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String LogInPage()
    {

        return "Login";
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String RegisterEntrance()
    {

        return "Register";
    }

    @RequestMapping(value="/ipReg", method= RequestMethod.GET)
    public String IPRegister(Model model)
    {
        model.addAttribute("insured", new InsuredPerson());
        model.addAttribute("user",new User());
        return "InsuredPersonReg";
    }

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    InsuredPersonRepo insuredrepo;

    @RequestMapping(value="/IPRegform", method=RequestMethod.POST)
    public String RegisterIP(@ModelAttribute InsuredPerson insured,@ModelAttribute User user) {
       if(usersRepo.findUserByUsername(user.getName()) == null) {
           user.setRole("ROLE_USER");
           user.setEnabled(1);
           User usrbuff = usersRepo.save(user);
           insured.setId(usrbuff.getId());
           insuredrepo.save(insured);
           return "signupSuccess";
       }else{
           return "signupFail";
       }
    }

    @RequestMapping(value="/iaReg", method= RequestMethod.GET)
    public String IARegister(Model model)
    {
        model.addAttribute("adjuster", new InsuranceAdjuster());
        model.addAttribute("user",new User());
        return "InsuranceAdjusterReg";
    }

    @Autowired
    InsuranceAdjusterRepository adjRepo;

    @RequestMapping(value="/IARegform", method=RequestMethod.POST)
    public String RegisterIA(@ModelAttribute InsuranceAdjuster adjuster,@ModelAttribute User user) {
        if(usersRepo.findUserByUsername(user.getName()) == null) {
            user.setRole("ROLE_ADJUSTER");
            User usrbuff = usersRepo.save(user);
            adjuster.setId(usrbuff.getId());
            adjuster.setAvailability(0);     //added later
            adjRepo.save(adjuster);
            return "signupSuccess";
        }else{
            return "signupFail";
        }
    }


    @RequestMapping(value="/emReg", method= RequestMethod.GET)
    public String EMRegister(Model model)
    {
        model.addAttribute("driver", new EmergencyVehicleDriver());
        model.addAttribute("user",new User());
        return "EmergencyVdriverReg";
    }

    @Autowired
    EmergencyVehicleRepo EMrepo;

    @RequestMapping(value="/EMRegform", method=RequestMethod.POST)
    public String RegisterEM(@ModelAttribute EmergencyVehicleDriver driver,@ModelAttribute User user) {
        if(usersRepo.findUserByUsername(user.getName()) == null) {
            user.setRole("ROLE_DRIVER");
            User usrbuff = usersRepo.save(user);
            driver.setId(usrbuff.getId());
            EMrepo.save(driver);
            return "signupSuccess";
        }else{
            return "signupFail";
        }
    }


    @RequestMapping(value="/InsuredLogedIn", method= RequestMethod.GET)
    public String InsuredLogedIn()
    {

        return "insuredPlogedIn";
    }


    @RequestMapping(value="/vForm", method=RequestMethod.GET)
    public String setFormNewVehicle(@RequestParam("user") String user, Model model) {

        User userE = usersRepo.findUserByUsername(user);

        model.addAttribute("user", userE);
        model.addAttribute("vehicle", new Vehicle());
        return "vehicleForm";
    }


    @Autowired
    VehicleRepository vehicleRepository;

    @RequestMapping(value="/VRegform", method=RequestMethod.POST)
    public String RegisterVehicle(@ModelAttribute Vehicle vehicle,@ModelAttribute User user) {
        InsuredPerson insuredBuff = insuredrepo.findOne(user.getId());
        List<Vehicle> vList = new ArrayList<>();
        vList.add(vehicle);
        insuredBuff.setVehicleList(vList);         //Need to validate License Plate No.
        vehicle.setOwnerId(insuredBuff);
        vehicleRepository.save(vehicle);
        return "signupSuccess";
    }


    @RequestMapping(value="/loginSlct", method= RequestMethod.GET)
    public String LoginPageSelection()
    {

        return "loginSelect";
    }


    @RequestMapping(value="/garage", method= RequestMethod.GET)
    public String GarageMainPage()
    {
        return "garage";
    }



    @Autowired
    GarageHistoryRepo garageHistoryRepo;

    @RequestMapping(value="/vListGarage", method=RequestMethod.GET)
    public String OngoingVehiclesGarage(@RequestParam("user") String user, Model model) {

        User userE = usersRepo.findUserByUsername(user);
        Garage ga = garageRepository.findOne(userE.getId());
        List<GarageHistory> garageHisList = garageHistoryRepo.findVehiclesByGarage(ga);
        model.addAttribute("gaHisList", garageHisList);

        return "onComingV";
    }


    @RequestMapping(value="/GarageLogged", method= RequestMethod.GET)
    public String GarageLoggedInPage()
    {
        return "GarageLogged";
    }

    @RequestMapping(value="/seeVehicle", method=RequestMethod.GET)
    public String seeVehicle(@RequestParam("id") Integer id, Model model) {

        Vehicle vehicle = vehicleRepository.findOne(id);
        /***new code*****/
        User usr = usersRepo.findOne(vehicle.getOwnerId().getId());

        model.addAttribute("vehicle",vehicle);
        model.addAttribute("usr",usr);
        return "vehicleInfo";
    }


    @RequestMapping(value="/garageForm", method= RequestMethod.GET)
    public String GarageRegForm(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("garage", new Garage());
        return "GarageReg";
    }


    @RequestMapping(value="/GarageRegform", method=RequestMethod.POST)
    public String RegisterGarage(@ModelAttribute User user,@ModelAttribute Garage garage) {
        if(usersRepo.findUserByUsername(user.getName()) == null) {
            user.setRole("ROLE_GARAGE");
            User userbuff =usersRepo.save(user);
            garage.setGarageId(userbuff.getId());
            garageRepository.save(garage);
            return "signupSuccess";
        }else{
            return "signupFail";
        }
    }


    @RequestMapping(value="/manager", method= RequestMethod.GET)
    public String ManagerApproach()
    {
        return "ManagerApproach";
    }


    @RequestMapping(value="/inspectReport", method=RequestMethod.GET)
    public String inspectReportForm(@RequestParam("user") String user, Model model) {

        User userE = usersRepo.findUserByUsername(user);
        Garage ga = garageRepository.findOne(userE.getId());

        model.addAttribute("garage", ga);
        model.addAttribute("report",new Payment());

        return "inspectReportForm";
    }


    @RequestMapping(value="/inspectRpost", method=RequestMethod.POST)
    public String inspectFormpost(@ModelAttribute Garage garage,@ModelAttribute Payment report) {
       Garage gbuff = garageRepository.findOne(garage.getGarageId());
        List<Payment> pList = new ArrayList<>();

        if(accidentRepository.findOne(report.getAccidentId()) != null) {
            pList.add(report);
            gbuff.setPaymentList(pList);
            garageRepository.save(gbuff);
            paymentRepository.save(report);
            return "inspectRsuccess";
        }else{
            return "NoAccident";
        }

    }


    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String FindAccidentBySearch(@RequestParam("accid") Integer accid,@RequestParam("license") String license,@RequestParam("insid") Integer insid,@RequestParam("name") String name,@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname, Model model) {

       if(accid != null)
       {
           Accident buff = accidentRepository.findOne(accid);
           if(buff != null) {
               model.addAttribute("accident", buff);
               return "result";
           }else{
               return "Noresult";
           }
       }else if(license.length() > 1){
               Vehicle vroom = vehicleRepository.findByLicense(license);
                       if(vroom != null) {
                          List<Accident> accList = vroom.getAccidentList();
                          model.addAttribute("acclist",accList);
                          return "result2";
                       }else{
                           return "Noresult";
                       }
        }else if(insid != null){
               InsuredPerson person = insuredrepo.findOne(insid);
                       if(person != null)
                       {
                           List<Vehicle> vList = person.getVehicleList();
                           model.addAttribute("vlist",vList);

                          // Vehicle v = new Vehicle();
                        List<Accident> aList = new ArrayList<>();
                           for(Vehicle v:vList){
                             aList.addAll(v.getAccidentList());
                       }

                       model.addAttribute("aList",aList);
                           return "result3";
                       }else{
                           return "Noresult";
                       }
       }else if(name.length()>1){
            User user = usersRepo.findUserByUsername(name);
            if(user != null)
            {
                InsuredPerson person = insuredrepo.findOne(user.getId());
                if(person != null)
                {
                    List<Vehicle> vList = person.getVehicleList();
                    model.addAttribute("vlist",vList);
                    List<Accident> aList = new ArrayList<>();
                    for(Vehicle v:vList){
                        aList.addAll(v.getAccidentList());
                    }

                    model.addAttribute("aList",aList);
                    return "result3";
                }else{
                    return "Noresult";
                }
            }else{
                return "Noresult";
            }

       }else if(firstname.length() > 1){
           List<User> uList = usersRepo.findUsersByFirstname(firstname);
           if(!uList.isEmpty())
           {
               model.addAttribute("ulist",uList);
               return "result4";
           }else{
               return "Noresult";
           }
       }else if(lastname.length() > 1) {
           List<User> uList  = usersRepo.findUsersByLastname(lastname);
           if(!uList.isEmpty())
           {
               model.addAttribute("ulist",uList);
               return "result4";
           }else{
               return "Noresult";
           }
       }else{
           return "Noresult";
       }

    }


    @RequestMapping(value="/searchAcc", method= RequestMethod.GET)
    public String SearchPage()
    {
        return "searchPage";
    }

    @RequestMapping(value="/managerForm", method= RequestMethod.GET)
    public String ManagerRegister(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("manager",new ClaimAssessingManager());
        return "managerRegForm";
    }

    @Autowired
    ClaimManagerRepo claimManagerRepo;

    @RequestMapping(value="/mngRegform", method=RequestMethod.POST)
    public String RegisterManager(@ModelAttribute ClaimAssessingManager manager,@ModelAttribute User user) {
        if(usersRepo.findUserByUsername(user.getName()) == null) {
            user.setRole("ROLE_MANAGER");
            User usrbuff = usersRepo.save(user);
            manager.setId(usrbuff.getId());
            claimManagerRepo.save(manager);
            return "signupSuccess";
        }else{
            return "signupFail";
        }
    }
//******************ADMIN********************************************************
@RequestMapping(value="/searchAdmin", method=RequestMethod.POST)
public String FindUsersBySearch(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("role") String role, Model model) {
        if(id != null)
        {
            User user = usersRepo.findOne(id);
            if(user != null) {
                model.addAttribute("user", user);
                return "adminResult";
            }else{
                return "Noresult";
            }
        }else if(name.length()>1){
            User user = usersRepo.findUserByOnlyUsername(name);
                    if(user != null)
                    {
                        model.addAttribute("user", user);
                        return "adminResult";
                    }else{
                        return "Noresult";
                    }
        }else if(firstname.length() > 1){
            List<User> uList = usersRepo.findUsersByFirstname(firstname);
            if(!uList.isEmpty())
            {
                model.addAttribute("ulist",uList);
                return "adminResult2";
            }else{
                return "Noresult";
            }
        }else if(lastname.length() > 1) {
            List<User> uList  = usersRepo.findUsersByLastname(lastname);
            if(!uList.isEmpty())
            {
                model.addAttribute("ulist",uList);
                return "adminResult2";
            }else{
                return "Noresult";
            }
        }else if(role.length() > 1){
            List<User> usrL = usersRepo.findUsersByRole(role);
            model.addAttribute("ulist",usrL);
            return "adminResult2";
        }else{
            return "Noresult";
        }

}


    @RequestMapping(value="/adminSideBar", method= RequestMethod.GET)
    public String Admin()
    {
        return "AdminHome";
    }

    @RequestMapping(value="/searchAdmin", method= RequestMethod.GET)
    public String AdminSearchPage()
    {
        return "searchPageAdmin";
    }

    @RequestMapping(value="/admin2", method= RequestMethod.GET)
    public String AdminSite()
    {
        return "indexAdmin";
    }

    @RequestMapping(value="/allUsers", method= RequestMethod.GET)
    public String AdminShowAllUsers(Model model)
    {
        List<User> target = new ArrayList<>();
        usersRepo.findAll().forEach(target::add);

        Collections.sort(target, new Comparator<User>(){            /****sort**/
        public int compare(User o1, User o2){
            if(o1.getId() == o2.getId())
                return 0;
            return o1.getId() < o2.getId() ? -1 : 1;
        }
        });

        model.addAttribute("ulist",target);
        return "adminResult2";

    }


    @RequestMapping(value="/enableAcc", method=RequestMethod.POST)
    public String EnableAccounts(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("enable") Integer enable){

        if(id!=null && name.length()>1)
        {
            User user = usersRepo.findOne(id);
            if(user != null)
            {
                    if(enable == 1)
                    {
                        user.setEnabled(1);
                        usersRepo.save(user);
                        return "updateSuccess";
                    } else{
                        user.setEnabled(0);
                        usersRepo.save(user);
                        return "updateSuccess";
                    }

            }else{
                return "failUpdate";
            }
        }else{
            return "failUpdate";
        }
    }

    @RequestMapping(value="/enAccount", method= RequestMethod.GET)
    public String AccountEnPage()
    {
        return "AccountEnable";
    }


    @RequestMapping(value="/setRole", method=RequestMethod.POST)
    public String UpdateRole(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("enable") String enable){

        if(id!=null && name.length()>1)
        {
            User user = usersRepo.findOne(id);
            if(user != null)
            {
                if(enable.equalsIgnoreCase("ROLE_ADMIN"))
                {
                    user.setRole("ROLE_ADMIN");
                    usersRepo.save(user);
                    return "updateSuccess";
                } else if(enable.equalsIgnoreCase("ROLE_USER")){
                    user.setRole("ROLE_USER");
                    usersRepo.save(user);
                    return "updateSuccess";
                }else if(enable.equalsIgnoreCase("ROLE_ADJUSTER")){
                    user.setRole("ROLE_ADJUSTER");
                    usersRepo.save(user);
                    return "updateSuccess";
                }else if(enable.equalsIgnoreCase("ROLE_MANAGER")){
                    user.setRole("ROLE_MANAGER");
                    usersRepo.save(user);
                    return "updateSuccess";
                }else if(enable.equalsIgnoreCase("ROLE_GARAGE")){
                    user.setRole("ROLE_GARAGE");
                    usersRepo.save(user);
                    return "updateSuccess";
                }else if(enable.equalsIgnoreCase("ROLE_DRIVER")){
                    user.setRole("ROLE_DRIVER");
                    usersRepo.save(user);
                    return "updateSuccess";
                }else{
                    return "failUpdate";
                }

            }else{
                return "failUpdate";
            }
        }else{
            return "failUpdate";
        }
    }


    @RequestMapping(value="/chngRole", method= RequestMethod.GET)
    public String ChangeRolePage()
    {
        return "changeRole";
    }

    @RequestMapping(value="/garageHome", method= RequestMethod.GET)
    public String garageHomePage()
    {
        return "garageIndex";
    }


    @RequestMapping(value="/recentAcc", method=RequestMethod.GET)
    public String recentAccByManagers(@RequestParam("user") String user, Model model) {

        User userE = usersRepo.findUserByUsername(user);
        ClaimAssessingManager mng = claimManagerRepo.findOne(userE.getId());
        List<Accident> accList = accidentRepository.findAccidentByClaimManager(mng);

        Collections.sort(accList, new Comparator<Accident>(){            /****sort**/
            public int compare(Accident o1, Accident o2){
                if(o1.getAccidentId() == o2.getAccidentId())
                    return 0;
                return o1.getAccidentId() < o2.getAccidentId() ? -1 : 1;
            }
        });

        model.addAttribute("acclist", accList);

        return "ownedAccidents";
    }

    @RequestMapping(value="/admin", method= RequestMethod.GET)
    public String AdminApproach()
    {
        return "AdminApproach";
    }

    @RequestMapping(value="/adminForm", method= RequestMethod.GET)
    public String AdminRegister(Model model)
    {
        model.addAttribute("user", new User());
        return "AdminRegForm";
    }


    @RequestMapping(value="/adminRegform", method=RequestMethod.POST)
    public String RegisterAdmin(@ModelAttribute User user) {
        if(usersRepo.findUserByUsername(user.getName()) == null) {
            user.setRole("ROLE_ADMIN");
            usersRepo.save(user);
            return "signupSuccess";
        }else{
            return "signupFail";
        }
    }

    @RequestMapping(value="/home", method= RequestMethod.GET)
    public String HomePagevims()
    {
        return "Home";
    }


    @RequestMapping(value="/doneCheck", method=RequestMethod.GET)
    public String DoneCheckVehicleGarage(@RequestParam("user") String user,@RequestParam("vid") Integer vid) {

        User userE = usersRepo.findUserByUsername(user);
        Garage ga = garageRepository.findOne(userE.getId());
        Vehicle vehicle = vehicleRepository.findOne(vid);
        GarageHistory garageHis = garageHistoryRepo.findHisByVehicleAndGarage(ga,vehicle);
        garageHis.setChecked("checked");
        garageHistoryRepo.save(garageHis);
        return "confirmSuccess";
    }

//********Mobile WEB**************************************************************

    @Autowired
    private ReportService reportService;

    @RequestMapping(value="/reportWEB", method=RequestMethod.POST)
    public String ReportAccidentWEB(@RequestParam("license") String license,@RequestParam("latitude") String lat,@RequestParam("longitude") String longt, Model model) {

      // model.addAttribute( "accident",reportService.saveAccident(license,lat,longt));
       if(reportService.saveAccident(license,lat,longt) != -1) {
           model.addAttribute( "accident",reportService.saveAccident(license,lat,longt));
           return "uploadForm";
       }else{
           return "NoVehicle";
       }
    }


    @RequestMapping(value="/ajax", method= RequestMethod.GET)
    public String AJAXtest()
    {

        return "ajax";
    }

    @Autowired
    ClosestGarage closestGarage;
    @RequestMapping(value="/getGarageWEB", method=RequestMethod.GET)
    public String getGarageListWeb(@RequestParam("lat") String lat,@RequestParam("longt") String longt, Model model) {


        List<GarageDTO> gList = closestGarage.findGarages(lat,longt);
        model.addAttribute("glist", gList);

        return "garageListweb";
    }

    @RequestMapping(value="/getPath", method=RequestMethod.GET)
    public String pathToGarage(@RequestParam("lat") String lat,@RequestParam("longt") String longt, Model model) {

        model.addAttribute("lati", lat);
        model.addAttribute("longti",longt);

        return "map";
    }

}
