package dev.patika.schoolsystem.controller;

import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.CourseWithStudentsDTO;
import dev.patika.schoolsystem.dto.InstructorDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<InstructorDTO> getInstructorOfCourse(@PathVariable long courseId){

        return new ResponseEntity<>(courseService.findInstructorOfCourse(courseId),HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseWithStudentsDTO courseWithStudentsDTO){

        return new ResponseEntity<>(courseService.saveCourse(courseWithStudentsDTO),HttpStatus.OK);

    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO,@PathVariable long courseId){

        return new ResponseEntity<>(courseService.updateCourse(courseDTO,courseId),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourseWithId(@PathVariable long courseId){

        return new ResponseEntity<>(courseService.deleteCourseById(courseId),HttpStatus.OK);

    }

    @DeleteMapping("/deleteWithBody")
    public ResponseEntity<String> deleteCourseWithBody(@RequestBody CourseDTO courseDTO){

        return new ResponseEntity<>(courseService.deleteCourseByObject(courseDTO),HttpStatus.OK);

    }

}
