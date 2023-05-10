package com.example.Web.controllers;

import com.example.Web.models.Adjective;
import com.example.Web.models.Occupation;
import com.example.Web.models.User;
import com.example.Web.models.result;
import com.example.Web.repo.AdjectiveRepository;
import com.example.Web.repo.OccupationRepository;
import com.example.Web.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ExpertController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    OccupationRepository occupationRepository;
    @Autowired
    AdjectiveRepository adjectiveRepository;
    @GetMapping("/expert")
    public String startMode(){
        return "expertsMode";
    }
    @GetMapping("/expert/send_test")
    public String sendTest(Model model){
        Iterable<User> allUsers = userRepo.findAll();
        model.addAttribute("allUsers",allUsers);
        return "send_test";
    }
    @GetMapping("/expert/grading_occupation")
    public String show(Model model){
        Iterable<Occupation> allOccupations = occupationRepository.findAll();
        model.addAttribute("allOccupations");
        return "occupations";
    }
    @GetMapping("/expert/grading_occupation/{id}")
    public String grade(Model model,@PathVariable(value = "id") int id){
        Occupation occupation = occupationRepository.findById(id).get();
        model.addAttribute("allOccupations");
        Iterable<Adjective> adjectiveList = adjectiveRepository.findAll();
        model.addAttribute("adjectiveList", adjectiveList);
        model.addAttribute("result", new result());
        return "grading_occupation";
    }

}
