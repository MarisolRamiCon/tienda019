package com.example.tienda19.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
    @NoArgsConstructor
    @Data

    @Entity
    @Table(name = "empleados")
    public class EmpleadosEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;
        @Column(name = "nombre")
        private String nombre;
        @Column(name = "apellido")
        private String apellido;
        @Column(name = "puesto")
        private String puesto;
        @Column(name = "salario")
        private Integer salario;
        @Column(name = "fecha_de_contratacion")
        private LocalDateTime fechaDeContratacion;
        @Column(name = "activo")
        private Boolean activo;

    }

