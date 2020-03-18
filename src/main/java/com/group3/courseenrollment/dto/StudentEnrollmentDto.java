package com.group3.courseenrollment.dto;

import com.group3.courseenrollment.domain.Enrollment;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Max;
import java.util.List;

@Data
public class StudentEnrollmentDto {
    @NonNull
    private Long studentId;
    @NonNull
    private Long sectionId;

    @NonNull
    private List<Enrollment> enrollments;

    public StudentEnrollmentDto(Long studentId,Long sectionId,List<Enrollment> enrollments){
        this.studentId = studentId;
        this.sectionId = sectionId;
        this.enrollments = enrollments;
    }
}
