package com.example.Web.controllers;

import com.example.Web.models.AllTestsResult;
import com.example.Web.models.FinishedUserTests;
import com.example.Web.models.Tests;
import com.example.Web.models.temp;
import com.example.Web.repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class TestsController {
    @Autowired
    TestsRepo testsRepo;
    @Autowired
    tempRepj tempRepj;
    @Autowired
    FinishedUserTestsRepo finishedUserTestsRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AllTestsResultRepo allTestsResultRepo;

    @GetMapping("/tests")
    public String tests(Model model, Authentication authentication) {
        Iterable<Tests> tests = testsRepo.findAll();
        model.addAttribute("tests", tests);
        boolean test = authentication != null && authentication.isAuthenticated();
        model.addAttribute("test", test);
        return "Tests";
    }

    @GetMapping("/tests/test_{text}")
    public String test(@PathVariable(value = "text") String text) {
        return ("lab_2/test_" + text);
    }

    @GetMapping("/tests/test_{text}/test_result")
    public String res(@PathVariable String text) {
        return "test_result";
    }

    @PostMapping("/tests/test_{text}/test_result")
    public String handleTestResult(@RequestParam("answers") String answersSt, @PathVariable(value = "text") String text, Authentication authentication) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Double> answers = mapper.readValue(answersSt, new TypeReference<List<Double>>() {
        });
        FinishedUserTests finishedUserTests = new FinishedUserTests(userRepo.findByEmail(authentication.getName()), testsRepo.findByTestid(Integer.parseInt(text)));
        finishedUserTestsRepo.save(finishedUserTests);
        int session_id = finishedUserTests.getSession_id();

        for (Double res : answers) {
            allTestsResultRepo.save(new AllTestsResult(finishedUserTests,res));
        }
        return "redirect:/occupation";
    }

}
