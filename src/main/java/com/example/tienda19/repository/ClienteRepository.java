package com.example.tienda19.repository;

import com.example.tienda19.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteRepository,Integer> {

    public List<ClienteEntity> findBynombreLessThanAnddireccionLessThan(String nombre, String direccion);

    //Crear un metodo personalizado con consultas o querys
    @Query(value = "select * from clientes where nombre>=:nombre and direccion>=:direccion",nativeQuery = true)
    public List<ClienteEntity> nombreAnddireccionQuery(String nombre, String direccion);

}
