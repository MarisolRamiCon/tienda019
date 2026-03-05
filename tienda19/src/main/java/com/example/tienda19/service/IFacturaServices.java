package com.example.tienda19.service;

import com.example.tienda19.entity.FacturaEntity;

import java.util.List;
import java.util.Optional;

public interface IFacturaServices {
    public List<FacturaEntity> readALL();
   public Optional<FacturaEntity> readbyId(Integer id);
    public FacturaEntity create(FacturaEntity facturaEntity);
      public FacturaEntity update(Integer id,FacturaEntity facturaEntity );

    public void DelateById (Integer id);

}
