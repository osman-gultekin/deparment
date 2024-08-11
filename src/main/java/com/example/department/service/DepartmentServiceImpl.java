package com.example.department.service;

import com.example.department.model.Department;
import com.example.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    @Override
    @Cacheable(value = "departmentsCache", key = "#id")
    public Optional<Department> getDepartment(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
