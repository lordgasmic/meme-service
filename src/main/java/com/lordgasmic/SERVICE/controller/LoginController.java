package com.lordgasmic.==PACKAGE_NAME==.controller;

import com.lordgasmic.==PACKAGE_NAME==.model.User;
import com.lordgasmic.==PACKAGE_NAME==.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public String login(@Valid @RequestBody final User user) {

        return service.login(user) ? "derp" : "no derp";
    }

    @PutMapping("/add")
    public String add(@Valid @RequestBody final User user) {
        return service.addUser(user) ? "created" : "not created";
    }
}
