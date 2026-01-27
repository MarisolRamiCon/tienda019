package com.example.tienda19.repository;

import com.example.tienda19.entity.EmpleadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<EmpleadosEntity, Integer> {
}
