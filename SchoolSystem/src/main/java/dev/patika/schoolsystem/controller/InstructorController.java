package dev.patika.schoolsystem.controller;

import dev.patika.schoolsystem.dto.*;
import dev.patika.schoolsystem.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * It is a control class that enables operations within the service class to be performed using get, post, delete and put mapping on the web base.
 */
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/list")
    public ResponseEntity<List<InstructorResponseDTO>> findAllList(){

        return new ResponseEntity<>(instructorService.findAllInstructor(), HttpStatus.OK);

    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<InstructorResponseDTO> findInstructorById(@PathVariable long instructorId){

        return new ResponseEntity<>(instructorService.findByInstructorId(instructorId),HttpStatus.OK);

    }

    @GetMapping("/instructorName/{instructorName}")
    public ResponseEntity<List<InstructorResponseDTO>> findInstructorByInstructorName(@PathVariable String instructorName){

        return new ResponseEntity<>(instructorService.findInstructorByName(instructorName),HttpStatus.OK);

    }

    @GetMapping("/courses/{instructorId}")
    public ResponseEntity<List<CourseDTO>> findCoursesOfInstructor(@PathVariable long instructorId){

        return new ResponseEntity<>(instructorService.getCoursesOfInstructor(instructorId),HttpStatus.OK);

    }

    @GetMapping("/address/{instructorId}")
    public ResponseEntity<AddressDTO> findAddressOfInstructor(@PathVariable long instructorId){

        return new ResponseEntity<>(instructorService.getAddressOfInstructor(instructorId),HttpStatus.OK);

    }

    @PostMapping("/savePermanent")
    public ResponseEntity<InstructorResponseDTO> savePermanentInstructor(@RequestBody PermanentInstructorDTO permanentInstructorDTO){

        return new ResponseEntity<>(instructorService.savePermanentInstructor(permanentInstructorDTO),HttpStatus.OK);

    }

    @PostMapping("/saveVisiting")
    public ResponseEntity<InstructorResponseDTO> saveVisitingInstructor(@RequestBody VisitingResearcherDTO visitingResearcherDTO){

        return new ResponseEntity<>(instructorService.saveVisitingResearcherInstructor(visitingResearcherDTO),HttpStatus.OK);

    }

    @PutMapping("/update/{instructorId}")
    public ResponseEntity<InstructorResponseDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable long instructorId){

        return new ResponseEntity<>(instructorService.updateInstructor(instructorDTO,instructorId),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{instructorId}")
    public ResponseEntity<String> deleteInstructorWithId(@PathVariable long instructorId){

        return new ResponseEntity<>(instructorService.deleteInstructorById(instructorId),HttpStatus.OK);

    }

}
