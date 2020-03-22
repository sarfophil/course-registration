package com.group3.courseenrollment.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;

@Data
public class EnrollmentDto {
    @NonNull
    private Long sectionId;

    @NonNull
    @FutureOrPresent
    private LocalDate startDate;

    @NonNull
    @Future
    private LocalDate endDate;
}
