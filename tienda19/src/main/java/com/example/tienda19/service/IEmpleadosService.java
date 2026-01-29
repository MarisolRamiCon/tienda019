package com.example.tienda19.service;

import com.example.tienda19.entity.EmpleadosEntity;

import java.util.List;

public interface IEmpleadosService {
        //public List<EmpleadosEntity> readAll();
    //Buscar por id a los Empleados
    public EmpleadosEntity readById(Integer id);
    //Metodos CRUD C-CREATE, R-READ, U - UPDATE, D- DELETE
    public EmpleadosEntity create(EmpleadosEntity empleados);
    public String updateById(Integer id, EmpleadosEntity empleados);
    public String deleteById(Integer id);
}

