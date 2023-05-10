package com.example.Web.controllers;

import com.example.Web.models.*;
import com.example.Web.repo.AdjectiveRepository;
import com.example.Web.repo.ExpertsOpinionRepo;
import com.example.Web.repo.OccupationRepository;
import com.example.Web.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ExpertController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    OccupationRepository occupationRepository;
    @Autowired
    AdjectiveRepository adjectiveRepository;
    @Autowired
    ExpertsOpinionRepo expertsOpinionRepo;

    @GetMapping("/expert")
    public String startMode() {
        return "expertsMode";
    }

    @GetMapping("/expert/send_test")
    public String sendTest(Model model) {
        Iterable<User> allUsers = userRepo.findAll();
        model.addAttribute("allUsers", allUsers);
        return "send_test";
    }

    @GetMapping("/expert/grading_occupation")
    public String show(Model model) {
        Iterable<Occupation> allOccupations = occupationRepository.findAll();
        model.addAttribute("allOccupations", allOccupations);
        return "occupations";
    }

    @GetMapping("/expert/grading_occupation/{id}")
    public String grade(Model model, @PathVariable(value = "id") int id) {
        Occupation occupation = occupationRepository.findById(id).get();
        model.addAttribute("occupation", occupation);
        Iterable<Adjective> adjectiveList = adjectiveRepository.findAll();
        model.addAttribute("adjectiveList", adjectiveList);
        model.addAttribute("result", new result());
        return "grading_occupation";
    }

    @PostMapping("/expert/grading_occupation/{id}")
    public String gradeBD(@PathVariable(value = "id") int id, @ModelAttribute("result") result result, Authentication authentication) {
        int[] selectedIds = result.getSelectedIds();
        Occupation occupation = occupationRepository.findById(id).get();
        User user = userRepo.findByEmail(authentication.getName());
        if (selectedIds != null || selectedIds.length != 0) {
            for (int adjId : selectedIds) {
                Adjective adjective = adjectiveRepository.findById(adjId).get();
                expertsOpinionRepo.save(new ExpertsOpinion(user, occupation, adjective));

            }
        }
        return "redirect:/expert/grading_occupation";

    }
}
