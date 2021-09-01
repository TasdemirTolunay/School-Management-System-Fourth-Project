package dev.patika.schoolsystem.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PermanentInstructor extends Instructor {

    private double fixedSalary;


}
