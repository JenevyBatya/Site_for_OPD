package com.example.Web.controllers;

import com.example.Web.models.occupation;
import com.example.Web.repo.OccupationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private OccupationRepository occupationRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","Main page");
        Iterable<occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);
        return "test_design";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title","Login page");
//        Iterable<occupation> occupations = occupationRepository.findAll();
//        model.addAttribute("occupations", occupations);
        return "Login";
    }


}
