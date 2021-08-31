package dev.patika.schoolsystem.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student extends BaseEntity{

    //Variables
    private String studentName;
    private LocalDate studentBirthDate;
    @Enumerated(EnumType.STRING)
    private Gender studentGender;

    public Student(String studentName, LocalDate studentBirthDate, Gender studentGender) {
        this.studentName = studentName;
        this.studentBirthDate = studentBirthDate;
        this.studentGender = studentGender;
    }

    //Relations with other classes
    @JsonManagedReference
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Address address;

}
