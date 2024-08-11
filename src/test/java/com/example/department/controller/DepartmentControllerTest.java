package com.example.department.controller;

import com.example.department.model.Department;
import com.example.department.model.dto.DepartmentDto;
import com.example.department.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class DepartmentControllerTest {

    @Mock
    private DepartmentServiceImpl departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDepartments() {
        Department department1 = new Department();
        Department department2 = new Department();
        when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(department1, department2));

        ResponseEntity<List<Department>> response = departmentController.getAllDepartments();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getDepartment_whenExists() {
        Department department = new Department();
        when(departmentService.getDepartment(anyLong())).thenReturn(Optional.of(department));

        ResponseEntity<Department> response = departmentController.getDepartment(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(department, response.getBody());
    }

    @Test
    void getDepartment_whenNotExists() {
        when(departmentService.getDepartment(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Department> response = departmentController.getDepartment(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void delete_whenDepartmentExists() {
        Department department = new Department();
        when(departmentService.getDepartment(anyLong())).thenReturn(Optional.of(department));

        ResponseEntity<String> response = departmentController.delete(1L);

        verify(departmentService, times(1)).delete(department);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void delete_whenDepartmentNotExists() {
        when(departmentService.getDepartment(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<String> response = departmentController.delete(1L);

        verify(departmentService, times(0)).delete(any());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void saveDepartment() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentController.saveDepartment(departmentDto);

        verify(departmentService, times(1)).save(any(Department.class));
    }

    @Test
    void updateDepartment() {
        Department department = new Department();
        departmentController.updateDepartment(department);

        verify(departmentService, times(1)).save(department);
    }
}