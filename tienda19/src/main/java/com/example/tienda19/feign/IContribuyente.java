package com.example.tienda19.feign;

import com.example.tienda19.model.ContribuyenteMockApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "Rh" , url="https://68d647ddc2a1754b426a2234.mockapi.io/api/v1/Aspirante")
public interface IContribuyente {
@GetMapping("/Rh")
    List<ContribuyenteMockApi> readALL();
@PostMapping("/Rh")
ContribuyenteMockApi readById (@PathVariable("id") Integer id);
@DeleteMapping("/Rh/{id}")
ContribuyenteMockApi create (@RequestBody ContribuyenteMockApi contribuyenteMockApi);

@PutMapping ContribuyenteMockApi update(@PathVariable("id") int id, @RequestBody ContribuyenteMockApi contribuyenteMockApi);
@GetMapping("/Rh/{id}")

    String delateLogico(@PathVariable("id") Integer id);


}
