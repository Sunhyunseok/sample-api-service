package com.sk.jdp.common.sample.user.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.jdp.common.core.controller.BaseController;
import com.sk.jdp.common.core.model.response.PaginatedResponse;
import com.sk.jdp.common.sample.user.model.User;
import com.sk.jdp.common.sample.user.model.UserSearch;
import com.sk.jdp.common.sample.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable @NotNull int id) {

        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/user")
    public int createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @PutMapping("/user")
    public int editUserEmail(@RequestBody @Valid User user) {
        return userService.editUserEmail(user);
    }

    @DeleteMapping("/user/{id}")
    public int removeUser(@PathVariable @NotNull int id) {
        return userService.removeUser(id);
    }

    @GetMapping("/remote/user")
    public List<User> getRemoteUser() {
        return userService.getRemoteUser();
    }

    @GetMapping("/user")
    public PaginatedResponse<User> getUserList(@ModelAttribute UserSearch search){
    	log.debug("search obj = {}", search);
    	return userService.getUserList(search);
    }

}
