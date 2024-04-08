package com.example.service;

import com.example.persistence.dao.CarDto;
import com.example.persistence.mapper.CarMapper;
import com.example.persistence.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public @Service class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<List<CarDto>> findAll() {
        return CompletableFuture.completedFuture(this.carRepository.findAllCars().stream()
                .map(this.carMapper::toDto)
                .toList());
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<CarDto> findById(Long id) {

        return CompletableFuture.completedFuture(this.carRepository.findById(id)
                .map(this.carMapper::toDto)
                .orElse(null));

    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<CarDto> save(CarDto carDto) {
        return CompletableFuture.completedFuture(this.carMapper.toDto(this.carRepository.save(this.carMapper.toEntity(carDto))));
    }


    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Void> delete(Long id) {

        if (!this.carRepository.existsById(id)) return CompletableFuture.runAsync(() -> {
            throw new RuntimeException("Car with id " + id + " not found");
        });

        return CompletableFuture.runAsync(() -> this.carRepository.deleteById(id));
    }
}
