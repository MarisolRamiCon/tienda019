package com.example.tienda19.service;

import com.example.tienda19.entity.ProveedoresEntity;

import java.util.List;

public interface IProveedoresService {
    public List<ProveedoresEntity> readAll();
    public ProveedoresEntity readById(Integer id);
    public ProveedoresEntity create(ProveedoresEntity proveedoresEntity);
    public String update(Integer id,ProveedoresEntity proveedoresEntity);
    public Boolean delete(Integer id);

}
