package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

}
