package com.example.Web.controllers;

import com.example.Web.models.occupation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login page");
//        Iterable<occupation> occupations = occupationRepository.findAll();
//        model.addAttribute("occupations", occupations);
        return "Login";
    }
}
