package com.example.Web.controllers;

import com.example.Web.models.*;
import com.example.Web.repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate jdbcTemplate;
    @Autowired
    TestsRepo testsRepo;
//    @Autowired
//    tempRepj tempRepj;
    @Autowired
    FinishedSessionUserTestRepo finishedSessionUserTestRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AllTestsResultRepo allTestsResultRepo;

    @GetMapping("/tests")
    public String tests(Model model, Authentication authentication) {
//        String sql = "SELECT trait_name FROM adjective";
//        List<String> items = jdbcTemplate.queryForList(sql,String.class);
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
        FinishedSessionUserTest finishedSessionUserTest = new FinishedSessionUserTest(userRepo.findByEmail(authentication.getName()), testsRepo.findById(Integer.parseInt(text)).get());
        finishedSessionUserTestRepo.save(finishedSessionUserTest);
        int session_id = finishedSessionUserTest.getId();

        for (Double res : answers) {
            allTestsResultRepo.save(new AllTestsResult(finishedSessionUserTest,res));
        }
        return "redirect:/occupation";
    }

}
