package com.example.tienda19.request;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Transactional
    @Builder

    public class RequetsFactura  {
        private long id;
        private double montoFactura;
        private Integer codigoPostal;
        private String razonsocial;
        private String regimensocial;
    }