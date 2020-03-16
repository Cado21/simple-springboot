//package com.fresh.restapi.controller;
//
//import com.fresh.restapi.model.user.User;
//import com.fresh.restapi.dto.request.UserRequest;
//import com.fresh.restapi.response.UserResponse;
//import com.fresh.restapi.response.UsersResponse;
//import com.fresh.restapi.services.User.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping
//public class  Controller {
//    @Autowired
//    UserService userService;
//
//    @GetMapping(value = "/hello-world")
//    public String helloWorld() {
//        return "Hello World";
//    }
//
//    @GetMapping(value = "/user")
//    public UserResponse getUserById(@RequestParam() Integer id) {
//        User user = userService.getUserById(id);
//        String message = ("success user with id: " + id);
//        return new UserResponse(user, message, 200);
//    }
//    @GetMapping(value = "/users")
//    public UsersResponse getUsersBySearchName(@RequestParam(required = false) String name){
//        List<User> users = name != null ? userService.findUserByQueryName(name) : userService.getAllUsers();
//        String message =  "success get all users where name include : \"" + name + "\"" ;
//
//        return new UsersResponse(users, message , 200);
//    }
//
//    @PostMapping(value = "/user")
//    public UserResponse createNewUser(@RequestBody UserRequest req) {
//        String name = req.getName();
//        User savedUser = userService.createNewUser(name);
//        Integer userId = savedUser.getId();
//        User user = userService.getUserById(userId);
//        return new UserResponse(user, "success creating new user", 201);
//    }
//
//    @PutMapping(value = "/user")
//    public UserResponse updateUserById(@RequestParam() Integer id ,@RequestBody UserRequest req) {
//        String name = req.getName();
//        User updatedUser = userService.updateUserById(id, name); // ini biasanya kalau mw bkin seperti payload bkin class lagi?
//        String message = "success update user with id: " + id;
//        return new UserResponse(updatedUser, message, 200);
//    }
//
//    @DeleteMapping(value = "/user")
//    public UserResponse deleteUserById(@RequestParam() Integer id) {
//        User deletedUser = userService.deleteUserById(id);
//        String message = "success update user with id: " + id;
//        return new UserResponse(deletedUser, message, 200);
//    }
//}
