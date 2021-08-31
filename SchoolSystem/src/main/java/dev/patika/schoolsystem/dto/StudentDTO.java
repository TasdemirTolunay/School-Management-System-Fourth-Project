package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Student name is mandatory")
    private String studentName;


    private LocalDate studentBirthDate;

    @NotNull(message = "Gender is mandatory")
    private Gender studentGender;

    @NotNull(message = "Address Id is mandatory")
    private long addressId;


}
