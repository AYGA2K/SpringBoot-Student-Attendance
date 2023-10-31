package ma.fstt.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import ma.fstt.serviceImpl.AbsenceServiceImpl;
import ma.fstt.serviceImpl.StudentServiceImpl;
import ma.fstt.entity.Absence;
import ma.fstt.entity.Student;

/**
 * AbsenceController
 */
@Controller
@RequestMapping("/absence")
public class AbsenceController {
  private final AbsenceServiceImpl absenceServiceImpl;
  private final StudentServiceImpl studentServiceImpl;
  private String dateStr;

  public AbsenceController(AbsenceServiceImpl absenceServiceImpl, StudentServiceImpl studentServiceImpl) {
    this.absenceServiceImpl = absenceServiceImpl;
    this.studentServiceImpl = studentServiceImpl;
    LocalDate date = LocalDate.now();
    this.dateStr = date.toString();
  }

  @GetMapping
  public String index(Model model) {

    List<Absence> absences = absenceServiceImpl.getAllAbsences(this.dateStr);
    model.addAttribute("absences", absences);
    return "absence/index";
  }

  @GetMapping("/{id}")
  @HxRequest
  public String show(Model model, @PathVariable Long id) {
    Absence absence = absenceServiceImpl.getAbsence(id);
    List<Student> students = absence.getStudents();
    model.addAttribute("students", students);
    return "absence/show";
  }

  @GetMapping("/create")
  @HxRequest
  public String create(Model model) {

    // check in absence table if there is absence with this date
    Absence absence = absenceServiceImpl.getAbsenceByDate(this.dateStr);
    if (absence == null) {
      List<Student> students = studentServiceImpl.getAllStudents();
      model.addAttribute("students", students);
      return "absence/create";
    }
    List<Absence> absences = absenceServiceImpl.getAllAbsences(this.dateStr);
    model.addAttribute("absences", absences);
    return "absence/index";
  }

  @PostMapping("/create")
  @HxRequest
  public String create(Model model,
      @RequestParam(value = "studentIds", required = false) List<Long> studentIds) {
    List<Student> students = new java.util.ArrayList<>();
    for (Long studentId : studentIds) {
      students.add(studentServiceImpl.getStudent(studentId));
    }
    LocalDate date = LocalDate.now();
    String dateStr = date.toString();
    Absence absence = new Absence();
    absence.setDate(dateStr);
    absence.setStudents(students);
    absenceServiceImpl.addAbsence(absence);

    // date of today to string
    // return "redirect:/absence/";
    List<Absence> absences = absenceServiceImpl.getAllAbsences(this.dateStr);
    model.addAttribute("absences", absences);
    return "absence/index";
  }

  // @GetMapping("/edit/{id}")
  // @HxRequest
  // public String edit(Model model, Long id) {
  // Absence absence = absenceServiceImpl.getAbsence(id);
  // model.addAttribute("absence", absence);
  // return "absence/edit";
  // }
  //
  // @PostMapping("/edit/{id}")
  // @HxRequest
  // public String edit(Model model, @ModelAttribute Absence absence,
  // @PathVariable Long id) {
  // absenceServiceImpl.updateAbsence(id, absence);
  // // return "redirect:/absence/";
  // List<Absence> absences = absenceServiceImpl.getAllAbsences(this.dateStr);
  // model.addAttribute("absences", absences);
  // return "absence/index";
  // }

  @DeleteMapping("/delete/{id}")
  @HxRequest
  public String delete(Model model, @PathVariable Long id) {
    absenceServiceImpl.deleteAbsence(id);
    List<Absence> absences = absenceServiceImpl.getAllAbsences(this.dateStr);
    model.addAttribute("absences", absences);
    return "absence/index";
  }
}
