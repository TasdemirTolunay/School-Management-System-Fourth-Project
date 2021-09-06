package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.EmptyListException;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.exceptions.StudentAgeNotValidException;
import dev.patika.schoolsystem.mapper.AddressMapper;
import dev.patika.schoolsystem.mapper.CourseMapper;
import dev.patika.schoolsystem.mapper.StudentMapper;
import dev.patika.schoolsystem.mapper.StudentWithCoursesMapper;
import dev.patika.schoolsystem.repository.AddressRepository;
import dev.patika.schoolsystem.repository.CourseRepository;
import dev.patika.schoolsystem.repository.StudentRepository;
import dev.patika.schoolsystem.util.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods of transaction on the student.
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentWithCoursesMapper studentWithCoursesMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * Gets all student list on the database.
     * @return Student List
     */
    public List<StudentWithCoursesDTO> findAllStudents(){

        List<Student> studentList = new ArrayList<>();
        Iterable<Student> studentIterable = studentRepository.findAll();
        studentIterable.iterator().forEachRemaining(studentList :: add);
        return studentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(studentList);

    }

    /**
     * Gets a student with id.
     * @param studentId The entered id parameter matches the id in the database.
     * @return Student
     */
    public StudentWithCoursesDTO findByStudentId(long studentId){

        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.STUDENT_NOT_FOUND, studentId)));
        return studentWithCoursesMapper.mapStudentToStudentWithCoursesDTO(foundStudent);

    }

    /**
     * Performs student save to the database.
     * @param studentDTO The sent studentDTO object is saved.
     * @return Returns the saved studentDTO object.
     */
    @Transactional
    public StudentWithCoursesDTO saveStudent(StudentDTO studentDTO){

        Student foundStudent = studentMapper.mapStudentDTOToStudent(studentDTO);
        int birthDateYear = foundStudent.getStudentBirthDate().getYear();
        int age = (LocalDate.now().getYear()) - birthDateYear;
        if(age < 18 || age > 40){
            throw new StudentAgeNotValidException(ErrorMessageConstants.WRONG_AGE);
        }
        return studentWithCoursesMapper.mapStudentToStudentWithCoursesDTO(studentRepository.save(foundStudent));

    }

    /**
     * Updating an object in the database.
     * @param studentDTO new values are sent with studentDTO.
     * @param studentId The Student object to be updated is found by id.
     * @return The updated Student object returns.
     */
    @Transactional
    public StudentWithCoursesDTO updateStudent(StudentDTO studentDTO, long studentId){

        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.STUDENT_NOT_FOUND, studentId)));
        int birthDateYear = studentDTO.getStudentBirthDate().getYear();
        int age = (LocalDate.now().getYear()) - birthDateYear;
        if(age < 18 || age > 40){
            throw new StudentAgeNotValidException(ErrorMessageConstants.WRONG_AGE);
        }
        foundStudent.setStudentName(studentDTO.getStudentName());
        foundStudent.setStudentGender(studentDTO.getStudentGender());
        foundStudent.setStudentBirthDate(studentDTO.getStudentBirthDate());
        studentRepository.save(foundStudent);
        return studentWithCoursesMapper.mapStudentToStudentWithCoursesDTO(foundStudent);

    }

    /**
     * Deletion of an object in the database.
     * @param studentId The Student object to be deleted is found by id.
     * @return Deleted information returns.
     */
    @Transactional
    public String deleteStudentById(long studentId){

        Student foundStudent = studentRepository.findById(studentId).get();
        List<Course> courseList = foundStudent.getCourses();
        for (Course c : courseList) {

            c.getStudents().remove(foundStudent);
            courseRepository.save(c);
        }
        studentRepository.deleteById(studentId);
        return "Student with id = " + studentId + " Deleted....";

    }

    /**
     * Gets a student with instructor name.
     * @param studentName The entered student name parameter matches the student name in the database.
     * @return Student by name
     */
    public List<StudentWithCoursesDTO> findStudentByName(String studentName){

        List<Student> studentList = studentRepository.findStudentByStudentName(studentName);
        return studentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(studentList);

    }

    /**
     * Group them by gender.
     * @return Gender groups.
     */
    public List<?> genderGroups(){

        return studentRepository.getGenderWithGrouping();

    }

    /**
     * Gets courses registered to the student.
     * @param studentId Student found by id.
     * @return Returns courses registered at the student.
     */
    public List<CourseDTO> findCoursesOfStudent(long studentId){

        List<Course> courseList = studentRepository.findById(studentId).get().getCourses();
        return courseMapper.mapCourseListToCourseDTOList(courseList);

    }

    /**
     * Gets address registered to the student.
     * @param studentId Student found by id.
     * @return Returns address registered at the student.
     */
    public AddressDTO findAddressOfStudent(long studentId){

        Address address = studentRepository.findById(studentId).get().getStudentAddress();
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
     * Student object found by id.
     * @param studentId Student found by id.
     * @return A Student.
     */
    public Student findStudentById(long studentId){

        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.STUDENT_NOT_FOUND, studentId)));

    }

    /**
     * Finds course names offered by the student.
     * @param studentId Student found by id.
     * @return Course Names
     */
    public List<String> StudentsCourses(long studentId){

        List<String> courseNames = new ArrayList<>();
        List<Course> foundStudentsCourses = findStudentById(studentId).getCourses();
        for (Course c : foundStudentsCourses) {

            courseNames.add(c.getCourseName());

        }

        return courseNames;

    }

    /**
     * Gets the city of the student's address.
     * @param studentId Student found by id.
     * @return City of address
     */
    public String  studentsAddressCity(long studentId){

        return findStudentById(studentId).getStudentAddress().getCity();

    }

}
