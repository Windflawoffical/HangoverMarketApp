package com.example.hangovermarketwebservice.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/check")
    public String check_commits(Model model) {
        return "check";
    }
}