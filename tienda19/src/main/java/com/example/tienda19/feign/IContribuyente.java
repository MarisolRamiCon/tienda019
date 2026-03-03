package com.example.tienda19.feign;

import com.example.tienda19.model.ContribuyenteMockApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "presonas", url = "https://68d647ddc2a1754b426a2234.mockapi.io/api/v5/Personas")
public interface IContribuyente {

    @GetMapping("/persona")
    List<ContribuyenteMockApi> readALL();

    @GetMapping("/persona")
    ContribuyenteMockApi readById(@PathVariable("id") Integer id);

    @PostMapping("/persona")
    ContribuyenteMockApi create(@RequestBody ContribuyenteMockApi contribuyenteMockApi);

    @PutMapping("/persona/{id}")
    ContribuyenteMockApi update(@PathVariable("id") Integer id,
                                @RequestBody ContribuyenteMockApi contribuyenteMockApi);

    @DeleteMapping("/persona/{id}")
    String deleteLogico(@PathVariable("id") Integer id);
}

