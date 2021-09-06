package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.service.CourseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the Course object is mapped to the CourseDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    @Autowired
    protected CourseService courseService;

    @Mapping(target = "numberOfStudents", expression = "java(courseService.getNumberOfStudents(course.getId()))")
    @Mapping(target = "instructorName", expression = "java(courseService.getInstructorOfCourseName(course.getId()))")
    public abstract CourseDTO mapCourseToCourseDTO(Course course);
    public abstract Course mapCourseDTOToCourse(CourseDTO courseDTO);
    public abstract List<CourseDTO> mapCourseListToCourseDTOList(Collection<Course> courses);
    public abstract List<Course> mapCourseDTOListToCourseList(Collection<CourseDTO> courseDTOS);

}
