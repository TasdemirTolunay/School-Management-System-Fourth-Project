package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * It is the class used to perform operations on the database.
 * By extending the crud repository, operations on the database are performed with spring data jpa.
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Course c WHERE c.courseCode = ?1")
    boolean selectExistsCourseCode(String courseCode);

    List<Course> findByCourseName(String courseName);

}
