package com.group3.courseenrollment.service.impl;

import com.group3.courseenrollment.domain.*;
import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;
import com.group3.courseenrollment.repository.SectionRepository;
import com.group3.courseenrollment.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.BDDMockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private SectionRepository sectionRepository;



    @Before
    public void setUp() throws Exception {

               Student student = new Student(2343,"Philip","powusu@miu.edu",
                        new Address("1000 North 4th Street","Fairfield","234","Country"),
                        new Address("1000 North 4th Street","Fairfield","234","Country"));

        studentService.addStudent(student);
        log.info("Added Student to DB");


    }

    private Section createSection(){
        Section section = new Section(new Faculty("Computer Science Faculty","Compro"));
        return sectionRepository.save(section);
    }

    @Test
    public void addEnrollment() {
        List<Enrollment> enrollments = Arrays.asList(
                new Enrollment(LocalDate.now(),LocalDate.now().plusDays(31)),
                new Enrollment(LocalDate.now().plusDays(31),LocalDate.now().plusDays(62)),
                new Enrollment(LocalDate.now().plusDays(93),LocalDate.now().plusDays(124)),
                new Enrollment(LocalDate.now().plusDays(155),LocalDate.now().plusDays(184)),
                new Enrollment(LocalDate.now().plusDays(300),LocalDate.now().plusDays(331))
        );
        Section section = createSection();

       doNothing().when(studentService).addEnrollment(343L,enrollments,section.getId());
    }

    private Date temporalDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    @Test
    public void loadEnrollmentByStudent() {
        List<Enrollment> enrollments = new ArrayList<>();
        given(studentService.loadEnrollmentByStudent(2343L)).willReturn(enrollments);
    }
}