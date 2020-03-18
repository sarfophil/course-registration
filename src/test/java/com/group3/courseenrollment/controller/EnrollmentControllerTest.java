package com.group3.courseenrollment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group3.courseenrollment.domain.*;
import com.group3.courseenrollment.dto.StudentEnrollmentDto;
import com.group3.courseenrollment.repository.SectionRepository;
import com.group3.courseenrollment.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@Slf4j
@SpringJUnitWebConfig
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnrollmentControllerTest {

    MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SectionRepository sectionRepository;

    private Long sectionId;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        persistData();
    }

    @Test
    public void getAllEnrollments() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/enrollments")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        if(mvcResult.getResponse().getStatus() == 404 || mvcResult.getResponse().getStatus() == 200){
            assertTrue(true);
        }else {
            fail();
        }
    }

    @Test
    public void addEnrollment() throws Exception {
        StudentEnrollmentDto studentEnrollmentDto = new StudentEnrollmentDto(2343L,sectionId,enrollmentList());
        // Converts StudentEnrollment to json
        String studentEnrollmentDtoToJson = requestJson(studentEnrollmentDto);
        mockMvc.perform(post("/enrollments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentEnrollmentDtoToJson))
                .andExpect(status().isCreated())
                .andReturn();
    }

    /**
     * Should return not found and Ok status
     * @throws Exception
     */
    @Test
    public void getEnrollment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/enrollments/2343")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        if(mvcResult.getResponse().getStatus() == 404 || mvcResult.getResponse().getStatus() == 200){
            assertTrue(true);
        }else {
            fail();
        }

    }


    private void persistData(){
        Student student = new Student(2343,"Philip","powusu@miu.edu",
                new Address("1000 North 4th Street","Fairfield","234","Country"),
                new Address("1000 North 4th Street","Fairfield","234","Country"));



        Section section = new Section(new Faculty("Computer Science Faculty","Compro"));

        studentService.addStudent(student);

        Section section1 = sectionRepository.save(section);
        sectionId = section1.getId();
    }

    private List<Enrollment> enrollmentList(){
        return Arrays.asList(
                new Enrollment(LocalDate.now(),LocalDate.now().plusDays(31)),
                new Enrollment(LocalDate.now().plusDays(31),LocalDate.now().plusDays(62)),
                new Enrollment(LocalDate.now().plusDays(93),LocalDate.now().plusDays(124)),
                new Enrollment(LocalDate.now().plusDays(155),LocalDate.now().plusDays(184)),
                new Enrollment(LocalDate.now().plusDays(300),LocalDate.now().plusDays(331))
        );
    }

    private Date temporalDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    private String requestJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(object);
    }
}