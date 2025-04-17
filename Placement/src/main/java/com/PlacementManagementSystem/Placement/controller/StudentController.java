package com.PlacementManagementSystem.Placement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.PlacementManagementSystem.Placement.model.Student;
import com.PlacementManagementSystem.Placement.service.StudentService;

@Controller
@RequestMapping("/admin/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// Show all students
	@GetMapping
	public String viewStudentsPage(Model model) {
		List<Student> studentList = studentService.getAllStudents();
		model.addAttribute("students", studentList);
		return "dashboard/students"; // students.html
	}

	// Show form to add new student
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("student", new Student());
		return "dashboard/add_students"; // add_student.html
	}

	// Handle saving of new student
	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	// Delete student by id
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}

	// Show form to edit existing student
	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "dashboard/edit_student"; // edit_student.html
	}

	// Handle update of student after editing
	@PostMapping("/update/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student updatedStudent) {
		Student existingStudent = studentService.getStudentById(id);

		existingStudent.setName(updatedStudent.getName());
		existingStudent.setEmail(updatedStudent.getEmail());
		existingStudent.setPhone(updatedStudent.getPhone());
		existingStudent.setDepartment(updatedStudent.getDepartment());
		existingStudent.setResumeLink(updatedStudent.getResumeLink());

		studentService.saveStudent(existingStudent);
		return "redirect:/students";
	}
}
