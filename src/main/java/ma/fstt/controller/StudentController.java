package ma.fstt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import ma.fstt.serviceImpl.StudentServiceImpl;
import ma.fstt.entity.Student;

/**
 * StudentController
 */
@Controller
@RequestMapping("/student")
public class StudentController {
  private final StudentServiceImpl studentServiceImpl;

  public StudentController(StudentServiceImpl studentServiceImpl) {
    this.studentServiceImpl = studentServiceImpl;
  }

  @GetMapping
  public String Index(Model model) {
    List<Student> students = studentServiceImpl.getAllStudents();
    model.addAttribute("students", students);
    return "students/index";
  }

  @GetMapping("/{id}")
  @HxRequest
  public String showStudentById(@PathVariable Long id, Model model) {
    Student student = studentServiceImpl.getStudent(id);
    model.addAttribute("student", student);
    return "students/show";
  }

  @GetMapping("/create")
  @HxRequest
  public String createStudent(Model model) {
    model.addAttribute("student", new Student());
    return "students/create";
  }

  @PostMapping("/create")
  @HxRequest
  public String saveStudent(Model model, @ModelAttribute Student student) {
    studentServiceImpl.addStudent(student);
    // return "redirect:/students";
    List<Student> students = studentServiceImpl.getAllStudents();
    model.addAttribute("students", students);

    return "students/index";

  }

  @HxRequest
  @GetMapping("/edit/{id}")
  public String editStudent(Model model, @PathVariable Long id) {
    Student student = studentServiceImpl.getStudent(id);
    model.addAttribute("student", student);
    return "students/edit";
  }

  @PutMapping("/edit/{id}")
  @HxRequest
  public String updateStudent(Model model, @ModelAttribute Student student, @PathVariable Long id) {
    studentServiceImpl.updateStudent(id, student);
    // return "redirect:/students";
    List<Student> students = studentServiceImpl.getAllStudents();
    model.addAttribute("students", students);

    return "students/index";

  }

  @DeleteMapping("/delete/{id}")
  @HxRequest
  public String deleteStudent(Model model, @PathVariable Long id) {
    studentServiceImpl.deleteStudent(id);
    // return "redirect:/students";
    List<Student> students = studentServiceImpl.getAllStudents();
    model.addAttribute("students", students);

    return "students/index";

  }

}
