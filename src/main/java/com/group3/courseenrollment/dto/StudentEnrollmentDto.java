package com.group3.courseenrollment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group3.courseenrollment.domain.Enrollment;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Max;
import java.util.List;

@Data
public class StudentEnrollmentDto {

    @NonNull
    private List<Long> enrollments;

    public StudentEnrollmentDto(@JsonProperty("enrollments") List<Long> enrollments){
        this.enrollments = enrollments;
    }


}
