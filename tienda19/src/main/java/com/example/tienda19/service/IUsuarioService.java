package com.example.tienda19.service;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.model.Usuario;
import com.example.tienda19.request.RequetsFactura;
import com.example.tienda19.response.ResponseFactura;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> readAll();
    public Usuario readById(Integer id);
    public Usuario create(Usuario usuario);
    public Usuario update(Integer id,Usuario usuario);
    public void delete(Integer id);

    interface IFacturaServices {
        public List<FacturaEntity> readALL();
    public Optional<FacturaEntity> readById(long id);
    public ResponseFactura create(RequetsFactura requetsFactura);
    public ResponseFactura update(long id, RequetsFactura requetsFactura);
    public void DelateById (long id);
    public List<FacturaEntity> findByMontoFacturaGreaterThanEqual(Double monto);
    }
}
