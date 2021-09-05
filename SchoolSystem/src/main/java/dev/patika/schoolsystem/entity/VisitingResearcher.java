package dev.patika.schoolsystem.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcher extends Instructor {

    private double hourlySalary;

}
