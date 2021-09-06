package dev.patika.schoolsystem.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student enrolled in the school.
 * A student can be enrolled in many courses.
 * A student can be enrolled to one address.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity{

    /**
     * This student's variables.
     */
    private String studentName;
    private LocalDate studentBirthDate;
    @Enumerated(EnumType.STRING)
    private Gender studentGender;

    /**
     * Defines the relation of the student with the address and the course.
     */
    @JsonManagedReference
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    Address studentAddress;

}