package io.kulksud.springboot.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Siddharth This is the service layer.
 */

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	// dependency injection done for database (repository) layer
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {

		Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());
		if (studentByEmail.isPresent()) {
			throw new IllegalStateException("Student with email: " + student.getEmail() + " already present!");
		}
		studentRepository.save(student);

		System.out.println(student);
	}

	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new IllegalStateException("Student " + id + " not present!");
		} else {
			studentRepository.deleteById(id);
		}
	}
	
	@Transactional
	public void updateStudent(Long id, String name, String email, String dept) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("student id "+id+" not found"));
		
		if(name != null && name.length() > 0 && !Objects.equals(name, student.getName())) {
			student.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())) {
			Optional<Student> studentOption = studentRepository.findByEmail(email);
			if(studentOption.isPresent()) {
				throw new IllegalStateException("student with email "+email+" already present!");
			} else {
				student.setEmail(email);
			}
		}
		
		if(dept != null && dept.length() > 0 && !Objects.equals(dept, student.getDept())) {
			student.setDept(dept);
		}
	}
}
