package ma.fstt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import ma.fstt.entity.Absence;

/**
 * AbsenceRepo
 */
public interface AbsenceRepo extends JpaRepository<Absence, Long> {
  Absence findOneByDate(@Param("targetDate") String targetDate);

  List<Absence> findByDate(@Param("targetDate") String targetDate);
}
