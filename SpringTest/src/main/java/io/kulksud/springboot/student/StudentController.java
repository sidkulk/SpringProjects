package io.kulksud.springboot.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author Siddharth
 *	This is the API layer
 */
@RestController
@RequestMapping(path = "api/v1")
public class StudentController {

	private final StudentService studentService;

	//dependency injection done for service layer
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/student")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping("/addstud")
	public void registerStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path = "/delstud/{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}
	
	@PutMapping(path = "/updstud/{studentId}")
	public void updateStudent(@PathVariable("studentId") Long stidentId, 
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) String email, 
			@RequestParam(required = false) String dept) {
		studentService.updateStudent(stidentId, name, email, dept);
	}
}
