package dev.patika.schoolsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructorDTO extends InstructorDTO{

    @NotNull(message = "Fixed Salary is mandatory")
    @ApiModelProperty(example = "9000")
    private double fixedSalary;

}
