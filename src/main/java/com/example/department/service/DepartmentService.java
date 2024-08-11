package com.example.department.service;

import com.example.department.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();

    Optional<Department> getDepartment(Long id);

    void save(Department department);

    void delete(Department department);
}
