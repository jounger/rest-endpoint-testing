package com.example.service;

import org.springframework.data.domain.Page;

import com.example.models.Users;

public interface UsersService {

  Long createUser(Users user);

  Page<Users> getUsers(int page, int limit);
  
  Users getUser(Long id);

}
