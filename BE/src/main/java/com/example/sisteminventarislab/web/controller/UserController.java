package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.service.UserService;
import com.example.sisteminventarislab.web.model.CreateUpdateUserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(
        path = "/api/user",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("create new User")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody CreateUpdateUserRequest request) {
        return userService.createUser(request);
    }

    @ApiOperation("get all Users")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @ApiOperation("update User by id")
    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable String id, @RequestBody CreateUpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    @ApiOperation("delete User by id")
    @DeleteMapping(path = "/{id}")
    public boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
