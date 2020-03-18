package com.group3.courseenrollment.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group3.courseenrollment.domain.Course;
import com.group3.courseenrollment.service.CourseService;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringJUnitWebConfig
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseControllerTest {
    MockMvc mockMvc;
    
    @MockBean
    private CourseService courseService;
       
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        persistData();
    }

    @Test
    public void getAllCourses() throws Exception {
    	MvcResult mvcResult = mockMvc.perform(get("/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        if(mvcResult.getResponse().getStatus() == 404 || mvcResult.getResponse().getStatus() == 200){
            assertTrue(true);
        }else {
            fail();
        }
    }

    @Test
    public void addCourse() throws Exception {
    	Course course = new Course("cs544", "EA", "EA");
        // Converts StudentEnrollment to json
        String courseToJson = requestJson(course);
        mockMvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseToJson))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void getCourse() throws Exception {
    	MvcResult mvcResult = mockMvc.perform(get("/courses/cs544")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        if(mvcResult.getResponse().getStatus() == 404 || mvcResult.getResponse().getStatus() == 200){
            assertTrue(true);
        }else {
            fail();
        }

    }

    @Test
    public void update() throws JsonProcessingException, Exception {
    		mockMvc.perform(put("/courses/{id}","cs544")
    		      .content(requestJson(new Course("cs544", "EA1", "EA1")))
    		      .contentType(MediaType.APPLICATION_JSON)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("cs544"))	
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("EA1"))
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("EA1"));
    }

    @Test
    public void deleteCourse() throws Exception {
    	mockMvc.perform( MockMvcRequestBuilders.delete("/courses/{id}", "cs435"))
    			.andExpect(status().isAccepted()); 
    }
    
    private void persistData(){
        Course course = new Course("cs544","EA","EA...");
        Course course1 = new Course("cs435","Algorithms","Algorithms...");
        courseService.addCourse(course);
        courseService.addCourse(course1);

    }
    
    private String requestJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(object);
    }
}