package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.VisitingResearcherDTO;
import dev.patika.schoolsystem.entity.VisitingResearcher;
import dev.patika.schoolsystem.service.InstructorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the VisitingResearcher object is mapped to the VisitingResearcherDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class VisitingResearcherMapper {

    @Autowired
    protected InstructorService instructorService;

    public abstract List<VisitingResearcher> mapVisitingResearcherDTOListToVisitingResearcherList(Collection<VisitingResearcherDTO> visitingResearcherDTOS);
    public abstract List<VisitingResearcherDTO> mapVisitingResearcherListToVisitingResearcherDTOList(Collection<VisitingResearcher> visitingResearchers);
    @Mapping(target = "instructorAddress", expression = "java(instructorService.findAddressById(visitingResearcherDTO.getAddressId()))")
    public abstract VisitingResearcher mapVisitingResearcherDTOToVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO);
    public abstract VisitingResearcherDTO mapVisitingResearcherToVisitingResearcherDTO(VisitingResearcher visitingResearcher);

}