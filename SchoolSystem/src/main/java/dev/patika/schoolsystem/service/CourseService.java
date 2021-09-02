package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.repository.CourseRepository;
import dev.patika.schoolsystem.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor findInstructorById(long instructorId){

        Instructor foundInstructor = instructorRepository.findById(instructorId).orElseThrow(() -> new IdNotFoundException(String.format("Instructor with ID: %d could not found!", instructorId)));
        return foundInstructor;

    }

}
