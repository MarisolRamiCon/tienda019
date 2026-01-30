package com.example.tienda19.repository;

import com.example.tienda19.entity.ProveedoresEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProveedoresRepository extends JpaRepository<ProveedoresEntity,Integer> {

    //consulta solo el contacto de un proveedor por id

    @Query(value = "SELECT contacto FROM proveedores WHERE id = :id", nativeQuery = true)
    String findContactoById(@Param("id") Integer id);


}
