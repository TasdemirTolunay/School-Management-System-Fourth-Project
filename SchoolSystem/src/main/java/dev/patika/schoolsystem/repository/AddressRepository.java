package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * It is the class used to perform operations on the database.
 * By extending the crud repository, operations on the database are performed with spring data jpa.
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}