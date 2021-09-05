package dev.patika.schoolsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponseDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    private String instructorName;

    private String instructorPhoneNumber;

    private String  addressCity;

    private List<String> instructorsCourseNames;

}
