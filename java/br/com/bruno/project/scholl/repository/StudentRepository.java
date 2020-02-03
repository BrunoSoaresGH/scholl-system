package br.com.bruno.project.scholl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bruno.project.scholl.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	Optional<Student> findByRegistration(int registration);

	void deleteByRegistration(int registration);
	
}
