package com.example.demo.Controllers;

import com.example.demo.Entities.User;
import com.example.demo.MongoDB.MongoDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import static com.example.demo.MongoDB.MongoDB.isUsernameAvailable;

@Controller
@RequestMapping(path = "")
public class RegistrationController {

    @GetMapping("/register")
    public String viewRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model){
        try{
            validateUsername(user.getUsername());
            isUsernameAvailable(user.getUsername());
            validatePassword(user.getPassword());
            if(!user.confirmPassword())
                throw new Exception(String.format("Passwords don't match"));
            MongoDB.addUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            return "register";
        }
        model.addAttribute("success", "You have Successfully Registered.");
        model.addAttribute("user", new User());
        return "register";
    }
    
    private static boolean validateUsername(String username) throws Exception {
        if(username.length() < 4)
        	 throw new Exception(String.format("Username should have at least. username length = %d characters!", username.length()));
        else if(!Character.isAlphabetic(username.charAt(0)))
        	  throw new Exception(String.format("Username should start with an alphabetic character!"));
        else{
            for(char c : username.toCharArray()){
                if(!(Character.isLetterOrDigit(c) || c == '_' || c == '-'))
                    throw new Exception(String.format("Username should have alphanumeric characters\n or underscore or hyphen only!"));
            }
            return true;
        }
    }

    private static boolean validatePassword(String password) throws Exception{
        if(password.length() < 8)
        throw new Exception(String.format("Password should have atleast. password length = %d characters!", password.length()));
        else{
            int upper = 0, lower = 0, num = 0, special = 0;
            for(char c : password.toCharArray()){
                if(Character.isSpaceChar(c) || c == '>' || c == '<' || c == '&' || c == ';')
                    throw new Exception(String.format("Password has invalid characters: " + c));
                else if(Character.isUpperCase(c))
                    upper++;
                else if(Character.isLowerCase(c))
                    lower++;
                else if(Character.isDigit(c))
                    num++;
                else
                    special++;

            }
            if(upper < 2)
                throw new Exception("Password should have at least 2 upper-case characters");
            if(lower < 2)
                throw new Exception("Password should have at least 2 lower-case characters");
            if(num < 2)
                throw new Exception("Password should have at least 2 numeric characters");
            if(special < 2)
                throw new Exception("Password should have at least 2 special characters");
            return true;
        }
    }

}

