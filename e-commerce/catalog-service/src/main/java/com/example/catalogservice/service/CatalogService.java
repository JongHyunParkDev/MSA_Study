package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;

import java.util.List;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
