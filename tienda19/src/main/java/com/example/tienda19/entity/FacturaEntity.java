package com.example.tienda19.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="factura")
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_factura")
    private int id;
    @Column (name ="razon_social" )
    private String RazonsociaL;
    @Column (name ="codigo_postal")
    private int CodigoPostal;
    @Column (name ="regimen_social")
    private String Regimensocial;
    @Column (name ="monto_factura")
    private int MontoFactura;
}
