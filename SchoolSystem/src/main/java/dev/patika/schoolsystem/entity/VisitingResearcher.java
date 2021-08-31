package dev.patika.schoolsystem.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class VisitingResearcher extends Instructor {

    private double hourlySalary;

    @Builder
    public VisitingResearcher(String instructorName, String instructorPhoneNumber, double hourlySalary) {

        super(instructorName, instructorPhoneNumber);
        this.hourlySalary = hourlySalary;

    }

    public VisitingResearcher() {

    }

}
