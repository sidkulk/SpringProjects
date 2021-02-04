package io.kulksud.springboot.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Siddharth Data Access layer.
 */

//this extracts data from database
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findByEmail(String email);
}
