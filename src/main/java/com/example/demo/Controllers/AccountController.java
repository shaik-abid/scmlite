package com.example.demo.Controllers;

import com.example.demo.Entities.Shipment;
import com.example.demo.Entities.User;
import com.example.demo.MongoDB.MongoDB;

import com.example.demo.document.*;
import com.sun.media.jfxmedia.logging.Logger;

import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


//@RestController
@Controller
@RequestMapping(path = "/account")
//@ResponseBody
public class AccountController {
	
//	@Autowired
//	private ShipmentTransactionsRepository shiptransrepo;

    private User user = null;

    @GetMapping("/login")
    public String viewLoginPage(Model model){
        if(this.user != null)
            return "redirect:/account/dashboard";
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String signInUser(@ModelAttribute User user, Model model){
        if(this.user != null)
            return "redirect:/account/dashboard";
        try{
            MongoDB.authenticateUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new User());
            return "login";
        }
        this.user = user;
        return "redirect:/account/dashboard";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model){
        if(this.user == null)
            return "redirect:/account/login";
        else
            model.addAttribute("user", this.user);
            return "dashboard";
    }

    @GetMapping("/logout")
    public String logoutUser(){
        if(this.user != null)
            this.user = null;
        return "redirect:/account/login";
    }


    @GetMapping("/devices")
    public String getDevicesDataStream(Model model){
        if(this.user == null)
            return "redirect:/account/login";
        else{
            model.addAttribute("devices", MongoDB.getDevices());
            return "devices";
        }
    }
         
    @ResponseBody
    @GetMapping("/deviceData")
    public String getDevicesData(Model model){
    	String DeviceDataString = null;
        if(this.user == null)
            return "false";
        else{
        	
        	try {
				model.addAttribute("devices", MongoDB.getDevices());
				DeviceDataString = DeviceDataString.valueOf(model);
				return DeviceDataString;
        	}
        	catch (Exception e){
        		System.out.println("Device Data list is null" + e);
        	}
        	 return DeviceDataString;
        }
    }

    
    
    @GetMapping("/createShipment")
    public String createShipmentPage(Model model){
        if(this.user == null)
            return "redirect:/account/login";
        else{
            model.addAttribute("shipment", new Shipment());
            model.addAttribute("devices", MongoDB.getDeviceIDs());
            return "createShipment";
        }
    }

    @PostMapping("/createShipment")
    public String submitShipmentForm(@Validated @ModelAttribute Shipment shipment, Model model){    	
        if(this.user == null)
        	 return "redirect:/account/login";
        else{
            model.addAttribute("devices", MongoDB.getDeviceIDs());
            try {
                notEmpty(shipment.getShipment_Number(), "Enter Shipment Number");
                notEmpty(shipment.getContainer_no(), "Enter Container Number");
                notEmpty(shipment.getDesc(), "Provide a description");
                notEmpty(shipment.getRoute(), "Select a Route");
                notEmpty(shipment.getGoods(), "Select Goods");
                notEmpty(shipment.getDevice(), "Select Device");
                notEmpty(shipment.getDate(), "Enter Date");
                notEmpty(shipment.getPo_no(), "Enter PO Number");
                notEmpty(shipment.getDelivery_no(), "Enter Delivery Number");
                notEmpty(shipment.getNdc_no(), "Enter NDC Number");
                notEmpty(shipment.getBatch_id(), "Enter Batch ID");
                notEmpty(shipment.getSerial_no(), "Enter Serial Number");
                MongoDB.addShipment(this.user.getUsername(), shipment.getShipment_Number(), shipment.getContainer_no(),
                        shipment.getDesc(), shipment.getRoute(), shipment.getGoods(), shipment.getDevice(), shipment.getDate(), shipment.getPo_no(), shipment.getDelivery_no(),
                        shipment.getNdc_no(), shipment.getBatch_id(), shipment.getSerial_no());
 
            }
            catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                model.addAttribute("shipment", shipment);
                System.out.println("Failed to create Shipment"+ e);
                return "createShipment";               
            }
            model.addAttribute("success", "Shipment created successfully!");
            model.addAttribute("shipment", new Shipment());       
            return "createShipment";
        }
    }
    
//    @ResponseBody
//    @PostMapping("/createShipment")
//    public boolean createShipment(@RequestBody Shipment shipment){
//    	boolean flag = false;
////        if(this.user == null)
////            return flag;
////        else{
////        	shipment.setDevice(MongoDB.getDeviceIDs());
//            try {
//            	ShipmentTransactions createshipment = new ShipmentTransactions();
//            	createshipment.setShipment_Num(shipment.getShipment_Number());
//            	createshipment.setContainer_num(shipment.getContainer_no());
//            	createshipment.setDescription(shipment.getDesc());
//            	createshipment.setRoute(shipment.getRoute());
//            	createshipment.setGoods(shipment.getGoods());
//            	createshipment.setDevice(shipment.getDevice());
//            	createshipment.setDate(shipment.getDate());
//            	createshipment.setPo_no(shipment.getPo_no());
//            	createshipment.setDelivery_num(shipment.getDelivery_no());
//            	createshipment.setNdc_num(shipment.getNdc_no());
//            	createshipment.setBatch_id(shipment.getBatch_id());
//            	createshipment.setSerial_num(shipment.getSerial_no());
//                MongoDB.addShipment(this.user.getUsername(), shipment.getShipment_Number(), shipment.getContainer_no(),
//                        shipment.getDesc(), shipment.getRoute(), shipment.getGoods(), shipment.getDevice(), shipment.getDate(), shipment.getPo_no(), shipment.getDelivery_no(),
//                        shipment.getNdc_no(), shipment.getBatch_id(), shipment.getSerial_no());
// //               shiptransrepo.insert(createshipment);
//            	flag = true;
//    			return flag;
//            }
//            catch (Exception e) {
////                model.addAttribute("error", e.getMessage());
////                model.addAttribute("shipment", shipment);
////                return "createShipment";
//            }
////            model.addAttribute("success", "Shipment created successfully!");
////            model.addAttribute("shipment", new Shipment());
////            return "createShipment";
//            return flag;
//        }
////    }
//    
//    
    
   @ResponseBody
   @PostMapping("/createShipments")
   public String submitShipmentForms(@Validated @RequestBody Shipment shipment, Model model){    	
   	boolean flag = false;
       if(this.user == null)
       	 return "false";
       else{
           model.addAttribute("devices", MongoDB.getDeviceIDs());
           try {
				shipment.getShipment_Number();
				shipment.getContainer_no();
				shipment.getDesc();
				shipment.getRoute();
				shipment.getGoods();
				shipment.getDevice();
				shipment.getDate();
				shipment.getPo_no();
				shipment.getDelivery_no();
				shipment.getNdc_no();
				shipment.getBatch_id();
				shipment.getSerial_no();
               MongoDB.addShipment(this.user.getUsername(), shipment.getShipment_Number(), shipment.getContainer_no(),
                       shipment.getDesc(), shipment.getRoute(), shipment.getGoods(), shipment.getDevice(), shipment.getDate(), shipment.getPo_no(), shipment.getDelivery_no(),
                       shipment.getNdc_no(), shipment.getBatch_id(), shipment.getSerial_no());
   			return "true";
           }
           catch (Exception e) {
               model.addAttribute("error", e.getMessage());
               model.addAttribute("shipment", shipment);
               System.out.println("Failed to create Shipment"+ e);              
           }
           model.addAttribute("success", "Shipment created successfully!");
           model.addAttribute("shipment", new Shipment());       
           return "true";
       }
   }

    private static boolean isAlphabetic(String s) throws Exception {
        for(char c: s.toCharArray()){
            if(!Character.isAlphabetic(c))
                throw new Exception("Only alphabetic characters are allowed!");
        }
        return true;
    }

    private static boolean notEmpty(String s, String msg) throws Exception{
        if(s.equals(""))
            throw new Exception(msg);
        return true;
    }

}
