package com.example.tienda19.controller;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.request.RequetsFactura;
import com.example.tienda19.response.ResponseFactura;
import com.example.tienda19.service.IFacturaServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacturaController.class)
class FacturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IFacturaServices facturaService;

    @Autowired
    private ObjectMapper objectMapper;
    private FacturaEntity factura;
    private ResponseFactura responseFactura;



    @BeforeEach
    void setUp() {
        factura = new FacturaEntity();
        factura.setId(1L);
        factura.setRazonsocial("Empresa ABC");
        factura.setCodigoPostal(12345);
        factura.setRegimensocial("General");
        factura.setMontoFactura(1000.0);

        responseFactura = new ResponseFactura();
        responseFactura.setId(1L);
        responseFactura.setRazonsocial("Empresa ABC");
        responseFactura.setCodigoPostal(12345);
        responseFactura.setRegimensocial("General");
        responseFactura.setMontoFactura(1000.0);
    }

    // Método auxiliar para convertir objeto a JSON
    private String toJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    // ----------------- TEST GET /facturas -----------------
    @Test
    void testReadAll() throws Exception {
        Mockito.when(facturaService.readALL()).thenReturn(List.of(factura));

        mockMvc.perform(get("/api/v1/facturas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].razonsocial").value("Empresa ABC"));
    }

    @Test
    void testReadById() throws Exception {
        // Caso encontrado
        Mockito.when(facturaService.readById(1L)).thenReturn(Optional.of(factura));
        mockMvc.perform(get("/api/v1/facturas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        // Caso no encontrado
        Mockito.when(facturaService.readById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/facturas/99"))
                .andExpect(status().isNotFound());
    }

    // ----------------- TEST POST /facturas -----------------
    @Test
    void testCreate() throws Exception {
        RequetsFactura request = new RequetsFactura();
        request.setRazonsocial("Empresa ABC");
        request.setCodigoPostal(12345);
        request.setRegimensocial("General");
        request.setMontoFactura(1000.0);

        Mockito.when(facturaService.create(any(RequetsFactura.class)))
                .thenReturn(responseFactura);

        mockMvc.perform(post("/api/v1/facturas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    // ----------------- TEST PUT /{id} -----------------
    @Test
    void testUpdate() throws Exception {
        RequetsFactura request = new RequetsFactura();
        request.setRazonsocial("Empresa XYZ");
        request.setCodigoPostal(54321);
        request.setRegimensocial("Especial");
        request.setMontoFactura(1500.0);

        ResponseFactura updatedResponse = new ResponseFactura();
        updatedResponse.setId(1L);
        updatedResponse.setRazonsocial("Empresa XYZ");
        updatedResponse.setCodigoPostal(54321);
        updatedResponse.setRegimensocial("Especial");
        updatedResponse.setMontoFactura(1500.0);

        Mockito.when(facturaService.update(eq(1L), any(RequetsFactura.class)))
                .thenReturn(updatedResponse);

        mockMvc.perform(put("/api/v1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.razonsocial").value("Empresa XYZ"))
                .andExpect(jsonPath("$.montoFactura").value(1500.0));
    }

    // ----------------- TEST DELETE /{id} -----------------
    @Test
    void testDelete() throws Exception {
        // Caso encontrado
        Mockito.doNothing().when(facturaService).DelateById(1L);
        mockMvc.perform(delete("/api/v1/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Factura eliminada correctamente."));

        // Caso no encontrado
        Mockito.doThrow(new RuntimeException("Factura no encontrada con id: 99"))
                .when(facturaService).DelateById(99L);
        mockMvc.perform(delete("/api/v1/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Factura no encontrada con id: 99"));
    }

    // ----------------- TEST GET /facturas/monto -----------------
    @Test
    void testFindByMonto() throws Exception {
        Mockito.when(facturaService.findByMontoFacturaGreaterThanEqual(1000.0))
                .thenReturn(List.of(factura));

        mockMvc.perform(get("/api/v1/facturas/monto")
                        .param("monto", "1000.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].montoFactura").value(1000.0));
    }
}