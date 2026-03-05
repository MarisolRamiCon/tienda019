package com.example.tienda19.entity;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name ="factura")
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_factura")
    private long id;
    @Column (name ="razon_social", columnDefinition="VARCHAR(255)", unique = true)

    private String razonsocial;
    @Column (name ="codigo_postal")

    private Integer codigoPostal;
    @Column (name ="regimen_social")

    private String regimensocial;
    @Column (name ="monto_factura",columnDefinition="DOUBLE")

    private double montoFactura;
}
