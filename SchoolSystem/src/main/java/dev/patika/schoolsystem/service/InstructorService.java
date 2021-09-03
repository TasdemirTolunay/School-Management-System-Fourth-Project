package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.InstructorDTO;
import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.mapper.InstructorMapper;
import dev.patika.schoolsystem.repository.AddressRepository;
import dev.patika.schoolsystem.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AddressRepository addressRepository;



    public Address findAddressById(long addressId){

        Address foundAddress = addressRepository.findById(addressId).orElseThrow(() -> new IdNotFoundException(String.format("Address with ID: %d could not found!", addressId)));
        return foundAddress;

    }

    public Instructor findInstructorById(long instructorId){

        Instructor foundInstructor = instructorRepository.findById(instructorId).orElseThrow(() -> new IdNotFoundException(String.format("Instructor with ID: %d could not found!", instructorId)));
        return foundInstructor;

    }

    public String instructorsAddressCity(long instructorId){

        return findInstructorById(instructorId).getInstructorAddress().getCity();

    }

    public List<String> instructorsCourseNames(long instructorId){

        List<String> courseNames = new ArrayList<>();
        List<Course> foundCourses = findInstructorById(instructorId).getCourseList();

        for (Course c : foundCourses) {

            courseNames.add(c.getCourseName());

        }

        return courseNames;

    }

}
