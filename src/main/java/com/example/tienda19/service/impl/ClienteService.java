package com.example.tienda19.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tienda19.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {
    @Autowired //Nos dice que vamos a hacer una inyeccion de dependencia
    ClienteRepository clienteRepository;

    @Override
    public List<ClienteEntity> readAll() {
        return clienteRepository.findAll().stream().filter(clientes -> clientes.getActivo()).toList();
    }

    @Override
    public ClienteEntity readById(Integer id) {
        Optional<ClienteEntity> clienteAbuscar= clienteRepository.findById(id);
        if(clienteAbuscar.isPresent()){
            return clienteAbuscar.get();
        }else{
            return null;
        }
    }

    @Override
    public ClienteEntity create(ClienteEntity clientes) {
        clienteRepository.save(clientes);
        return clientes;
    }
    @Override
    public String updateById(Integer id, ClienteEntity clientes) {
        Optional<ClienteEntity> clienteBuscado = clienteRepository.findById(id);
        if(clienteBuscado.isPresent()){
            ClienteEntity modificado= new ClienteEntity();
            modificado.setidCliente(clientes.getIdCliente());
            modificado.setnombre(clientes.getNombre());
            modificado.setdireccion(clientes.getDireccion());
            clienteRepository.save(modificado);
            return "Departamento actualizado";
        }else{
            return "Departamento no encontrado";
        }

    }

    @Override
    public String deleteById(Integer id) {
        Optional<ClienteEntity> clienteABorrar = clienteRepository.findById(id);
        if(clienteABorrar.isPresent()){
            ClienteEntity clientes = clienteABorrar.get();
            clientes.setActivo(false);
            clienteRepository.save(clientes);
            return "Departamento borrado";
        }else{
            return "No hay tal departamento";
        }
    }


    @Override
    public List<ClienteEntity> nombreAnddireccion(String nombre, String direccion) {
        return clienteRepository.findBynombreLessThanAnddireccionLessThan(nombre,direccion);
    }

    @Override
    public List<ClienteEntity> nombreAnddireccionQ(String nombre, String direccion) {
        return clienteRepository.nombreAnddireccionQuery(nombre,direccion);
    }

}









