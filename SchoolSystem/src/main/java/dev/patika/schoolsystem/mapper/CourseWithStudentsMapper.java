package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.CourseWithStudentsDTO;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.service.CourseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the Course object is mapped to the CourseWithStudentsDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class CourseWithStudentsMapper {

    @Autowired
    protected CourseService courseService;

    @Mapping(target = "students", expression = "java(courseService.findAllStudentsWithIds(courseWithStudentsDTO.getStudentsId()))")
    @Mapping(target = "instructor", expression = "java(courseService.findInstructorById(courseWithStudentsDTO.getInstructorId()))")
    public abstract Course mapCourseWithStudentsDTOToCourse(CourseWithStudentsDTO courseWithStudentsDTO);
    public abstract CourseWithStudentsDTO mapCourseToCourseWithStudentsDTO(Course course);
    public abstract List<CourseWithStudentsDTO> mapCourseListToCourseWithStudentsDTOList(Collection<Course> courses);
    public abstract List<Course> mapCourseWithStudentsDTOListToCourseList(Collection<CourseWithStudentsDTO> courseWithStudentsDTOS);

}
