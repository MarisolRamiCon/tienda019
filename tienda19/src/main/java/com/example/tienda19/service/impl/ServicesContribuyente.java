package com.example.tienda19.service.impl;

import com.example.tienda19.feign.IContribuyente;

import com.example.tienda19.model.ContribuyenteMockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesContribuyente implements IContribuyente {


    private final IContribuyente iContribuyente;
    @Autowired
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

    public ContribuyenteMockApi update(int id, ContribuyenteMockApi contribuyenteMockApi) { return iContribuyente.update(id, contribuyenteMockApi);}



    @Override
    public String delateLogico(Integer id){////Seleciono
    Optional<ContribuyenteMockApi> cotribuyenteBuscado = Optional.ofNullable(iContribuyente.readById(id));///bysco mi elemento
        if(cotribuyenteBuscado.isPresent()){
            ContribuyenteMockApi contribuyente2 = cotribuyenteBuscado.get();
            contribuyente2.setActivo(false);
            iContribuyente.update(id, contribuyente2);
            return "Contribuyente con el ID: " + id;
        } else {
            return "Contribuyente no encontrado con el ID: " + id;
        }
    }
    }


