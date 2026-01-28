package com.example.tienda19.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //anotation de lombok para el constructor sin argumentos
@AllArgsConstructor //anotation de lombok para el constructor con todos los argumentos
@Data //getter y setter
@Entity
@Table(name = "clientes")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "activo")
    private Boolean activo = true;

}