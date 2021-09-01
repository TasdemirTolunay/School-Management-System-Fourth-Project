package dev.patika.schoolsystem.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity{

    //Variables
    private String studentName;
    private LocalDate studentBirthDate;
    @Enumerated(EnumType.STRING)
    private Gender studentGender;

    //Relations with other classes
    @JsonManagedReference
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Address address;

}
