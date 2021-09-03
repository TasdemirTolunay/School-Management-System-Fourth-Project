package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.repository.CourseRepository;
import dev.patika.schoolsystem.repository.InstructorRepository;
import dev.patika.schoolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Course findCourseById(long courseId){

        return courseRepository.findById(courseId).orElseThrow(() -> new IdNotFoundException(String.format("Course with ID: %d could not found!", courseId)));

    }

    public Instructor findInstructorById(long instructorId){

        Instructor foundInstructor = instructorRepository.findById(instructorId).orElseThrow(() -> new IdNotFoundException(String.format("Instructor with ID: %d could not found!", instructorId)));
        return foundInstructor;

    }

    public int getNumberOfStudents(){

        return studentRepository.numberOfStudents();

    }

    public long instructorOfCourseId(long courseId){
        
        return findCourseById(courseId).getInstructor().getId();

    }

    public List<Student> findAllStudentsWithIds(List<Long> studensId){
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < studensId.size(); i ++) {

            int finalI = i;
            Student foundStudent = studentRepository.findById(studensId.get(i)).orElseThrow(() -> new IdNotFoundException(String.format("Student with ID: %d could not found!", studensId.get(finalI))));
            studentList.add(foundStudent);
        }
        return studentList;

    }

}
