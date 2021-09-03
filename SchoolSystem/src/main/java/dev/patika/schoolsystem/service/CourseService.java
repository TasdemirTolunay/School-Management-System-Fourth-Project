package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.CourseWithStudentsDTO;
import dev.patika.schoolsystem.dto.InstructorDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.schoolsystem.mapper.CourseMapper;
import dev.patika.schoolsystem.mapper.CourseWithStudentsMapper;
import dev.patika.schoolsystem.mapper.InstructorMapper;
import dev.patika.schoolsystem.mapper.StudentWithCoursesMapper;
import dev.patika.schoolsystem.repository.CourseRepository;
import dev.patika.schoolsystem.repository.InstructorRepository;
import dev.patika.schoolsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private InstructorMapper instructorMapper;

    @Autowired
    private StudentWithCoursesMapper studentWithCoursesMapper;

    @Autowired
    private CourseWithStudentsMapper courseWithStudentsMapper;

    public List<CourseDTO> findAllCourses(){

        List<Course> courseList = new ArrayList<>();
        Iterable<Course> courseIterable = courseRepository.findAll();
        courseIterable.iterator().forEachRemaining(courseList :: add);
        return courseMapper.mapCourseListToCourseDTOList(courseList);

    }

    public CourseDTO findByCourseId(long courseId){

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Course with ID: %d could not found!", courseId)));
        return courseMapper.mapCourseToCourseDTO(course);

    }

    @Transactional
    public CourseDTO saveCourse(CourseWithStudentsDTO courseWithStudentsDTO){

        Course foundCourse = courseWithStudentsMapper.mapCourseWithStudentsDTOToCourse(courseWithStudentsDTO);
        List<Student> students = foundCourse.getStudents();
        if(students.size() > 20){
            throw new StudentNumberForOneCourseExceededException("A Course can have a maximum of 20 Students!!!");
        }
        courseRepository.save(foundCourse);
        return courseMapper.mapCourseToCourseDTO(foundCourse);

    }

    @Transactional
    public CourseDTO updateCourse(CourseDTO courseDTO, long courseId){

        Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Course with ID: %d could not found!", courseId)));
        foundCourse.setCourseName(courseDTO.getCourseName());
        foundCourse.setCourseCode(courseDTO.getCourseCode());
        foundCourse.setCourseCreditScore(courseDTO.getCourseCreditScore());
        courseRepository.save(foundCourse);
        return courseMapper.mapCourseToCourseDTO(foundCourse);

    }

    @Transactional
    public String deleteCourseById(long courseId){

        courseRepository.deleteById(courseId);
        return "Course id = " + courseId + " Deleted....";

    }

    @Transactional
    public String deleteCourseByObject(CourseDTO courseDTO){

        Course course = courseMapper.mapCourseDTOToCourse(courseDTO);
        courseRepository.delete(course);
        return "Course Deleted.....";

    }

    public List<CourseDTO> findCourseByName(String courseName){

        List<Course> foundCourses = courseRepository.findByCourseName(courseName);
        return courseMapper.mapCourseListToCourseDTOList(foundCourses);

    }

    public List<StudentWithCoursesDTO> findStudentsOfCourse(long courseId){

        List<Student> studentList = courseRepository.findById(courseId).get().getStudents();
        return studentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(studentList);

    }

    public InstructorDTO findInstructorOfCourse(long courseId){

        Instructor instructorList = courseRepository.findById(courseId).get().getInstructor();
        return instructorMapper.mapInstructorToInstructorDTO(instructorList);

    }

    public Course findCourseById(long courseId){

        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Course with ID: %d could not found!", courseId)));

    }

    public Instructor findInstructorById(long instructorId){

        Instructor foundInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format("Instructor with ID: %d could not found!", instructorId)));
        return foundInstructor;

    }

    public int getNumberOfStudents(){

        return studentRepository.numberOfStudents();

    }

    public long instructorOfCourseId(long courseId){
        
        return findCourseById(courseId).getInstructor().getId();

    }

    public List<Student> findAllStudentsWithIds(List<Long> studensId){
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < studensId.size(); i ++) {

            int finalI = i;
            Student foundStudent = studentRepository.findById(studensId.get(i))
                    .orElseThrow(() -> new IdNotFoundException(String.format("Student with ID: %d could not found!", studensId.get(finalI))));
            studentList.add(foundStudent);
        }
        return studentList;

    }

}
