package dev.patika.schoolsystem.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcherDTO extends InstructorDTO{

    @NotNull(message = "Hourly Salary is mandatory")
    @Min(0)
    private double hourlySalary;

}
