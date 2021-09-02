package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.PermanentInstructorDTO;
import dev.patika.schoolsystem.entity.PermanentInstructor;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PermanentInstructorMapper {

    public abstract List<PermanentInstructor> mapPermanentInstructorDTOListToPermanentInstructorList(Collection<PermanentInstructorDTO> permanentInstructorDTOS);
    public abstract List<PermanentInstructorDTO> mapPermanentInstructorListToPermanentInstructorDTOList(Collection<PermanentInstructor> permanentInstructors);
    public abstract PermanentInstructor mapPermanentInstructorDTOToPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);
    public abstract PermanentInstructorDTO mapPermanentInstructorToPermanentInstructorDTO(PermanentInstructor permanentInstructor);

}