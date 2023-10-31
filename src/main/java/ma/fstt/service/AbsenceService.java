package ma.fstt.service;

import java.util.List;

import ma.fstt.entity.Absence;

/**
 * AbsenceService
 */
public interface AbsenceService {
  List<Absence> getAllAbsences(String dateStr);

  Absence getAbsence(Long id);

  void addAbsence(Absence absence);

  void updateAbsence(Long id, Absence absence);

  void deleteAbsence(Long id);

  Absence getAbsenceByDate(String dateStr);

}
