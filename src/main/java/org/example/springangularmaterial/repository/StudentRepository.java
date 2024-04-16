package org.example.springangularmaterial.repository;

import org.example.springangularmaterial.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByCode(String code);
    List<Student> findByprogramId(String programId);
}
