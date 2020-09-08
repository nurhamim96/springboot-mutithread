package com.example.springbootmultithreading.api.controller;

import com.example.springbootmultithreading.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String USER_ADDR = "/users";

    @PostMapping(value = USER_ADDR, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity userSave(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            userService.saveUser(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = USER_ADDR, produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllUsers() {
       return userService.findAllUsers().thenApply(ResponseEntity::ok);
    }
}
