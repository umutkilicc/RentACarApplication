package com.RentACar.RentACar.service;

import com.RentACar.RentACar.dto.CarDto;

import java.util.List;
import java.util.UUID;

public interface CarService {

    CarDto saveCar(CarDto carDto);

    CarDto updateCar(CarDto carDto);

    List<CarDto> saveAllCars(List<CarDto> carDtoList);

    List<CarDto> findAll();

    List<CarDto> findByCategory(String categoryName);

    List<CarDto> findByName(String name);

    CarDto findById(UUID uuid);

    void delete(CarDto carDto);

    void deleteById(UUID uuid);

    void deleteAll(List<CarDto> carDtos);
}
