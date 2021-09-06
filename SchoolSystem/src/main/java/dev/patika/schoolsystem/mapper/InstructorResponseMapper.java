package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.InstructorResponseDTO;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.service.InstructorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the Instructor object is mapped to the InstructorResponseDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class InstructorResponseMapper {

    @Autowired
    protected InstructorService instructorService;

    @Mapping(target = "addressCity", expression = "java(instructorService.instructorsAddressCity(instructor.getId()))")
    @Mapping(target = "instructorsCourseNames", expression = "java(instructorService.instructorsCourseNames(instructor.getId()))")
    public abstract InstructorResponseDTO mapInstructorToInstructorResponseDTO(Instructor instructor);
    public abstract List<InstructorResponseDTO> mapInstructorListToInstructorResponseDTOList(Collection<Instructor> instructors);

}
