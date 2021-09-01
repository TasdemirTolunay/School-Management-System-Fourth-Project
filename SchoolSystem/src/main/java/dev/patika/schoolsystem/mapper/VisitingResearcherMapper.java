package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.VisitingResearcherDTO;
import dev.patika.schoolsystem.entity.VisitingResearcher;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class VisitingResearcherMapper {

    public abstract List<VisitingResearcher> mapVisitingResearcherListToVisitingResearcherDTOList(Collection<VisitingResearcherDTO> visitingResearcherDTOS);
    public abstract List<VisitingResearcherDTO> mapVisitingResearcherDTOListToVisitingResearcherList(Collection<VisitingResearcher> visitingResearchers);
    public abstract VisitingResearcher mapVisitingResearcherToVisitingResearcherDTO(VisitingResearcherDTO visitingResearcherDTO);
    public abstract VisitingResearcherDTO mapVisitingResearcherDTOToVisitingResearcher(VisitingResearcher visitingResearcher);

}