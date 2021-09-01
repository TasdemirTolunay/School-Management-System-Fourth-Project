package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.exceptions.EmptyListException;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAllAddress(){

        List<Address> addressList = new ArrayList<>();
        Iterable<Address> iteAddress = addressRepository.findAll();
        iteAddress.iterator().forEachRemaining(addressList :: add);
        if(addressList.isEmpty()){
            throw new EmptyListException("List is empty.....");
        }
        return addressList;

    }

    public Address findAddressById(long addressId){

        Address foundAddress = addressRepository.findById(addressId).orElseThrow(() -> new IdNotFoundException(String.format("Address with ID: %d could not found!", addressId)));

        return foundAddress;

    }


}
