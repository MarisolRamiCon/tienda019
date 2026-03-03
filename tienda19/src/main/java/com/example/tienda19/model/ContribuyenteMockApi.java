package com.example.tienda19.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ContribuyenteMockApi {

    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechaRegistro;
    private boolean activo = true;
}