package ma.fstt.service;

import java.util.List;

import ma.fstt.entity.Student;

/**
 * StudentService
 */
public interface StudentService {
  List<Student> getAllStudents();

  void addStudent(Student student);

  void deleteStudent(Long id);

  void updateStudent(Long id, Student student);

  Student getStudent(Long id);

}
