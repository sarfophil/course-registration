package com.group3.courseenrollment.dto;

import com.group3.courseenrollment.domain.Faculty;
import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Data
public class SectionDto {
    @NonNull
    private List<Long> facultyList;

    @NonNull
    private Long offeringId;
}
