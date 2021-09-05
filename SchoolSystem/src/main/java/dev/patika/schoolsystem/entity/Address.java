package dev.patika.schoolsystem.entity;

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
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity{

    //Variables
    private String country;
    private String city;
    private String plateCode;

    //Relations with other classes
    @JsonManagedReference
    @OneToMany(mappedBy = "studentAddress")
    private List<Student> studentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "instructorAddress")
    private List<Instructor> instructorList = new ArrayList<>();

}
