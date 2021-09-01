package dev.patika.schoolsystem.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructorDTO extends InstructorDTO{

    @NotNull(message = "Fixed Salary is mandatory")
    @Min(0)
    private double fixedSalary;

}
