package com.example.Web.controllers;

import com.example.Web.models.Role;
import com.example.Web.models.User;
import com.example.Web.models.UserRole;
import com.example.Web.repo.UserRepo;
import com.example.Web.repo.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.Web.repo.OccupationRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private OccupationRepository occupationRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserRoleRepo userRoleRepo;

    //    @GetMapping("/occupation")
//    public String occupation(Model model) {
//        model.addAttribute("title", "Login page");
//        Iterable<occupation> occupations = occupationRepository.findAll();
//        model.addAttribute("occupations", occupations);
//        return "Occupation";
//    }
    @GetMapping("/test_1")
    public String test(Model model) {
        model.addAttribute("title", "test");
        return "lab_2/test_1";
    }
    @GetMapping("/test_2")
    public String test2(Model model) {
        model.addAttribute("title", "test");
        return "test_2";
    }
    @GetMapping("/test_3")
    public String test3(Model model) {
        model.addAttribute("title", "test");
        return "test_3";
    }

    @GetMapping("/registration")
    public String registaration() {
        return "registration";

    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, Map<String,Object> model1) {
        // Проверка, что email не пустой и не зарегистрирован в базе данных
        if (userForm.getEmail() == null || userRepo.findByEmail(userForm.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.userForm", "This email is already registered");
        }

        // Если есть ошибки, вернуть форму регистрации с ошибками
        if (bindingResult.hasErrors()) {
            model.addAttribute("message","This email is already registered");
            model1.put("message","This email is already registered");
            return "registration";
        }

        // Сохранение пользователя в базе данных
        userRepo.save(userForm);
        userRoleRepo.save(new UserRole(userForm,Role.USER));

        // Перенаправление на страницу успешной регистрации
        return "redirect:/login";
    }

//        User userFromDB = userRepo.findByEmail(user.getEmail());
//        if(userFromDB!=null){
//            model.put("message","Пользователь с данной почтой уже зарегистрирован");
//            return "registration";
//        }else{
//            user.setActive(true);
//
//            userRepo.save(user);
//            userRoleRepo.save(new UserRole(user,Role.USER));
//            return "redirect: /login";

}



