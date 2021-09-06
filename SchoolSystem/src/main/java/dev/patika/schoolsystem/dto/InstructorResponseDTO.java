package dev.patika.schoolsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  An InstructorResponse DTO class that will return as the response object of the instructor class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponseDTO {

    /**
     * Variables to display as response body.
     */
    @ApiModelProperty(hidden = true)
    private long id;

    private String instructorName;

    private String instructorPhoneNumber;

    private String  addressCity;

    private List<String> instructorsCourseNames;

}
