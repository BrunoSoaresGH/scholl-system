package br.com.bruno.project.scholl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int registration;
	private String name;
	private String birthDay; 
	private String sex;
	
	public Student() {}
	
	public Student(String name, String birthDay, String sex) {
		this.name = name;
		this.birthDay = birthDay;
		this.sex = sex;
	}

	public int getRegistration() {
		return registration;
	}

	public String getName() {
		return name;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public String getSex() {
		return sex;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
		
}
