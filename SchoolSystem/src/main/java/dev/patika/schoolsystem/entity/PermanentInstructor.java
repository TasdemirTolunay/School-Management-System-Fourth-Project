package dev.patika.schoolsystem.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class PermanentInstructor extends Instructor {

    private double fixedSalary;

    @Builder
    public PermanentInstructor(String instructorName, String instructorPhoneNumber, double fixedSalary) {

        super(instructorName, instructorPhoneNumber);
        this.fixedSalary = fixedSalary;

    }

    public PermanentInstructor() {

    }

}
