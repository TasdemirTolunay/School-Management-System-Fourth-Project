package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.*;
import dev.patika.schoolsystem.entity.*;
import dev.patika.schoolsystem.exceptions.EmptyListException;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.exceptions.InstructorIsAlreadyExistException;
import dev.patika.schoolsystem.mapper.*;
import dev.patika.schoolsystem.repository.AddressRepository;
import dev.patika.schoolsystem.repository.CourseRepository;
import dev.patika.schoolsystem.repository.InstructorRepository;
import dev.patika.schoolsystem.util.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods of transaction on the instructor.
 */
@Service
@RequiredArgsConstructor
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorResponseMapper instructorResponseMapper;

    @Autowired
    private InstructorMapper instructorMapper;

    @Autowired
    private PermanentInstructorMapper permanentInstructorMapper;

    @Autowired
    private VisitingResearcherMapper visitingResearcherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * Gets all instructor list on the database.
     * @return Instructor List
     */
    public List<InstructorResponseDTO> findAllInstructor(){

        List<Instructor> instructorList = new ArrayList<>();
        Iterable<Instructor> instructorIterable = instructorRepository.findAll();
        instructorIterable.iterator().forEachRemaining(instructorList :: add);
        if(instructorList.isEmpty()){

                throw new EmptyListException(ErrorMessageConstants.EMPTY_LIST);

        }
        return instructorResponseMapper.mapInstructorListToInstructorResponseDTOList(instructorList);

    }

    /**
     * Gets a instructor with id.
     * @param instructorId The entered id parameter matches the id in the database.
     * @return Instructor
     */
    public InstructorResponseDTO findByInstructorId(long instructorId){

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId)));
        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(instructor);

    }

    /**
     * Performs instructor save to the database.
     * @param permanentInstructorDTO The sent permanentInstructorDTO object is saved.
     * @return Returns the saved permanentInstructorDTO object.
     */
    @Transactional
    public InstructorResponseDTO savePermanentInstructor(PermanentInstructorDTO permanentInstructorDTO){

        PermanentInstructor instructor = permanentInstructorMapper.mapPermanentInstructorDTOToPermanentInstructor(permanentInstructorDTO);
        if(instructorRepository.selectExistsPhoneNumber(instructor.getInstructorPhoneNumber())){

            throw new InstructorIsAlreadyExistException("Instructor With PhoneNumber : " + instructor.getInstructorPhoneNumber() + " is already exists!!!!");

        }

        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(instructorRepository.save(instructor));

    }

    /**
     * Performs instructor save to the database.
     * @param visitingResearcherDTO The sent visitingResearcherDTO object is saved.
     * @return Returns the saved visitingResearcherDTO object.
     */
    @Transactional
    public InstructorResponseDTO saveVisitingResearcherInstructor(VisitingResearcherDTO visitingResearcherDTO){

        VisitingResearcher instructor = visitingResearcherMapper.mapVisitingResearcherDTOToVisitingResearcher(visitingResearcherDTO);
        if(instructorRepository.selectExistsPhoneNumber(instructor.getInstructorPhoneNumber())){

            throw new InstructorIsAlreadyExistException("Instructor With PhoneNumber : " + instructor.getInstructorPhoneNumber() + " is already exists!!!!");

        }
        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(instructorRepository.save(instructor));

    }

    /**
     * Updating an object in the database.
     * @param instructorDTO new values are sent with instructorDTO.
     * @param instructorId The Instructor object to be updated is found by id.
     * @return The updated Instructor object returns.
     */
    @Transactional
    public InstructorResponseDTO updateInstructor(InstructorDTO instructorDTO, long instructorId){

        Instructor foundInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId)));
        if(instructorRepository.selectExistsPhoneNumber(instructorDTO.getInstructorPhoneNumber())){

            throw new InstructorIsAlreadyExistException("Instructor With PhoneNumber : " + instructorDTO.getInstructorPhoneNumber() + " is already exists!!!!");

        }
        foundInstructor.setInstructorName(instructorDTO.getInstructorName());
        foundInstructor.setInstructorPhoneNumber(instructorDTO.getInstructorPhoneNumber());
        instructorRepository.save(foundInstructor);
        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(foundInstructor);

    }

    /**
     * Deletion of an object in the database.
     * @param instructorId The Instructor object to be deleted is found by id.
     * @return Deleted information returns.
     */
    @Transactional
    public String deleteInstructorById(long instructorId){

        List<Course> courseList = instructorRepository.findById(instructorId).get().getCourseList();
        for (Course c : courseList) {

            c.setInstructor(null);
            courseRepository.save(c);

        }
        instructorRepository.deleteById(instructorId);
        return "Instructor with id = " + instructorId + " Deleted....";

    }

    /**
     * Gets a instructor with instructor name.
     * @param instructorName The entered instructor name parameter matches the instructor name in the database.
     * @return Instructor
     */
    public List<InstructorResponseDTO> findInstructorByName(String instructorName){

        List<Instructor> instructorList = instructorRepository.findByInstructorName(instructorName);
        return instructorResponseMapper.mapInstructorListToInstructorResponseDTOList(instructorList);

    }

    /**
     * Gets courses registered to the instructor.
     * @param instructorId Instructor found by id.
     * @return Returns courses registered at the instructor.
     */
    public List<CourseDTO> getCoursesOfInstructor(long instructorId){

        List<Course> courseList = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId))).getCourseList();
        return courseMapper.mapCourseListToCourseDTOList(courseList);

    }

    /**
     * Gets address registered to the instructor.
     * @param instructorId Instructor found by id.
     * @return Returns address registered at the instructor.
     */
    public AddressDTO getAddressOfInstructor(long instructorId){

        Address address = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId))).getInstructorAddress();
        return addressMapper.mapAddressToAddressDTO(address);

    }

    /**
     * Address object found by id.
     * @param addressId Address found by id.
     * @return An Address.
     */
    public Address findAddressById(long addressId){

        Address foundAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.ADDRESS_NOT_FOUND, addressId)));
        return foundAddress;

    }

    /**
     * Instructor object found by id.
     * @param instructorId Instructor found by id.
     * @return Instructor
     */
    public Instructor findInstructorById(long instructorId){

        Instructor foundInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId)));
        return foundInstructor;

    }

    /**
     * Gets the city of the instructor's address.
     * @param instructorId Instructor found by id.
     * @return City of address
     */
    public String instructorsAddressCity(long instructorId){

        return findInstructorById(instructorId).getInstructorAddress().getCity();

    }

    /**
     * Finds course names offered by the instructor.
     * @param instructorId Instructor found by id.
     * @return Course Names
     */
    public List<String> instructorsCourseNames(long instructorId){

        List<String> courseNames = new ArrayList<>();
        List<Course> foundCourses = findInstructorById(instructorId).getCourseList();

        for (Course c : foundCourses) {

            courseNames.add(c.getCourseName());

        }

        return courseNames;

    }

}
