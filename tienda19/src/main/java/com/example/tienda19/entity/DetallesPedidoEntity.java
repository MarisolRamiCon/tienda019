package com.example.tienda19.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data

    @Entity
    @Table(name = "detalles_pedido")
    public class DetallesPedidoEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;
        @Column(name = "pedido")
        private Integer pedido;
        @Column(name = "producto")
        private String producto;
        @Column(name = "cantidad")
        private Integer cantidad;
        @Column(name = "precio_unitario")
        private Integer precioUnitario;
        @Column(name = "activo")
        private Boolean activo;
    }

