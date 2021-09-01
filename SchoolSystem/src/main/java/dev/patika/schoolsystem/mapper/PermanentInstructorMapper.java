package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.PermanentInstructorDTO;
import dev.patika.schoolsystem.entity.PermanentInstructor;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PermanentInstructorMapper {

    public abstract List<PermanentInstructor> mapPermanentInstructorListToPermanentInstructorDTOList(Collection<PermanentInstructorDTO> permanentInstructorDTOS);
    public abstract List<PermanentInstructorDTO> mapPermanentInstructorDTOListToPermanentInstructorList(Collection<PermanentInstructor> permanentInstructors);
    public abstract PermanentInstructor mapPermanentInstructorToPermanentInstructorDTO(PermanentInstructorDTO permanentInstructorDTO);
    public abstract PermanentInstructorDTO mapPermanentInstructorDTOToPermanentInstructor(PermanentInstructor permanentInstructor);

}