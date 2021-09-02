package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Instructor extends BaseEntity{

    //Variables
    private String instructorName;
    private String instructorPhoneNumber;

    //Relations with other classes
    @JsonBackReference
    @ManyToOne
    Address instructorAddress;

    @JsonManagedReference
    @OneToMany(mappedBy = "instructor")
    private List<Course> courseList = new ArrayList<>();

}
