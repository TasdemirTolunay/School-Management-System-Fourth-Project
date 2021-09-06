package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the Student object is mapped to the StudentWithCoursesDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class StudentWithCoursesMapper {

    @Autowired
    protected StudentService studentService;

    @Mapping(target = "studentsCourses", expression = "java(studentService.StudentsCourses(student.getId()))")
    @Mapping(target = "addressCity", expression = "java(studentService.studentsAddressCity(student.getId()))")
    public abstract StudentWithCoursesDTO mapStudentToStudentWithCoursesDTO(Student student);
    public abstract Student mapStudentWithCoursesDTOToStudent(StudentWithCoursesDTO studentWithCoursesDTO);
    public abstract List<StudentWithCoursesDTO> mapStudentListToStudentWithCoursesDTOList(Collection<Student> students);
    public abstract List<Student> mapStudentWithCoursesDTOListToStudentList(Collection<StudentWithCoursesDTO> studentWithCoursesDTOS);

}
