package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.mapper.AddressMapper;
import dev.patika.schoolsystem.repository.AddressRepository;
import dev.patika.schoolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
