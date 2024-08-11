package com.example.department.controller;

import com.example.department.model.Department;
import com.example.department.model.dto.DepartmentDto;
import com.example.department.service.DepartmentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl departmentService;

    private ModelMapper modelMapper=new ModelMapper();

    @GetMapping("/get-all-departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departmentList = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentList);
    }

    @GetMapping("/get-department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartment(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Department> deletedDepartment = departmentService.getDepartment(id);
        if (deletedDepartment.isPresent()) {
            departmentService.delete(deletedDepartment.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public void saveDepartment(@RequestBody DepartmentDto departmentDto){
        departmentService.save(modelMapper.map(departmentDto,Department.class));
    }

    @PostMapping("/update")
    public void updateDepartment(@RequestBody Department department){
        departmentService.save(department);
    }
}
