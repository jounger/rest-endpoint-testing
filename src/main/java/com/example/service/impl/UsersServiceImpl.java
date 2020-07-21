package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.models.Users;
import com.example.repository.UsersRepository;
import com.example.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

  @Autowired
  private UsersRepository usersRepository;

  @Override
  public Long createUser(Users user) {
    // TODO Auto-generated method stub
    usersRepository.save(user);
    return user.getId();
  }

  @Override
  public Page<Users> getUsers(int page, int limit) {
    return usersRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  @Override
  public Users getUser(Long id) {
    return usersRepository.findById(id).orElseThrow(null);
  }
}
