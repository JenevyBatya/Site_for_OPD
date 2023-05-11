package com.example.Web.controllers;

import com.example.Web.AdjectiveCount;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Controller
public class ExpertController {

    @PersistenceContext
    private EntityManager entityManager;
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
    public String show(Model model,Authentication authentication) {
        int userId = userRepo.findByEmail(authentication.getName()).getId();
        String forChecked = "SELECT eo.occupation.id from ExpertsOpinion eo where eo.user.id = :userId";
        Query query = entityManager.createQuery(forChecked, Integer.class);
        query.setParameter("userId", userId);
        List<Integer> ratedOccupations = query.getResultList();
        model.addAttribute("ratedOccupations",ratedOccupations);
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
        if (allAnswersCount == 0){
            model.addAttribute("message","Данную профессию еще не оценил ни один эксперт. Станьте первым!");
        }
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
