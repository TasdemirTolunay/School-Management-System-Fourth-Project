package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO extends BaseEntity {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Instructor name is mandatory")
    private String instructorName;

    @NotBlank(message = "Instructor Phone Number is mandatory")
    private String instructorPhoneNumber;

    @NotNull(message = "Address Id is mandatory")
    private int addressId;

}
