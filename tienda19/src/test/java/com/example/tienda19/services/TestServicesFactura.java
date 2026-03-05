package com.example.tienda19.services;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.repository.FacturaRepository;
import com.example.tienda19.request.RequetsFactura;
import com.example.tienda19.response.ResponseFactura;
import com.example.tienda19.service.impl.FacturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------- Test Create ----------
    @Test
    void testCreateFactura() {
        RequetsFactura request = new RequetsFactura();
        request.setRazonsocial("Empresa ABC");
        request.setCodigoPostal(12345);
        request.setRegimensocial("General");
        request.setMontoFactura(1000.0);

        FacturaEntity savedFactura = new FacturaEntity();
        savedFactura.setId(1L);
        savedFactura.setRazonsocial(request.getRazonsocial());
        savedFactura.setCodigoPostal(request.getCodigoPostal());
        savedFactura.setRegimensocial(request.getRegimensocial());
        savedFactura.setMontoFactura(request.getMontoFactura());

        when(facturaRepository.save(any(FacturaEntity.class))).thenReturn(savedFactura);

        ResponseFactura response = facturaService.create(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Empresa ABC", response.getRazonsocial());
        assertEquals(12345, response.getCodigoPostal());
        assertEquals("General", response.getRegimensocial());
        assertEquals(1000.0, response.getMontoFactura());

        verify(facturaRepository).save(any(FacturaEntity.class));
    }

    // ---------- Test Update ----------
    @Test
    void testUpdateFactura_Existente() {
        long facturaId = 1L;
        RequetsFactura request = new RequetsFactura();
        request.setRazonsocial("Empresa XYZ");
        request.setCodigoPostal(54321);
        request.setRegimensocial("Especial");
        request.setMontoFactura(1500.0);

        FacturaEntity existente = new FacturaEntity();
        existente.setId(facturaId);
        existente.setRazonsocial("Empresa Vieja");
        existente.setCodigoPostal(00000);
        existente.setRegimensocial("Antiguo");
        existente.setMontoFactura(1000.0);

        when(facturaRepository.findById(facturaId)).thenReturn(Optional.of(existente));
        when(facturaRepository.save(any(FacturaEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseFactura response = facturaService.update(facturaId, request);

        assertNotNull(response);
        assertEquals(facturaId, response.getId());
        assertEquals("Empresa XYZ", response.getRazonsocial());
        assertEquals(54321, response.getCodigoPostal());
        assertEquals("Especial", response.getRegimensocial());
        assertEquals(1500.0, response.getMontoFactura());

        verify(facturaRepository).findById(facturaId);
        verify(facturaRepository).save(existente);
    }

    @Test
    void testUpdateFactura_NoExistente() {
        long facturaId = 99L;
        RequetsFactura request = new RequetsFactura();
        when(facturaRepository.findById(facturaId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> facturaService.update(facturaId, request));
        assertEquals("Factura no encontrada con id: 99", exception.getMessage());

        verify(facturaRepository).findById(facturaId);
        verify(facturaRepository, never()).save(any());
    }

    // ---------- Test readAll ----------
    @Test
    void testReadAll() {
        FacturaEntity f1 = new FacturaEntity();
        f1.setRazonsocial("A");
        f1.setRegimensocial("R1");

        FacturaEntity f2 = new FacturaEntity();
        f2.setRazonsocial(null); // Se filtrará
        f2.setRegimensocial("R2");

        List<FacturaEntity> facturas = List.of(f1, f2);
        when(facturaRepository.findAll()).thenReturn(facturas);

        List<FacturaEntity> result = facturaService.readALL();

        assertEquals(1, result.size());
        assertEquals(f1, result.get(0));
    }

    // ---------- Test readById ----------
    @Test
    void testReadById() {
        FacturaEntity factura = new FacturaEntity();
        factura.setId(1L);
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        Optional<FacturaEntity> result = facturaService.readById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    // ---------- Test deleteById ----------
    @Test
    void testDeleteById_Existente() {
        FacturaEntity factura = new FacturaEntity();
        factura.setId(1L);

        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        facturaService.DelateById(1L);

        verify(facturaRepository).delete(factura);
    }

    @Test
    void testDeleteById_NoExistente() {
        when(facturaRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> facturaService.DelateById(99L));
        assertEquals("Factura no encontrada con id: 99", exception.getMessage());
    }

    // ---------- Test findByMontoFacturaGreaterThanEqual ----------
    @Test
    void testFindByMontoFacturaGreaterThanEqual_Existente() {
        FacturaEntity f1 = new FacturaEntity();
        f1.setMontoFactura(1200.0);
        List<FacturaEntity> facturas = List.of(f1);

        when(facturaRepository.findByMontoFacturaGreaterThanEqual(1000.0)).thenReturn(facturas);

        List<FacturaEntity> result = facturaService.findByMontoFacturaGreaterThanEqual(1000.0);

        assertEquals(1, result.size());
        assertEquals(f1, result.get(0));
    }

    @Test
    void testFindByMontoFacturaGreaterThanEqual_Vacio() {
        when(facturaRepository.findByMontoFacturaGreaterThanEqual(2000.0)).thenReturn(new ArrayList<>());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> facturaService.findByMontoFacturaGreaterThanEqual(2000.0));
        assertEquals("No se encontraron facturas con ese monto", exception.getMessage());
    }

    @Test
    void testFindByMontoFacturaGreaterThanEqual_MontoNegativo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> facturaService.findByMontoFacturaGreaterThanEqual(-1.0));
        assertEquals("El monto debe ser mayor o igual a 0", exception.getMessage());
    }
}