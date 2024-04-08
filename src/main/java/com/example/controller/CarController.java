package com.example.controller;

import com.example.persistence.dao.CarDto;
import com.example.service.CarService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/cars")
public @Controller class CarController {

    private static final Logger log = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> findAll() {

        List<CarDto> carList = this.carService.findAll().join();

        log.info("Car list: " + carList);

        return ResponseEntity.ok(carList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findById(@PathVariable(name = "id") Long id) {

        CarDto carDto = this.carService.findById(id).join();

        if (carDto == null) return ResponseEntity.notFound().build();

        log.info("Car: " + carDto);

        return ResponseEntity.ok(carDto);
    }

    @PostMapping
    public ResponseEntity<CarDto> save(@RequestBody CarDto carDto) {

        CarDto savedCarDto = this.carService.save(carDto).join();

        log.info("Saved car: " + savedCarDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {

        this.carService.delete(id).join();

        log.info("Deleted car with id: " + id);

        return ResponseEntity.noContent().build();
    }
}
