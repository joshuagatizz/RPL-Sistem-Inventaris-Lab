package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Response;
import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.entity.helper.ResponseHelper;
import com.example.sisteminventarislab.service.UserService;
import com.example.sisteminventarislab.web.model.Request.CreateUpdateUserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
@RequestMapping(
        path = "/api/user",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    /**
     * method ini memanfaatkan method create new user pada class controller UserController,
     * digunakan untuk membuat user baru dengan menerima request berupa
     * user baru yang akan dibuat.
     *
     *
     *
     * @param request
     * @return
     */
    @ApiOperation("create new User")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<User> createUser(@RequestBody @Valid CreateUpdateUserRequest request) {
        return ResponseHelper.ok(userService.createUser(request));
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
    public Response<List<User>> getAllUsers() {
        return ResponseHelper.ok(userService.getAllUser());
    }

    /**
     * method update user by id pada class controller UserController ini
     * digunakan untuk mengupdate user.
     *
     * method updateUser pada controller ini akan memanfaatkan
     * implementasi dari method update user by id.
     *
     *
     * @param id
     * @param request
     * @return
     */
    @ApiOperation("update User by id")
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<User> updateUser(@PathVariable String id, @RequestBody @Valid CreateUpdateUserRequest request) {
        return ResponseHelper.ok(userService.updateUser(id, request));
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
    public Response<Boolean> deleteUser(@PathVariable String id) {
        return ResponseHelper.ok(userService.deleteUser(id));
    }
}