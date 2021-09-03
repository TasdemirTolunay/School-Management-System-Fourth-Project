package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("SELECT COUNT(s) FROM Student s")
    int numberOfStudents();

    List<Student> findStudentByStudentName(String studentName);

    @Query("select s.studentGender , count(s.studentGender) from Student s group by s.studentGender")
    List<?> getGenderWithGrouping();

}
