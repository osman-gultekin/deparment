package com.example.department.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_seq1")
    @SequenceGenerator(name = "department_id_seq1", sequenceName = "department_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String location;
}
