package com.example.tienda19.feign;

import com.example.tienda19.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "UserClient",url = "https://6970453978fec16a63fd3f7a.mockapi.io/api/v1")
public interface UserClient {

    @GetMapping("/personas")
    public List<Usuario> readAll();

    @GetMapping("/personas/{id}")
    public Usuario readById(@PathVariable("id") Integer id);

    @PostMapping ("/personas")
    public Usuario create(@RequestBody Usuario usuario);

    @PutMapping("/personas/{id}")
    public Usuario update(@PathVariable("id")Integer id,@RequestBody Usuario usuario);

    @DeleteMapping("/personas/{id}")
    public void delete(@PathVariable("id") Integer id);



}
