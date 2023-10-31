package ma.fstt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fstt.entity.Absence;
import ma.fstt.repository.AbsenceRepo;
import ma.fstt.service.AbsenceService;

/**
 * AbsenceServiceImpl
 */
@Service
public class AbsenceServiceImpl implements AbsenceService {
  @Autowired
  private AbsenceRepo absenceRepo;

  @Override
  public void addAbsence(Absence absence) {
    absenceRepo.save(absence);
  }

  @Override
  public void deleteAbsence(Long id) {
    absenceRepo.deleteById(id);
  }

  @Override
  public Absence getAbsence(Long id) {
    return absenceRepo.findById(id).orElse(null);
  }

  @Override
  public List<Absence> getAllAbsences(String targetDate) {
    return absenceRepo.findByDate(targetDate);
  }

  @Override
  public void updateAbsence(Long id, Absence absence) {
    // Absence absenceToUpdate = absenceRepo.findById(id).orElse(null);
    // absenceToUpdate.setDate(absence.getDate());
    // absence.setStudent(absence.getStudent());
    // absenceRepo.save(absenceToUpdate);
  }

  @Override
  public Absence getAbsenceByDate(String dateStr) {
    return absenceRepo.findOneByDate(dateStr);
  }

}
