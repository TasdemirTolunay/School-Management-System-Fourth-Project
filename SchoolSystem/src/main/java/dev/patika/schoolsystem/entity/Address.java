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
@Builder
public class Address extends BaseEntity{

    //Variables
    private String country;
    private String city;
    private String plateCode;

    public Address(String country, String city, String plateCode) {
        this.country = country;
        this.city = city;
        this.plateCode = plateCode;
    }

    //Relations with other classes
    @JsonManagedReference
    @OneToMany(mappedBy = "address")
    private List<Student> studentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "address")
    private List<Instructor> instructorList = new ArrayList<>();

}
