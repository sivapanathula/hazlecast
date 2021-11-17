package com.cache.hazlecast.controller;

import com.cache.hazlecast.model.UserAccount;
import com.cache.hazlecast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({ "/get/{accountNumber}" })
    public UserAccount getUser(@PathVariable("accountNumber") String accountNumber) {
        return userService.findByAccountNumber(accountNumber);
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserAccount user) {
        userService.save(user);
    }

    @DeleteMapping({ "/{accountNumber}" })
    public UserAccount deleteUser(@PathVariable("accountNumber") String accountNumber) {
        return userService.deleteByAccountNumber(accountNumber);
    }

}
