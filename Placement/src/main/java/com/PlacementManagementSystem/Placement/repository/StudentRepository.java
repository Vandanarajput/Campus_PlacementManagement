package com.PlacementManagementSystem.Placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlacementManagementSystem.Placement.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
