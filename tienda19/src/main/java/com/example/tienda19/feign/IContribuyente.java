package com.example.tienda19.feign;

import com.example.tienda19.model.ContribuyenteMockApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface IContribuyente {
    List<ContribuyenteMockApi> readALL();

    ContribuyenteMockApi create(@RequestBody ContribuyenteMockApi contribuyenteMockApi);

}

