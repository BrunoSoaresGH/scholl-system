package br.com.bruno.project.scholl.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bruno.project.scholl.dto.StudentDto;
import br.com.bruno.project.scholl.form.StudentForm;
import br.com.bruno.project.scholl.model.Student;
import br.com.bruno.project.scholl.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public List<StudentDto> getStudents() {
		List<Student> students = studentRepository.findAll();
		return StudentDto.convert(students);
	}

	@GetMapping("/{registration}")
	public ResponseEntity<Student> getStudent(@PathVariable int registration) {
		Optional<Student> student = studentRepository.findByRegistration(registration);
		if (student.isPresent()) {
			return ResponseEntity.ok(student.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentForm form, UriComponentsBuilder uriBuilder) {
		Student student = form.convert();
		studentRepository.save(student);

		URI uri = uriBuilder.path("/students/{registration}").buildAndExpand(student.getRegistration()).toUri();

		return ResponseEntity.created(uri).body(new StudentDto(student));
	}

	@PutMapping("/{registration}")
	@Transactional
	public ResponseEntity<StudentDto> updateStudent(@PathVariable int registration, @RequestBody @Valid StudentForm form) {
		Optional<Student> optional = studentRepository.findByRegistration(registration);
		if (optional.isPresent()) {
			Student student = form.update(registration, studentRepository);
			return ResponseEntity.ok(new StudentDto(student));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{registration}")
	@Transactional
	public ResponseEntity<Student> deleteStudent(@PathVariable int registration) {
		Optional<Student> optional = studentRepository.findByRegistration(registration);
		if (optional.isPresent()) {
			studentRepository.deleteByRegistration(registration);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
