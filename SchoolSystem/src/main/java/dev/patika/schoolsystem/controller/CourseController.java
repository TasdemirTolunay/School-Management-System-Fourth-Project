package dev.patika.schoolsystem.controller;

import dev.patika.schoolsystem.dto.*;
import dev.patika.schoolsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * It is a control class that enables operations within the service class to be performed using get, post, delete and put mapping on the web base.
 */
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> findAllList(){

        return new ResponseEntity<>(courseService.findAllCourses(), HttpStatus.OK);

    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> findCourseById(@PathVariable long courseId){

        return new ResponseEntity<>(courseService.findByCourseId(courseId),HttpStatus.OK);

    }

    @GetMapping("/courseName/{courseName}")
    public ResponseEntity<List<CourseDTO>> findCourseByCourseName(@PathVariable String courseName){

        return new ResponseEntity<>(courseService.findCourseByName(courseName),HttpStatus.OK);

    }

    @GetMapping("/students/{courseId}")
    public ResponseEntity<List<StudentWithCoursesDTO>> getStudentsOfCourse(@PathVariable long courseId){

        return new ResponseEntity<>(courseService.findStudentsOfCourse(courseId),HttpStatus.OK);

    }

    @GetMapping("/instructor/{courseId}")
    public ResponseEntity<InstructorResponseDTO> getInstructorOfCourse(@PathVariable long courseId){

        return new ResponseEntity<>(courseService.findInstructorOfCourse(courseId),HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseWithStudentsDTO courseWithStudentsDTO){

        return new ResponseEntity<>(courseService.saveCourse(courseWithStudentsDTO),HttpStatus.OK);

    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseWithStudentsDTO courseWithStudentsDTO,@PathVariable long courseId){

        return new ResponseEntity<>(courseService.updateCourse(courseWithStudentsDTO,courseId),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourseWithId(@PathVariable long courseId){

        return new ResponseEntity<>(courseService.deleteCourseById(courseId),HttpStatus.OK);

    }

}
