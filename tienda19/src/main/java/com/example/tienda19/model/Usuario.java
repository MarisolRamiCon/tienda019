package com.example.tienda19.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private Integer id;
    private String name;
    private String destino;
    private LocalDateTime hora;
}
