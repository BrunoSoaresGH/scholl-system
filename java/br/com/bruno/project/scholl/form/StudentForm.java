package br.com.bruno.project.scholl.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.bruno.project.scholl.model.Student;
import br.com.bruno.project.scholl.repository.StudentRepository;

public class StudentForm {
	
	@NotNull @NotEmpty @Length(min = 3)
	private String name;
	@NotNull @NotEmpty
	private String birthDay; 
	@NotNull @NotEmpty
	private String sex;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getSex() {
		return sex;
	}
	public void setSexo(String sex) {
		this.sex = sex;
	}
	
	public Student convert() {
		Student student = new Student(name, birthDay, sex);
		return student;
	}
	
	public Student update(int registration, StudentRepository studentRepository) {
		Student student = studentRepository.getOne(registration);
		student.setName(name);
		student.setBirthDay(birthDay);
		student.setSex(sex);
		return student;
	} 
	
}
