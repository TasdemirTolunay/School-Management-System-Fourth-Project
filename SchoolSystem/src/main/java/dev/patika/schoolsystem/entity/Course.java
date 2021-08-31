package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course extends  BaseEntity{

    //Variables
    private String courseName;
    private String courseCode;
    private double courseCreditScore;

    public Course(String courseName, String courseCode, double courseCreditScore) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCreditScore = courseCreditScore;
    }

    //Relations with other classes
    @JsonBackReference
    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Instructor instructor;

}
