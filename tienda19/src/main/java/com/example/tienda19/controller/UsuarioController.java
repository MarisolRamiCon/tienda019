package com.example.tienda19.controller;

import com.example.tienda19.model.Usuario;
import com.example.tienda19.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/personas")
    public List<Usuario> readAll() {
        return usuarioService.readAll();
    }
    @GetMapping("/personas/{id}")
    public Usuario readById(@PathVariable("id") Integer id) {
        return usuarioService.readById(id);
    }
    @PostMapping("/personas")
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);//corregir
    }
    @PutMapping("/personas/{id}")
    public Usuario update(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario);
    }
    @DeleteMapping("/personas/{id}")
    public void delete(@PathVariable("id") Integer id) {
        usuarioService.delete(id);
    }
}
