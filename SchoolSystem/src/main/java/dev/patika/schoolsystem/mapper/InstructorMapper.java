package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.InstructorDTO;
import dev.patika.schoolsystem.dto.PermanentInstructorDTO;
import dev.patika.schoolsystem.dto.VisitingResearcherDTO;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.service.InstructorService;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the Instructor object is mapped to the InstructorDTO, the PermanentInstructorDTO and the VisitingResearcherDTO objects.
 */
@Mapper(componentModel = "spring", uses = {PermanentInstructorMapper.class, VisitingResearcherMapper.class})
public abstract class InstructorMapper {

    @Autowired
    protected InstructorService instructorService;

    @Mapping(target = "instructorAddress", expression = "java(instructorService.findAddressById(instructorDTO.getAddressId()))")
    public abstract Instructor mapInstructorDTOToInstructor(InstructorDTO instructorDTO);

    @IterableMapping(elementTargetType = InstructorDTO.class)
    public abstract InstructorDTO mapInstructorToInstructorDTO(Instructor instructor);

    @IterableMapping(elementTargetType = InstructorDTO.class)
    public abstract List<InstructorDTO> mapInstructorListToInstructorDTOList(Collection<Instructor> instructors);

    public abstract List<Instructor> mapInstructorDTOListToInstructorList(Collection<InstructorDTO> instructorDTOS);

    @IterableMapping(elementTargetType = PermanentInstructorDTO.class)
    public abstract PermanentInstructorDTO mapInstructorToPermanentInstructorDTO(Instructor instructor);

    @Mapping(target = "instructorAddress", expression = "java(instructorService.findAddressById(permanentInstructorDTO.getAddressId()))")
    public abstract Instructor mapPermanentInstructorDTOToInstructor(PermanentInstructorDTO permanentInstructorDTO);

    @IterableMapping(elementTargetType = VisitingResearcherDTO.class)
    public abstract VisitingResearcherDTO mapInstructorToVisitingResearcherDTO(Instructor instructor);

    @Mapping(target = "instructorAddress", expression = "java(instructorService.findAddressById(visitingResearcherDTO.getAddressId()))")
    public abstract Instructor mapVisitingResearcherDTOToInstructor(VisitingResearcherDTO visitingResearcherDTO);

    @IterableMapping(elementTargetType = VisitingResearcherDTO.class)
    public abstract List<VisitingResearcherDTO> mapInstructorListToVisitingResearcherDTOList(Collection<Instructor> instructors);


    @IterableMapping(elementTargetType = PermanentInstructorDTO.class)
    public abstract List<PermanentInstructorDTO> mapInstructorListToPermanentInstructorDTOList(Collection<Instructor> instructors);

}