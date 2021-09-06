package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * It is the class used to perform operations on the database.
 * By extending the crud repository, operations on the database are performed with spring data jpa.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("SELECT COUNT(s) FROM Student s")
    int numberOfStudents();

    List<Student> findStudentByStudentName(String studentName);

    @Query("select s.studentGender , count(s.studentGender) from Student s group by s.studentGender")
    List<?> getGenderWithGrouping();

}
