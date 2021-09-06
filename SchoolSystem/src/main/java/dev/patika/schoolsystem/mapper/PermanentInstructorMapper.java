package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.PermanentInstructorDTO;
import dev.patika.schoolsystem.entity.PermanentInstructor;
import dev.patika.schoolsystem.service.InstructorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the PermanentInstructor object is mapped to the PermanentInstructorDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class PermanentInstructorMapper {

    @Autowired
    protected InstructorService instructorService;

    public abstract List<PermanentInstructor> mapPermanentInstructorDTOListToPermanentInstructorList(Collection<PermanentInstructorDTO> permanentInstructorDTOS);
    public abstract List<PermanentInstructorDTO> mapPermanentInstructorListToPermanentInstructorDTOList(Collection<PermanentInstructor> permanentInstructors);
    @Mapping(target = "instructorAddress", expression = "java(instructorService.findAddressById(permanentInstructorDTO.getAddressId()))")
    public abstract PermanentInstructor mapPermanentInstructorDTOToPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);
    public abstract PermanentInstructorDTO mapPermanentInstructorToPermanentInstructorDTO(PermanentInstructor permanentInstructor);

}