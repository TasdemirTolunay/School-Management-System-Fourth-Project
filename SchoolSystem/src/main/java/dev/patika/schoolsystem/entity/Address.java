package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a address enrolled in the student and instructor.
 * An address can be enrolled in many students.
 * An address can be enrolled in many instructors.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity{

    /**
     * This address's variables.
     */
    private String country;
    private String city;
    private String plateCode;

    /**
     * Defines the relation of the address with the student and the instructor.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "studentAddress")
    private List<Student> studentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "instructorAddress")
    private List<Instructor> instructorList = new ArrayList<>();

}