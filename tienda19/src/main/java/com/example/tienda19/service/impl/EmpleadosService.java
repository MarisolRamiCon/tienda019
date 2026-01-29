package com.example.tienda19.service.impl;

import com.example.tienda19.entity.EmpleadosEntity;
import com.example.tienda19.repository.EmpleadosRepository;
import com.example.tienda19.service.IEmpleadosService;
import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmpleadosService implements IEmpleadosService {
    @Autowired //Nos dice que vamos a hacer una inyeccion de dependencia
    EmpleadosRepository empleadosRepository;


    public List<EmpleadosEntity> readAll() {
        return empleadosRepository.findAll().stream().filter(
                empleados -> empleados.getActivo()).toList();
    }

    @Override
    public EmpleadosEntity readById(Integer id) {
        Optional<EmpleadosEntity> empleadoABuscar= empleadosRepository.findById(id);
        if(empleadoABuscar.isPresent()){
            return empleadoABuscar.get();
        }else{
            return null;
        }

    }

    @Override
    public EmpleadosEntity create(EmpleadosEntity empleado) {
        empleadosRepository.save(empleado);
        return empleado;
    }

    @Override
    public String updateById(Integer id, EmpleadosEntity empleado) {
        Optional<EmpleadosEntity> departamentoBuscado = empleadosRepository.findById(id);
        if(departamentoBuscado.isPresent()){
            EmpleadosEntity modificado= new EmpleadosEntity();
            modificado.setId(empleado.getId());
            //modificado.setM2(empleado.getM2());
            //modificado.setPrecio(empleado.getPrecio());
            empleadosRepository.save(modificado);
            return "Empleado actualizado";
        }else{
            return "Empleado no encontrado";
        }

    }

    @Override
    public String deleteById(Integer id) {
        Optional<EmpleadosEntity> empleadoABorrar = empleadosRepository.findById(id);
        if(empleadoABorrar.isPresent()){
            EmpleadosEntity empleados = empleadoABorrar.get();
            empleados.setActivo(false);
            empleadosRepository.save(empleados);
            return "Empleado borrado";
        }else{
            return "No existe el empleado";
        }

    }

}
