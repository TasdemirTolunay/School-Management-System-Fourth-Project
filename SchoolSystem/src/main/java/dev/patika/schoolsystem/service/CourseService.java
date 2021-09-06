package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.*;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.CourseIsAlreadyExistException;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.schoolsystem.mapper.*;
import dev.patika.schoolsystem.repository.CourseRepository;
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
 * This class contains methods of transaction on the course.
 */
@Service
@RequiredArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private InstructorResponseMapper instructorResponseMapper;

    @Autowired
    private StudentWithCoursesMapper studentWithCoursesMapper;

    @Autowired
    private CourseWithStudentsMapper courseWithStudentsMapper;

    /**
     * Gets all course list on the database.
     * @return Course List
     */
    public List<CourseDTO> findAllCourses(){

        List<Course> courseList = new ArrayList<>();
        Iterable<Course> courseIterable = courseRepository.findAll();
        courseIterable.iterator().forEachRemaining(courseList :: add);
        return courseMapper.mapCourseListToCourseDTOList(courseList);

    }

    /**
     * Gets a course with id.
     * @param courseId The entered id parameter matches the id in the database.
     * @return Course
     */
    public CourseDTO findByCourseId(long courseId){

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.COURSE_NOT_FOUND, courseId)));
        return courseMapper.mapCourseToCourseDTO(course);

    }

    /**
     * Performs course save to the database.
     * @param courseWithStudentsDTO The sent courseWithStudentsDTO object is saved.
     * @return Returns the saved courseWithStudentsDTO object.
     */
    @Transactional
    public CourseDTO saveCourse(CourseWithStudentsDTO courseWithStudentsDTO){

        Course foundCourse = courseWithStudentsMapper.mapCourseWithStudentsDTOToCourse(courseWithStudentsDTO);
        List<Student> students = foundCourse.getStudents();
        if(courseRepository.selectExistsCourseCode(foundCourse.getCourseCode())){

            throw new CourseIsAlreadyExistException("Course With CourseCode : " + foundCourse.getCourseCode() + " is already exists!!!!");

        }

        if(students.size() > 20){

            throw new StudentNumberForOneCourseExceededException(ErrorMessageConstants.MAX_COURSE_STUDENTS);

        }
        courseRepository.save(foundCourse);
        return courseMapper.mapCourseToCourseDTO(foundCourse);

    }

    /**
     * Updating an object in the database.
     * @param courseWithStudentsDTO new values are sent with courseWithStudentsDTO.
     * @param courseId The Course object to be updated is found by id.
     * @return The updated Course object returns.
     */
    @Transactional
    public CourseDTO updateCourse(CourseWithStudentsDTO courseWithStudentsDTO, long courseId){

        Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.COURSE_NOT_FOUND, courseId)));
        if(courseRepository.selectExistsCourseCode(courseWithStudentsDTO.getCourseCode())){

            throw new CourseIsAlreadyExistException("Course With CourseCode : " + courseWithStudentsDTO.getCourseCode() + " is already exists!!!!");

        }
        foundCourse.setCourseName(courseWithStudentsDTO.getCourseName());
        foundCourse.setCourseCode(courseWithStudentsDTO.getCourseCode());
        foundCourse.setCourseCreditScore(courseWithStudentsDTO.getCourseCreditScore());
        courseRepository.save(foundCourse);
        return courseMapper.mapCourseToCourseDTO(foundCourse);

    }

    /**
     * Deletion of an object in the database.
     * @param courseId The Course object to be deleted is found by id.
     * @return Deleted information returns.
     */
    @Transactional
    public String deleteCourseById(long courseId){

        courseRepository.deleteById(courseId);
        return "Course with id = " + courseId + " Deleted....";

    }

    /**
     * Gets a course with course name.
     * @param courseName The entered course name parameter matches the course name in the database.
     * @return Course
     */
    public List<CourseDTO> findCourseByName(String courseName){

        List<Course> foundCourses = courseRepository.findByCourseName(courseName);
        return courseMapper.mapCourseListToCourseDTOList(foundCourses);

    }

    /**
     * Gets students registered to the course.
     * @param courseId Course found by id.
     * @return Returns students registered at the course.
     */
    public List<StudentWithCoursesDTO> findStudentsOfCourse(long courseId){

        List<Student> studentList = courseRepository.findById(courseId).get().getStudents();
        return studentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(studentList);

    }

    /**
     * Gets instructors registered to the course.
     * @param courseId Course found by id.
     * @return Returns instructors registered at the course.
     */
    public InstructorResponseDTO findInstructorOfCourse(long courseId){

        Instructor instructorList = courseRepository.findById(courseId).get().getInstructor();
        return instructorResponseMapper.mapInstructorToInstructorResponseDTO(instructorList);

    }

    /**
     * Course object found by id.
     * @param courseId Course found by id.
     * @return Course
     */
    public Course findCourseById(long courseId){

        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.COURSE_NOT_FOUND, courseId)));

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
     * Gets the number of students enrolled in the course.
     * @param courseId Course found by id.
     * @return The number of students enrolled in the course returns.
     */
    public int getNumberOfStudents(long courseId){

        return courseRepository.findById(courseId).get().getStudents().size();

    }

    /**
     * Finds students with the id's.
     * @param studensId Ids of students are sent.
     * @return Student List
     */
    public List<Student> findAllStudentsWithIds(List<Long> studensId){
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < studensId.size(); i ++) {

            int finalI = i;
            Student foundStudent = studentRepository.findById(studensId.get(i))
                    .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.STUDENT_NOT_FOUND, studensId.get(finalI))));
            studentList.add(foundStudent);
        }
        return studentList;

    }

    /**
     * Finds the name of the instructor giving the course.
     * @param courseId Course found by id.
     * @return Instructor Name
     */
    public String getInstructorOfCourseName(long courseId){

        return courseRepository.findById(courseId).get().getInstructor().getInstructorName();

    }

}
