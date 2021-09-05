package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO extends BaseEntity {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Instructor name is mandatory")
    @ApiModelProperty(example = "Koray Guney")
    private String instructorName;

    @NotBlank(message = "Instructor Phone Number is mandatory")
    @ApiModelProperty(example = "+905555555555")
    private String instructorPhoneNumber;

    private int addressId;

}
