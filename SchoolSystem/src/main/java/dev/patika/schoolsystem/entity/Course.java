package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course enrolled in the school.
 * Many students can enroll in a course.
 * A course could be instructed by only one instructor.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Course extends  BaseEntity{

    /**
     * This course's variables.
     */
    private String courseName;
    private String courseCode;
    private double courseCreditScore;

    /**
     * Defines the relation of the course with the student and the instructor.
     */
    @JsonBackReference
    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Instructor instructor;

}