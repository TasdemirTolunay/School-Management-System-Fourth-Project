package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.service.CourseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    @Autowired
    protected CourseService courseService;

    @Mapping(target = "instructor", expression = "java(courseService.findInstructorById(courseDTO.getInstructorId()))")
    public abstract Course mapCourseDTOToCourse(CourseDTO courseDTO);
    public abstract CourseDTO mapCourseToCourseDTO(Course course);
    public abstract List<CourseDTO> mapCourseListToCourseDTOList(Collection<Course> courses);
    public abstract List<Course> mapCourseDTOListToCourseList(Collection<CourseDTO> courseDTOS);

}
