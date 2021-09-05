package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.BaseEntity;
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
public class StudentDTO extends BaseEntity {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Student name is mandatory")
    @ApiModelProperty(example = "Tolunay TASDEMIR")
    private String studentName;

    @ApiModelProperty(example = "1996-02-12")
    private LocalDate studentBirthDate;

    @NotNull(message = "Gender is mandatory")
    @ApiModelProperty(example = "Male")
    private Gender studentGender;

    @NotNull(message = "Address Id is mandatory")
    private int addressId;

}
