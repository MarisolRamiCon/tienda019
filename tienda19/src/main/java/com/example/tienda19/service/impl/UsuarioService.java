package com.example.tienda19.service.impl;

import com.example.tienda19.exceptions.ExternalServiceException;
import com.example.tienda19.exceptions.NotFoundException;
import com.example.tienda19.feign.UserClient;
import com.example.tienda19.model.Usuario;
import com.example.tienda19.service.IUsuarioService;
import feign.FeignException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UserClient userClient;

    @Override
    public List<Usuario> readAll() {
        try {
            return userClient.readAll();
        } catch (FeignException e) {
            throw new ExternalServiceException("Error al obtener usuarios", e);
        }
    }

    @Override
    public Usuario readById(Integer id) {
        try {
            return userClient.readById(id);
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado", e);
        } catch (FeignException e) {
            throw new ExternalServiceException("Error al consultar usuario", e);
        }
    }

    @Override
    public Usuario create(Usuario usuario) {
        try {
            return userClient.create(usuario);
        } catch (FeignException e) {
            throw new ExternalServiceException("Error al crear usuario", e);
        }
    }

    @Override
    public Usuario update(Integer id, Usuario usuario) {
        try {
            return userClient.update(id, usuario);
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado", e);
        } catch (FeignException e) {
            throw new ExternalServiceException("Error al actualizar usuario", e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            userClient.delete(id);

        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado", e);
        } catch (FeignException e) {
            throw new ExternalServiceException("Error al eliminar usuario", e);
        }
    }
}