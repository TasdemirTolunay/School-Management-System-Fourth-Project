package dev.patika.schoolsystem.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructor extends Instructor {

    private double fixedSalary;


}
