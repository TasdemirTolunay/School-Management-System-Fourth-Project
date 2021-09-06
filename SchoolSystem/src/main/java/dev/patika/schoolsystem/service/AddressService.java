package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.InstructorResponseDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.EmptyListException;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.mapper.AddressMapper;
import dev.patika.schoolsystem.mapper.InstructorResponseMapper;
import dev.patika.schoolsystem.mapper.StudentWithCoursesMapper;
import dev.patika.schoolsystem.repository.AddressRepository;
import dev.patika.schoolsystem.repository.InstructorRepository;
import dev.patika.schoolsystem.repository.StudentRepository;
import dev.patika.schoolsystem.util.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods of transaction on the address.
 */
@Service
@RequiredArgsConstructor
public class AddressService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private StudentWithCoursesMapper studentWithCoursesMapper;

    @Autowired
    private InstructorResponseMapper instructorResponseMapper;


    /**
     * Gets all address list on the database.
     * @return Address List
     */
    public List<AddressDTO> findAllAddress(){

        List<Address> addressList = new ArrayList<>();
        Iterable<Address> iteAddress = addressRepository.findAll();
        iteAddress.iterator().forEachRemaining(addressList :: add);
        List<AddressDTO> addressDTOList = addressMapper.mapAddressListToAddressDTOList(addressList);
        if(addressDTOList.isEmpty()){
            throw new EmptyListException(ErrorMessageConstants.EMPTY_LIST);
        }
        return addressDTOList;

    }

    /**
     * Gets an address with id.
     * @param addressId The entered id parameter matches the id in the database.
     * @return Address with id.
     */
    public AddressDTO findByAddressId(long addressId){

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.ADDRESS_NOT_FOUND, addressId)));
        return addressMapper.mapAddressToAddressDTO(address);

    }

    /**
     * Performs address save to the database.
     * @param addressDTO The sent addressDTO object is saved.
     * @return Returns the saved addressDTO object.
     */
    @Transactional
    public AddressDTO saveAddress(AddressDTO addressDTO){

        Address address = addressMapper.mapAddressDTOToAddress(addressDTO);
        return addressMapper.mapAddressToAddressDTO(addressRepository.save(address));

    }

    /**
     * Updating an object in the database.
     * @param addressDTO new values are sent with AddressDTO.
     * @param addressId The Address object to be updated is found by id.
     * @return The updated Address object returns.
     */
    @Transactional
    public AddressDTO updateAddress(AddressDTO addressDTO, long addressId){

        Address foundAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.ADDRESS_NOT_FOUND, addressId)));
        foundAddress.setCity(addressDTO.getCity());
        foundAddress.setCountry(addressDTO.getCountry());
        foundAddress.setPlateCode(addressDTO.getPlateCode());
        addressRepository.save(foundAddress);
        return addressMapper.mapAddressToAddressDTO(foundAddress);


    }

    /**
     * Deletion of an object in the database.
     * @param addressId The Address object to be deleted is found by id.
     * @return Deleted information returns.
     */
    @Transactional
    public String deleteAddressById(long addressId){

        List<Student> studentList = addressRepository.findById(addressId).get().getStudentList();
        for (Student s : studentList) {

            s.setStudentAddress(null);
            studentRepository.save(s);

        }
        List<Instructor> instructorList = addressRepository.findById(addressId).get().getInstructorList();
        for (Instructor i : instructorList) {

            i.setInstructorAddress(null);
            instructorRepository.save(i);

        }
        addressRepository.deleteById(addressId);

        return "Address with id = " + addressId + " Deleted....";

    }

    /**
     * Gets students registered to the address.
     * @param addressId Address found by id.
     * @return Returns students registered at the address.
     */
    public List<StudentWithCoursesDTO> getStudentsOfAddress(long addressId){

        List<Student> studentList = addressRepository.findById(addressId).get().getStudentList();
        return studentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(studentList);

    }

    /**
     * Gets instructors registered to the address.
     * @param addressId Address found by id.
     * @return Returns instructors registered at the address.
     */
    public List<InstructorResponseDTO> getInstructorsOfAddress(long addressId){

        List<Instructor> instructorList = addressRepository.findById(addressId).get().getInstructorList();
        return instructorResponseMapper.mapInstructorListToInstructorResponseDTOList(instructorList);

    }

    /**
     * Address object found by id.
     * @param addressId Address found by id.
     * @return An Address.
     */
    public Address findAddressById(long addressId){

        return addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.ADDRESS_NOT_FOUND, addressId)));

    }

}
