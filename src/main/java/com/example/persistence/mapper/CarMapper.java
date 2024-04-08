package com.example.persistence.mapper;

import com.example.persistence.dao.CarDto;
import com.example.persistence.entity.Car;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "make", source = "make"),
            @Mapping(target = "model", source = "model"),
            @Mapping(target = "color", source = "color")
    })
    CarDto toDto(Car car);


    @InheritInverseConfiguration
    Car toEntity(CarDto carDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarDto carDto, @MappingTarget Car car);
}
