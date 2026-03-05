package com.example.tienda19.repository;

import com.example.tienda19.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacturaRepository extends JpaRepository<FacturaEntity,Long> {



    // Devuelve todas las facturas con monto >= 1
    @Query(value = "SELECT * FROM factura WHERE factura_monto >= 1", nativeQuery = true)
    List<FacturaEntity> facturaAlmacenada();

    List<FacturaEntity> findByMontoFacturaGreaterThanEqual(Double monto);


    // No necesitas un método estático findALL(), JpaRepository ya tiene findAll()
}


