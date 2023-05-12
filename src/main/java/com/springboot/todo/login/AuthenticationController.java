package com.springboot.todo.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class AuthenticationController {
    private AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String authAndWelcome(@RequestParam String name, @RequestParam String password, ModelMap model){
        // @RequestBody VS. @RequestParam
        // for POST request
        // @RequestBody deserialize the body to java object
        // @RequestParam deserialize the body to the method's argument
        if (authService.authenticate(name, password)){
            model.put("name", name);
            return "welcome";
        }
        else{
            model.put("errorMessage", "Wrong password or username, please try again!");
            return "login";
        }
    }






}
