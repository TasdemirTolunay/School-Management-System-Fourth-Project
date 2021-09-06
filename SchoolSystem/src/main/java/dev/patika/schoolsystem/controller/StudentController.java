package dev.patika.schoolsystem.controller;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * It is a control class that enables operations within the service class to be performed using get, post, delete and put mapping on the web base.
 */
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentWithCoursesDTO>> findAllList(){

        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);

    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentWithCoursesDTO> findStudentById(@PathVariable long studentId){

        return new ResponseEntity<>(studentService.findByStudentId(studentId),HttpStatus.OK);

    }

    @GetMapping("/studentName/{studentName}")
    public ResponseEntity<List<StudentWithCoursesDTO>> findStudentByStudentName(@PathVariable String studentName){

        return new ResponseEntity<>(studentService.findStudentByName(studentName),HttpStatus.OK);

    }

    @GetMapping("/genders/group")
    public ResponseEntity<List<?>> getGendersWithGroup(){

        return new ResponseEntity<>(studentService.genderGroups(),HttpStatus.OK);

    }

    @GetMapping("/courses/{studentId}")
    public ResponseEntity<List<CourseDTO>> getCoursesOfStudents(@PathVariable long studentId){

        return new ResponseEntity<>(studentService.findCoursesOfStudent(studentId),HttpStatus.OK);

    }

    @GetMapping("/address/{studentId}")
    public ResponseEntity<AddressDTO> getAddressOfStudent(@PathVariable long studentId){

        return new ResponseEntity<>(studentService.findAddressOfStudent(studentId),HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<StudentWithCoursesDTO> saveStudent(@RequestBody StudentDTO studentDTO){

        return new ResponseEntity<>(studentService.saveStudent(studentDTO),HttpStatus.OK);

    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<StudentWithCoursesDTO> updateStudent(@RequestBody StudentDTO studentDTO,@PathVariable long studentId){

        return new ResponseEntity<>(studentService.updateStudent(studentDTO,studentId),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudentWithId(@PathVariable long studentId){

        return new ResponseEntity<>(studentService.deleteStudentById(studentId),HttpStatus.OK);

    }

}
