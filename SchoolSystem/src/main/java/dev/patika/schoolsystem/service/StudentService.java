package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.mapper.AddressMapper;
import dev.patika.schoolsystem.repository.AddressRepository;
import dev.patika.schoolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;


    public Address findAddressById(long addressId){

        Address foundAddress = addressRepository.findById(addressId).orElseThrow(() -> new IdNotFoundException(String.format("Address with ID: %d could not found!", addressId)));

        return foundAddress;

    }

    public Student findStudentById(long studentId){

        return studentRepository.findById(studentId).orElseThrow(() -> new IdNotFoundException(String.format("Student with ID: %d could not found!", studentId)));

    }

    public List<String> StudentsCourses(long studentId){

        List<String> courseNames = new ArrayList<>();
        List<Course> foundStudentsCourses = findStudentById(studentId).getCourses();
        for (Course c : foundStudentsCourses) {

            courseNames.add(c.getCourseName());

        }

        return courseNames;

    }

    public String  studentsAddressCity(long studentId){

        return findStudentById(studentId).getStudentAddress().getCity();

    }

}
