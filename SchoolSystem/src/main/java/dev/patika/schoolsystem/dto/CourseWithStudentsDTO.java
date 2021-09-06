package dev.patika.schoolsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * An CourseWithStudents DTO class that will return as the request object of the course class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithStudentsDTO{

    /**
     * Variables to display as request body.
     */
    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Course name is mandatory")
    @ApiModelProperty(example = "Java Spring BootCamp")
    private String courseName;

    @NotBlank(message = "Course Code is mandatory")
    @ApiModelProperty(example = "J-00000113")
    private String courseCode;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @ApiModelProperty(example = "25")
    private double courseCreditScore;

    @NotNull(message = "Instructor id is mandatory")
    private long instructorId;

    @Size(max = 20)
    private List<Long> studentsId;

}
