package com.group3.courseenrollment.service.impl;

import com.group3.courseenrollment.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTest {

    @MockBean
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addEnrollment() {
       // given(studentService.addEnrollment();)
    }

    @Test
    public void loadEnrollmentByStudent() {
    }
}