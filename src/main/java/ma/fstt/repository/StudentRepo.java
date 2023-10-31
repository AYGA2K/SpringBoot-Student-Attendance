package ma.fstt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fstt.entity.Student;

/**
 * StudentRepo
 */
public interface StudentRepo extends JpaRepository<Student, Long> {

}
