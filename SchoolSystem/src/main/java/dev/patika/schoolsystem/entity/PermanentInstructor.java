package dev.patika.schoolsystem.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * PermanentInstructor is the type of instructor that receives a fixed salary.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructor extends Instructor {

    private double fixedSalary;

}