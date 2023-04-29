package com.example.Web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.Web.repo.OccupationRepository;
@Controller
public class LoginController {
    @Autowired
    private OccupationRepository occupationRepository;
//    @GetMapping("/occupation")
//    public String occupation(Model model) {
//        model.addAttribute("title", "Login page");
//        Iterable<occupation> occupations = occupationRepository.findAll();
//        model.addAttribute("occupations", occupations);
//        return "Occupation";
//    }


}
