package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the Student object is mapped to the StudentDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Autowired
    protected StudentService studentService;

    @Mapping(target = "studentAddress", expression = "java(studentService.findAddressById(studentDTO.getAddressId()))")
    public abstract Student mapStudentDTOToStudent(StudentDTO studentDTO);
    public abstract StudentDTO mapStudentToStudentDTO(Student student);
    public abstract List<Student> mapStudentDTOListToStudentList(Collection<StudentDTO> studentDTOS);
    public abstract List<StudentDTO> mapStudentListToStudentDTOList(Collection<Student> students);

}