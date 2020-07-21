package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Users;
import com.example.service.UsersService;

@RestController
@RequestMapping("/api")
public class UsersController {

  @Autowired
  private UsersService usersService;

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody Users request) {
    System.out.println("user: " + request.toString());
    Long id = usersService.createUser(request);
    request.setId(id);
    return ResponseEntity.ok(request);
  }
  
  @GetMapping("/user/{id}")
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    Users user = usersService.getUser(id);
    return ResponseEntity.ok(user);
  }

}
