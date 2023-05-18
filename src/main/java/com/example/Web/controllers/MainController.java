package com.example.Web.controllers;

//import com.example.Web.models.ChosenAdj;

import com.example.Web.AdjectiveCount;
import com.example.Web.models.*;
//import com.example.Web.repo.AdjectiveRepository;
//import com.example.Web.models.Adjective;
import com.example.Web.repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    JdbcTemplate jdbcTemplate;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private OccupationRepository occupationRepository;
    @Autowired
    private AdjectiveRepository adjectiveRepository;
    //    @Autowired
//    private tempRepj tempRepj;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AwaitingForCheckingByRepository awaitingForCheckingByRepository;


    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("title", "Main page");
        boolean test = authentication != null && authentication.isAuthenticated();
        model.addAttribute("test", test);
        return "test_design";

    }

    @GetMapping("/occupation")
    public String occupation(Model model, Authentication authentication) {
        String forChecked = "SELECT DISTINCT eo.occupation.id from ExpertsOpinion eo";
        Query query = entityManager.createQuery(forChecked, Integer.class);
        List<Integer> ratedOccupations = query.getResultList();
        model.addAttribute("ratedOccupations", ratedOccupations);
        model.addAttribute("title", "Main page");
        Iterable<Occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);
        boolean test = authentication != null && authentication.isAuthenticated();
        model.addAttribute("test", test);
        return "Occupation";
    }

//    @GetMapping("/tests")
//    public String showAdjectives(Model model, Authentication authentication) {
//        Iterable<Adjective> adjectiveList = adjectiveRepository.findAll();
//        model.addAttribute("adjectiveList", adjectiveList);
//        model.addAttribute("result", new result());
//        boolean test = authentication != null && authentication.isAuthenticated();
//        model.addAttribute("test", test);
//        return "Tests";
//    }
//
//    @PostMapping("/tests")
//    public String saveAdjectives(@ModelAttribute("result") result result, Model model) {
//        int[] selectedIds = result.getSelectedIds();
//        temp temp;
//        if (selectedIds != null) {
//            List<String> selectedNames = new ArrayList<>();
//            for (int id : selectedIds) {
//                Adjective adjective = adjectiveRepository.findById(id)
//                        .orElseThrow(() -> new IllegalArgumentException("Invalid adjective id: " + id));
//                ;
//                selectedNames.add(adjective.getTrait_name());
//                temp = new temp(id, adjective.getTrait_name());
//                tempRepj.save(temp);
//            }
//            // Дальнейшая обработка выбранных прилагательных
//        } else {
//            return "redirect:/tests";
//        }
//        return "redirect:/tests";
//    }


    @GetMapping("/occupation/add")
    public String add(Model model, Authentication authentication) {
        model.addAttribute("title", "add page");
        Iterable<Occupation> occupations = occupationRepository.findAll();
        model.addAttribute("occupations", occupations);
        Iterable<Adjective> adjectiveList = adjectiveRepository.findAll();
        model.addAttribute("adjectiveList", adjectiveList);
        model.addAttribute("result", new result());
        boolean test = authentication != null && authentication.isAuthenticated();
        model.addAttribute("test", test);
        return "Occupation_add";
    }

    @PostMapping("/occupation/add")
    public String occupationAdd(@RequestParam String occupation_name, @RequestParam String description, @ModelAttribute("result") result result, Authentication authentication) {
        Occupation occupation = new Occupation(occupation_name, description);
        occupationRepository.save(occupation);
        int[] selectedIds = result.getSelectedIds();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        if (selectedIds != null || selectedIds.length != 0) {
            for (int adjId : selectedIds) {
                Adjective adjective = adjectiveRepository.findById(adjId).get();
                awaitingForCheckingByRepository.save(new AwaitingForCheckingBy(user, occupation, adjective));
            }
        }

//        temp temp;
//        if (selectedIds != null){
//            List<String> selectedNames = new ArrayList<>();
//            for (int id : selectedIds) {
//                adjective adjective = adjectiveRepository.findById(id)
//                        .orElseThrow(() -> new IllegalArgumentException("Invalid adjective id: " + id));
//                selectedNames.add(adjective.getTrait_name());
//                temp = new temp(id,adjective.getTrait_name());
//                tempRepj.save(temp);
//            }
//            // Дальнейшая обработка выбранных прилагательных
//        }
        return "redirect:/occupation";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);

        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        boolean test = authentication != null && authentication.isAuthenticated();
        model.addAttribute("test", test);

        return "profile";

    }

    @GetMapping("/occupation/{id}")
    public String details(Model model, @PathVariable(value = "id") int id, Authentication authentication) {
        Occupation occupation = occupationRepository.findById(id).get();
        model.addAttribute("occupation", occupation);
        boolean test = authentication != null && authentication.isAuthenticated();
        model.addAttribute("test", test);
        String forAdjectiveIds = "SELECT new com.example.Web.AdjectiveCount(adj.name, COUNT(eo.adjective.id)) " +
                "FROM ExpertsOpinion eo inner join Adjective adj on eo.adjective.id=adj.id " +
                "WHERE eo.occupation.id = :occupationId " +
                "GROUP BY eo.adjective.id " +
                "ORDER BY COUNT(eo.adjective.id) DESC";
        String forAllAnswers = "SELECT COUNT(*) FROM ExpertsOpinion eo WHERE eo.occupation.id = :occupationId";
        Long allAnswersCount = entityManager.createQuery(forAllAnswers, Long.class)
                .setParameter("occupationId", id)
                .getSingleResult();
        List<AdjectiveCount> adjectiveIds = entityManager.createQuery(forAdjectiveIds, AdjectiveCount.class)
                .setParameter("occupationId", id)
                .setMaxResults(10)
                .getResultList();
        model.addAttribute("adjectiveIds", adjectiveIds);
        model.addAttribute("allAnswersCount", allAnswersCount);
        if (allAnswersCount == 0) {
            model.addAttribute("message", "Данную профессию еще не оценил ни один эксперт. Станьте первым!");
        }
        return "occupation_details";

    }

//    @GetMapping("/test_result")
//    public String res() {
//        return "test_result";
//    }


//    @PostMapping("/test_result")
//    public String handleTestResult(@RequestParam("reactionTimes") String reactionTimesStr) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<Integer> reactionTimes = mapper.readValue(reactionTimesStr, new TypeReference<List<Integer>>() {
//        });
//        for (double res : reactionTimes) {
//            tempRepj.save(new temp(res));
//        }
//        return "redirect:/occupation";
//    }
}
