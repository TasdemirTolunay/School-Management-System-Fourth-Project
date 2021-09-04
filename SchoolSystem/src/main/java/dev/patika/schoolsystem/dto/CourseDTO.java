package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO extends BaseEntity {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Course name is mandatory")
    private String courseName;

    @NotBlank(message = "Course Code is mandatory")
    private String courseCode;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double courseCreditScore;

    private String  instructorName;

    private int numberOfStudents;

}
