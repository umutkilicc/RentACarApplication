package com.RentACar.RentACar.validation;

import com.RentACar.RentACar.dto.CarDto;
import com.RentACar.RentACar.entity.Car;
import com.RentACar.RentACar.repository.CarRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Component
@Validated
public class CarValidator {

    private final CarRepository carRepository;

    public CarValidator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void carSaveValidator(@Valid CarDto carDto) {

        List<Car> car = carRepository.findByTitleContainingIgnoreCase(carDto.getTitle());
        if (!CollectionUtils.isEmpty(car)) {
            throw new RuntimeException("This Car has been added before");
        }
    }


}
