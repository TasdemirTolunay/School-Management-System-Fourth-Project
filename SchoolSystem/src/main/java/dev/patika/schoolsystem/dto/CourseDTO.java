package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Course name is mandatory")
    private String courseName;

    @NotBlank(message = "Course Code is mandatory")
    private String courseCode;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double courseCreditScore;

    @NotNull(message = "Instructor id is mandatory")
    private long instructorId;

    @Size(max = 20)
    private List<Student> students;

}
