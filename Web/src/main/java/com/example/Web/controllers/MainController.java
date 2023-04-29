package com.example.Web.controllers;

//import com.example.Web.models.ChosenAdj;
import com.example.Web.models.Occupation;
//import com.example.Web.repo.AdjectiveRepository;
//import com.example.Web.models.Adjective;
import com.example.Web.models.adjective;
import com.example.Web.models.result;
import com.example.Web.models.temp;
import com.example.Web.repo.AdjectiveRepository;
import com.example.Web.repo.OccupationRepository;
import com.example.Web.repo.tempRepj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private OccupationRepository occupationRepository;
    @Autowired
    private AdjectiveRepository adjectiveRepository;
    @Autowired
    private tempRepj tempRepj;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        Iterable<Occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);
        return "test_design";
    }

    @GetMapping("/occupation")
    public String occupation(Model model){
        model.addAttribute("title","Main page");
        Iterable<Occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);
        return "Occupation";
    }
    @GetMapping("/tests")
    public String showAdjectives(Model model) {
        Iterable<adjective> adjectiveList = adjectiveRepository.findAll();
        model.addAttribute("adjectiveList", adjectiveList);
        model.addAttribute("result", new result());
        return "Tests";
    }

    @PostMapping("/tests")
    public String saveAdjectives(@ModelAttribute("result") result result, Model model) {
        int[] selectedIds = result.getSelectedIds();
        temp temp;
        if (selectedIds != null){
            List<String> selectedNames = new ArrayList<>();
            for (int id : selectedIds) {
                adjective adjective = adjectiveRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid adjective id: " + id));;
                selectedNames.add(adjective.getTrait_name());
                temp = new temp(id,adjective.getTrait_name());
                tempRepj.save(temp);
            }
            // Дальнейшая обработка выбранных прилагательных
        }
        else{
            return "redirect:/tests";
        }
        return "redirect:/tests";
    }



    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title","Login page");
        return "Login";
    }
    @GetMapping("/occupation/add")
    public String add(Model model) {
        model.addAttribute("title", "add page");
        Iterable<Occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);

        return "Occupation_add";
    }

    @PostMapping("/occupation/add")
    public String occupationAdd(@RequestParam String occupation_name,@RequestParam String description,@RequestParam String category,@RequestParam String trait_name, Model model){
        Occupation occupation = new Occupation(occupation_name,description);
        adjective adjective = new adjective(trait_name,category);
        occupationRepository.save(occupation);
        adjectiveRepository.save(adjective);
        return "redirect:/occupation";
    }
}
