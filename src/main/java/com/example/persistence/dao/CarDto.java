package com.example.persistence.dao;

import java.io.Serializable;

/**
 * DTO for {@link com.example.persistence.entity.Car}
 */
public record CarDto(
        Long id,
        String make,
        String model,
        String color) implements Serializable {
}