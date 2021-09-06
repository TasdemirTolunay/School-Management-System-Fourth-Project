package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * It is the class used to perform operations on the database.
 * By extending the crud repository, operations on the database are performed with spring data jpa.
 */
@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM Instructor i WHERE i.instructorPhoneNumber =?1")
    boolean selectExistsPhoneNumber(String instructorPhoneNumber);

    List<Instructor> findByInstructorName(String instructorName);

}
