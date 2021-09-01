package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Mapping(target = "address", expression = "java(studentService.findAddressById(studentDTO.getAddressId()))")
    public abstract Student mapStudentToStudentDTO(StudentDTO studentDTO);
    public abstract StudentDTO mapStudentDTOToStudent(Student student);
    public abstract List<Student> mapStudentListToStudentDTOList(Collection<StudentDTO> studentDTOS);
    public abstract List<StudentDTO> mapStudentDTOListToStudentList(Collection<Student> students);

}