package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    private Environment env;
    private CatalogService catalogService;

    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/heath_check")
    public String status() {
        return String.format("It's Working in Catalog Service PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseCatalog>> getUsers() {
        List<CatalogDto> userDtoList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();

        userDtoList.forEach(e -> result.add(new ModelMapper().map(e, ResponseCatalog.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
