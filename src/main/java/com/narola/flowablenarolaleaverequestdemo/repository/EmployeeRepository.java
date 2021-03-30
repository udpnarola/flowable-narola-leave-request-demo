package com.narola.flowablenarolaleaverequestdemo.repository;

import com.narola.flowablenarolaleaverequestdemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    Optional<EmployeeEntity> findByEmail(String email);
}
