package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
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

    public Instructor(String instructorName, String instructorPhoneNumber) {
        this.instructorName = instructorName;
        this.instructorPhoneNumber = instructorPhoneNumber;
    }

    //Relations with other classes
    @JsonBackReference
    @ManyToOne
    private Address address;

    @JsonManagedReference
    @OneToMany(mappedBy = "instructor")
    private List<Course> courseList = new ArrayList<>();

}
