package com.example.tienda19.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "proveedores")
public class ProveedoresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "activo")
    private Boolean activo = true;

}
