package dev.patika.schoolsystem.controller;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.InstructorResponseDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * It is a control class that enables operations within the service class to be performed using get, post, delete and put mapping on the web base.
 */
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/list")
    public ResponseEntity<List<AddressDTO>> findAllList(){

        return new ResponseEntity<>(addressService.findAllAddress(), HttpStatus.OK);

    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> findAddressById(@PathVariable long addressId){

        return new ResponseEntity<>(addressService.findByAddressId(addressId),HttpStatus.OK);

    }

    @GetMapping("/students/{addressId}")
    public ResponseEntity<List<StudentWithCoursesDTO>> findStudentsOfAddress(@PathVariable long addressId){

        return new ResponseEntity<>(addressService.getStudentsOfAddress(addressId),HttpStatus.OK);

    }

    @GetMapping("/instructors/{addressId}")
    public ResponseEntity<List<InstructorResponseDTO>> findInstructorsOfAddress(@PathVariable long addressId){

        return new ResponseEntity<>(addressService.getInstructorsOfAddress(addressId),HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO){

        return new ResponseEntity<>(addressService.saveAddress(addressDTO),HttpStatus.OK);

    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO,@PathVariable long addressId){

         return new ResponseEntity<>(addressService.updateAddress(addressDTO,addressId),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<String> deleteAddressWithId(@PathVariable long addressId){

        return new ResponseEntity<>(addressService.deleteAddressById(addressId),HttpStatus.OK);

    }

}