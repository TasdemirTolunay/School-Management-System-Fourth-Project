package dev.patika.schoolsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcherDTO extends InstructorDTO{

    @NotNull(message = "Hourly Salary is mandatory")
    @ApiModelProperty(example = "150")
    private double hourlySalary;

}
