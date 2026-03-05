package com.example.tienda19.repository;

import com.example.tienda19.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;




    @Query(value = "SELECT * FROM factura WHERE factura_monto >= 1", nativeQuery = true)



}
