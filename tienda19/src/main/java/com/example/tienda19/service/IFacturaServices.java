package com.example.tienda19.service;

import com.example.tienda19.entity.FacturaEntity;

import java.util.List;
import java.util.Optional;

public interface IFacturaServices {
    public List<FacturaEntity> readALL();
   public Optional<FacturaEntity> readbyId(Integer id);
    public FacturaEntity update(FacturaEntity facturaEntity);
    public FacturaEntity create(FacturaEntity facturaEntity);
    public String delateById (Integer id);

}
