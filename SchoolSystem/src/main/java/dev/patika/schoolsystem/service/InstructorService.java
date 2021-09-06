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


    public List<InstructorResponseDTO> findAllInstructor(){

        List<Instructor> instructorList = new ArrayList<>();
        Iterable<Instructor> instructorIterable = instructorRepository.findAll();
        instructorIterable.iterator().forEachRemaining(instructorList :: add);
        if(instructorList.isEmpty()){

                throw new EmptyListException(ErrorMessageConstants.EMPTY_LIST);

        }
        return instructorResponseMapper.mapInstructorListToInstructorResponseDTOList(instructorList);

    }

    public InstructorResponseDTO findByInstructorId(long instructorId){

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId)));
        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(instructor);

    }

    @Transactional
    public InstructorResponseDTO savePermanentInstructor(PermanentInstructorDTO permanentInstructorDTO){

        PermanentInstructor instructor = permanentInstructorMapper.mapPermanentInstructorDTOToPermanentInstructor(permanentInstructorDTO);
        if(instructorRepository.selectExistsPhoneNumber(instructor.getInstructorPhoneNumber())){

            throw new InstructorIsAlreadyExistException("Instructor With PhoneNumber : " + instructor.getInstructorPhoneNumber() + " is already exists!!!!");

        }

        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(instructorRepository.save(instructor));

    }

    @Transactional
    public InstructorResponseDTO saveVisitingResearcherInstructor(VisitingResearcherDTO visitingResearcherDTO){

        VisitingResearcher instructor = visitingResearcherMapper.mapVisitingResearcherDTOToVisitingResearcher(visitingResearcherDTO);
        if(instructorRepository.selectExistsPhoneNumber(instructor.getInstructorPhoneNumber())){

            throw new InstructorIsAlreadyExistException("Instructor With PhoneNumber : " + instructor.getInstructorPhoneNumber() + " is already exists!!!!");

        }
        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(instructorRepository.save(instructor));

    }

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

    public List<InstructorResponseDTO> findInstructorByName(String instructorName){

        List<Instructor> instructorList = instructorRepository.findByInstructorName(instructorName);
        return instructorResponseMapper.mapInstructorListToInstructorResponseDTOList(instructorList);

    }

    public List<CourseDTO> getCoursesOfInstructor(long instructorId){

        List<Course> courseList = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId))).getCourseList();
        return courseMapper.mapCourseListToCourseDTOList(courseList);

    }

    public AddressDTO getAddressOfInstructor(long instructorId){

        Address address = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId))).getInstructorAddress();
        return addressMapper.mapAddressToAddressDTO(address);

    }



    public Address findAddressById(long addressId){

        Address foundAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.ADDRESS_NOT_FOUND, addressId)));
        return foundAddress;

    }

    public Instructor findInstructorById(long instructorId){

        Instructor foundInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId)));
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
