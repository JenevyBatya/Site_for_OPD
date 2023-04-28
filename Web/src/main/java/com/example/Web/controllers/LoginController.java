package com.example.Web.controllers;

import com.example.Web.models.occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.Web.repo.OccupationRepository;
@Controller
public class LoginController {
    @Autowired
    private OccupationRepository occupationRepository;
    @GetMapping("/occupation")
    public String occupation(Model model) {
        model.addAttribute("title", "Login page");
        Iterable<occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);
        return "Occupation";
    }
    @GetMapping("/occupation/add")
    public String add(Model model) {
        model.addAttribute("title", "Login page");
        Iterable<occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);
        return "Occupation_add";
    }
@GetMapping("/login")
public String login(Model model){
    model.addAttribute("title","Login page");
//        Iterable<occupation> occupations = occupationRepository.findAll();
//        model.addAttribute("occupations", occupations);
    return "Login";
}
}
