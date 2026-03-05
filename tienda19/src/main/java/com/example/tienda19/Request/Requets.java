package com.example.tienda19.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Transactional
    @jakarta.transaction.Transactional
    @Table(name ="factura")
    public class Requets {


        private int id;
        private String razonsociaL;
        private String regimensocial;

}
