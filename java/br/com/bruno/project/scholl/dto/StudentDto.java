package br.com.bruno.project.scholl.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bruno.project.scholl.model.Student;

public class StudentDto {
	
	private int registration;
	private String name; 
	
	public StudentDto(Student student) {
		this.registration = student.getRegistration();
		this.name = student.getName();
	}

	public int getRegistration() {
		return registration;
	}

	public String getName() {
		return name;
	}

	public static List<StudentDto> convert(List<Student> students) {
		return students.stream().map(StudentDto::new).collect(Collectors.toList());
	}
	
}
