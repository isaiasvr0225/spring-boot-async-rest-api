package com.example.service;

import com.example.persistence.dao.CarDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {

    CompletableFuture<List<CarDto>> findAll();

    CompletableFuture<CarDto> findById(Long id);

    CompletableFuture<CarDto> save(CarDto carDto);

    CompletableFuture<Void> delete(Long id);
}
