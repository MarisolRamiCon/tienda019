package com.example.tienda19.repository;

import com.example.tienda19.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacturaRepository extends JpaRepository<FacturaEntity, Integer> {



    @Query(value = "SELECT * FROM factura_monto WHERE factura_monto >= 1", nativeQuery = true)
    public List<FacturaEntity> FacturaAlamacenada();

    static List<FacturaEntity> findALL() {
        return findALL();
    }



}
