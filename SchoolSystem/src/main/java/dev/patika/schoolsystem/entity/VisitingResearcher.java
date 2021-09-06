package dev.patika.schoolsystem.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * VisitingResearcher is the type of instructor that receives a hourly salary.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcher extends Instructor {

    private double hourlySalary;

}