package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 *  An StudentWithCourses DTO class that will return as the response object of the student class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithCoursesDTO {

    /**
     * Variables to display as response body.
     */
    @ApiModelProperty(hidden = true)
    private long id;

    private String studentName;

    private LocalDate studentBirthDate;

    private Gender studentGender;

    private String  addressCity;

    private List<String> studentsCourses;

}
