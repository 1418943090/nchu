//package com.love.nchu.controller;
//
//import javafx.application.Application;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RegistryControllerTest {
//
//    @Autowired
//    WebApplicationContext wac;
//   public MockMvc mcv;
//
//   @Before
//    public void setup()
//   {
//       MockMvcBuilders.webAppContextSetup(wac).build();
//   }
//
//   @Test
//    public void registry()throws Exception{
//
//       mcv.perform(MockMvcRequestBuilders.get("/registry_action")
//               .param("username","ro ot")
//               .contentType(MediaType.APPLICATION_JSON_UTF8))
//               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
//   }
//
//
//
//
//}
