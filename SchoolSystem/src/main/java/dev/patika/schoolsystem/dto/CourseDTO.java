package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

/**
 * An Course DTO class that will return as the response object of the course class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO extends BaseEntity {

    /**
     * Variables to display as response body.
     */
    @ApiModelProperty(hidden = true)
    private long id;
    private String courseName;
    private String courseCode;
    private double courseCreditScore;
    private String  instructorName;
    private int numberOfStudents;

}