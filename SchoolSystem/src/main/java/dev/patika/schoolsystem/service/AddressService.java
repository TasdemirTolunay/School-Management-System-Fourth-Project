package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.EmptyListException;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.mapper.AddressMapper;
import dev.patika.schoolsystem.mapper.StudentWithCoursesMapper;
import dev.patika.schoolsystem.repository.AddressRepository;
import dev.patika.schoolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private StudentWithCoursesMapper studentWithCoursesMapper;


    public List<AddressDTO> findAllAddress(){

        List<Address> addressList = new ArrayList<>();
        Iterable<Address> iteAddress = addressRepository.findAll();
        iteAddress.iterator().forEachRemaining(addressList :: add);
        List<AddressDTO> addressDTOList = addressMapper.mapAddressListToAddressDTOList(addressList);
        if(addressDTOList.isEmpty()){
            throw new EmptyListException("List is empty.....");
        }
        return addressDTOList;

    }

    public AddressDTO findByAddressId(long addressId){

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Address with ID: %d could not found!", addressId)));
        return addressMapper.mapAddressToAddressDTO(address);

    }

    @Transactional
    public AddressDTO saveAddress(AddressDTO addressDTO){

        Address address = addressMapper.mapAddressDTOToAddress(addressDTO);
        return addressMapper.mapAddressToAddressDTO(addressRepository.save(address));

    }

    @Transactional
    public AddressDTO updateAddress(AddressDTO addressDTO, long addressId){

        Address foundAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Address with ID: %d could not found!", addressId)));
        foundAddress.setCity(addressDTO.getCity());
        foundAddress.setCountry(addressDTO.getCountry());
        foundAddress.setPlateCode(addressDTO.getPlateCode());
        addressRepository.save(foundAddress);
        return addressMapper.mapAddressToAddressDTO(foundAddress);


    }

    @Transactional
    public String deleteAddressById(long addressId){

        List<Address> addressList = new ArrayList<>();
        Iterable<Address> iteAddress = addressRepository.findAll();
        iteAddress.iterator().forEachRemaining(addressList :: add);
        if(addressList.isEmpty()){

            throw new EmptyListException("List is Empty!!!");

        }
        addressList.remove(addressRepository.findById(addressId).get());
        addressRepository.deleteById(addressId);
        return "Address id = " + addressId + " Deleted....";

    }

    @Transactional
    public String deleteAddressByObject(AddressDTO addressDTO){

        Address foundAddress = addressMapper.mapAddressDTOToAddress(addressDTO);
        addressRepository.delete(foundAddress);
        return "Address Deleted......";

    }


    public int numberOfStudents(){

        return studentRepository.numberOfStudents();

    }

    public Address findAddressById(long addressId){

        return addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Address with ID: %d could not found!", addressId)));

    }



}
