package com.example.tienda19.service.impl;


import com.example.tienda19.feign.IContribuyente;
import com.example.tienda19.model.ContribuyenteMockApi;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesContribuyente implements IContribuyente {
    private final IContribuyente iContribuyente;
    public ServicesContribuyente(IContribuyente iContribuyente) {
        this.iContribuyente = iContribuyente;
    }
    @Override
public List<ContribuyenteMockApi> readALL() {return iContribuyente.readALL();
    }
@Override
    public ContribuyenteMockApi readById(Integer id){
    return  iContribuyente.readById(id);
    }
    @Override
    public ContribuyenteMockApi create(ContribuyenteMockApi contribuyenteMockApi){return iContribuyente.create(contribuyenteMockApi);}


    public ContribuyenteMockApi update(@PathVariable("id") int id, @RequestBody ContribuyenteMockApi contribuyenteMockApi) {
        return iContribuyente.update(id, contribuyenteMockApi);
    }
    @Override
    public  String delateLogico(@PathVariable("id") Integer id) {
        try {
            Optional<ContribuyenteMockApi> contribuyenteBuscado = Optional.ofNullable(iContribuyente.readById(id));

            return contribuyenteBuscado
                    .map(contribuyente -> {
                        contribuyente.setActivo(false);  // Cambia el estado de "activo" a false
                        iContribuyente.update(id, contribuyente);  // Actualiza el contribuyente
                        return "Contribuyente con el ID: " + id + " ha sido desactivado.";  // Mensaje de éxito
                    })
                    .orElse("Contribuyente no encontrado con el ID: " + id);  // Mensaje si no se encontró al contribuyente
        } catch (FeignException e) {
            // Captura errores de Feign
            return "Error al realizar la solicitud al servicio externo: " + e.getMessage();
        } catch (Exception e) {
            // Captura cualquier otro error inesperado
            return "Ocurrió un error inesperado: " + e.getMessage();
        }
    }
    }


