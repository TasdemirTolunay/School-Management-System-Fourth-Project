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
@EqualsAndHashCode(callSuper = true)
public class Course extends  BaseEntity{

    //Variables
    private String courseName;
    private String courseCode;
    private double courseCreditScore;

    //Relations with other classes
    @JsonBackReference
    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Instructor instructor;

}
