package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService{
    private CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<CatalogDto> getAllCatalogs() {
        List<CatalogDto> result = new ArrayList<>();

        catalogRepository.findAll().forEach(e -> result.add(new ModelMapper().map(e, CatalogDto.class)));

        return result;
    }
}
