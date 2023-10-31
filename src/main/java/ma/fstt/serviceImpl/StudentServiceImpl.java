package ma.fstt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fstt.entity.Student;
import ma.fstt.repository.StudentRepo;
import ma.fstt.service.StudentService;

/**
 * StudentServiceImpl
 */
@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  private StudentRepo studentRepo;

  @Override
  public List<Student> getAllStudents() {
    return studentRepo.findAll();
  }

  @Override
  public void addStudent(Student student) {
    studentRepo.save(student);
  }

  @Override
  public void deleteStudent(Long id) {
    studentRepo.deleteById(id);
  }

  @Override
  public void updateStudent(Long id, Student student) {
    Student existingStudent = studentRepo.findById(id).orElse(null);
    existingStudent.setFirstName(student.getFirstName());
    existingStudent.setLastName(student.getLastName());
    studentRepo.save(existingStudent);
    studentRepo.save(student);
  }

  @Override
  public Student getStudent(Long id) {
    return studentRepo.findById(id).orElse(null);
  }

}
