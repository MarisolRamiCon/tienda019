package com.example.tienda19.service.impl;

import com.example.tienda19.entity.ProveedoresEntity;
import com.example.tienda19.feign.IContribuyente;
import com.example.tienda19.model.ContribuyenteMockApi;
import com.example.tienda19.repository.ProveedoresRepository;
import com.example.tienda19.service.IProveedoresService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProveedoresService implements IProveedoresService {
    @Autowired
    ProveedoresRepository proveedoresRepository;

    @Override
    public List<ProveedoresEntity> readAll() {
        log.info("Se consultó exitosamente la lista de proveedores");
        return proveedoresRepository.findAll()
                .stream()
                .filter(proveedor -> proveedor.getNombreEmpresa() != null && !proveedor.getNombreEmpresa().isBlank())
                .toList();
    }


    @Override
    public ProveedoresEntity readById(Integer id) {
        Optional<ProveedoresEntity> proveedoresABuscar = proveedoresRepository.findById(id);
        if (proveedoresABuscar.isPresent()) {
            log.info("provedor encontrado con id={}",id);
            return proveedoresABuscar.get();
        } else {
            log.warn("provedor no encontrado con id={}",id);
            return null;
        }
    }

    @Override
    public ProveedoresEntity create(ProveedoresEntity proveedores) {
        proveedoresRepository.save(proveedores);
        log.info("proveedor creado exitosamente");
        return proveedores;
    }

    @Override
    public String update(Integer id, ProveedoresEntity proveedores) {
        Optional<ProveedoresEntity> proveedoresABuscar = proveedoresRepository.findById(id);
        if (proveedoresABuscar.isPresent()) {
            ProveedoresEntity existente = proveedoresABuscar.get();
            log.info("proveedor encontrado exitosamente",existente);
            existente.setNombreEmpresa(proveedores.getNombreEmpresa());
            existente.setContacto(proveedores.getContacto());
            existente.setCorreoElectronico(proveedores.getCorreoElectronico());
            existente.setTelefono(proveedores.getTelefono());

            proveedoresRepository.save(existente);
            log.info("proveedor actualizado exitosamente");
            return "Proveedor actualizado";
        } else {
            log.warn("proveedor no encontrado con id={}",id);
            return "Proveedor no encontrado";
        }
    }
    public Boolean delete(Integer id) {
        Optional<ProveedoresEntity> proveedoresABorrar = proveedoresRepository.findById(id);
        if (proveedoresABorrar.isPresent()) {
            ProveedoresEntity proveedor = proveedoresABorrar.get();
            proveedor.setActivo(false); // baja lógica
            proveedoresRepository.save(proveedor);
            return true;
        } else {
            return false;
        }
    }

    public String obtnerContacto(Integer id) {
        return proveedoresRepository.findContactoById(id);
    }

    @Service
    public static class ServicesContribuyente implements IContribuyente {
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



        public ContribuyenteMockApi update(Integer id, ContribuyenteMockApi contribuyenteMockApi) {
            return iContribuyente.update(id, contribuyenteMockApi);
        }




        @Override
        public String deleteLogico(Integer id) {
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
}
