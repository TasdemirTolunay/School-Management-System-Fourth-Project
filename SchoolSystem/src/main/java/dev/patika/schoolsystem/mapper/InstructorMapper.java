package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.InstructorDTO;
import dev.patika.schoolsystem.entity.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class InstructorMapper {

    @Mapping(target = "address", expression = "java(instructorService.findAddressById(instructorDTO.getAddressId()))")
    public abstract Instructor mapInstructorToInstructorDTO(InstructorDTO instructorDTO);
    public abstract InstructorDTO mapInstructorDTOToInstructor(Instructor instructor);
    public abstract List<InstructorDTO> mapInstructorDTOListToInstructorList(Collection<Instructor> instructors);
    public abstract List<Instructor> mapInstructorListToInstructorDTOList(Collection<InstructorDTO> instructorDTOS);

}