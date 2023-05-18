package com.springboot.todo.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class AuthenticationController {
    @GetMapping("/")
    public String welcomePage(ModelMap model) {
        model.put("name",getLoginUsername());
        return "welcome";
    }

    private String getLoginUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();

    }
}
