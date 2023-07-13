package com.example.imsmanagemnt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/home")
    public String h(){
        return "index";
    }

    @GetMapping("/sales")
    public String sales() {
        return "sales";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }


}
