package com.example.tienda19.service;

import com.example.tienda19.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> readAll();
    public Usuario readById(Integer id);
    public Usuario create(Usuario usuario);
    public Usuario update(Integer id,Usuario usuario);
    public void delete(Integer id);
}
