package com.example.department.service;

import com.example.department.model.Department;
import com.example.department.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cacheManager = new ConcurrentMapCacheManager("departmentsCache");
    }

    @Test
    void getAllDepartments() {
        Department department1 = new Department();
        Department department2 = new Department();
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));

        List<Department> departments = departmentService.getAllDepartments();

        assertEquals(2, departments.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void getDepartment_whenExists() {
        Department department = new Department();
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartment(1L);

        assertEquals(department, result.orElse(null));
        verify(departmentRepository, times(1)).findById(anyLong());
    }

    @Test
    void getDepartment_whenNotExists() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Department> result = departmentService.getDepartment(1L);

        assertEquals(Optional.empty(), result);
        verify(departmentRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        Department department = new Department();
        departmentService.save(department);

        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void delete() {
        Department department = new Department();
        departmentService.delete(department);

        verify(departmentRepository, times(1)).delete(department);
    }

    @Test
    void testGetDepartmentCaching() {
        Department department = new Department();
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        Cache cache = cacheManager.getCache("departmentsCache");
        cache.put(1L,department);

        Optional<Department> result1 = departmentService.getDepartment(1L);
        assertEquals(department, result1.orElse(null));

        Optional<Department> result2 = departmentService.getDepartment(1L);
        assertEquals(department, result2.orElse(null));

        assertEquals(department, cache.get(1L).get());
    }
}