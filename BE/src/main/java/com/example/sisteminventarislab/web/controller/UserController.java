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

    /**
     * fungsi untuk mendapatkan list dari semua User.
     * fungsi ini akan menggunakan method getAllUser dari userService
     * dan mengembalikan hasilnya.
     *
     * @return List<User>, yaitu List dari semua User
     */
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

    /**
     * Method untuk deleteUser pada class controller UserController yang menerima id
     * dari data user yang ingin dihapus
     *
     * Method updateUser pada controller ini akan memanfaatkan implementasi dari
     * method deleteUser pada class interface service UserService, yang mana diimplementasi pada class
     * UserServiceImpl
     *
     * @param id (id dari user)
     * @return Boolean (true apabila data user berhasil dihapus, false apabila data user gagal dihapus)
     */
    @ApiOperation("delete User by id")
    @DeleteMapping(path = "/{id}")
    public boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
