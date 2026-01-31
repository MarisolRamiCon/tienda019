package com.example.tienda19.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContribuyenteMockApi {
    private Integer id;
    private String nombre;
    private String apeido;
    private Date Fecha_registro;
    private boolean Activo;
}
