package com.example.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.models.Users;
import com.example.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UsersController.class)
public class UsersControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  UsersService usersService;

  @Test
  void whenValidRegister_thenReturns200() throws Exception {
    Users user = new Users();
    user.setUsername("anvannguyen");
    user.setPassword("12346");
    user.setPhone("0967390001");
    user.setEmail("anvannguyen@gmail.com");
    user.setAddress("Ha Noi, Viet Nam");
    user.setStatus("OK");
    // MOCK: https://stackoverflow.com/a/37896584/10597062
    when(usersService.createUser(Mockito.any(Users.class))).thenReturn(1L);
    MvcResult mvcResult = mockMvc
        .perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.username").value("anvannguyen")).andReturn();
    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    user.setId(1L);
    String expectedResponseBody = objectMapper.writeValueAsString(user);
    System.out.println("actual: " + actualResponseBody);
    System.out.println("expected: " + expectedResponseBody);
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
  }
}
