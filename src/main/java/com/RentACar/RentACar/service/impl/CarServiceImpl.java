package com.RentACar.RentACar.service.impl;

import com.RentACar.RentACar.common.service.impl.BaseService;
import com.RentACar.RentACar.dto.CarDto;
import com.RentACar.RentACar.entity.Car;
import com.RentACar.RentACar.repository.CarRepository;
import com.RentACar.RentACar.service.CarService;
import com.RentACar.RentACar.service.mapper.CarMapper;
import com.RentACar.RentACar.validation.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl extends BaseService<CarRepository, CarMapper, Car, CarDto> implements CarService {

    private final CarValidator carValidator;
    private final CarRepository carRepository;

    @Autowired
    protected CarServiceImpl(CarMapper mapper, CarRepository repository, CarValidator carValidator, CarRepository carRepository) {
        super(mapper, repository);
        this.carValidator = carValidator;
        this.carRepository = carRepository;
    }

    /**
     * @param carDto
     * @return
     */
    public CarDto saveCar(CarDto carDto) {

        carValidator.carSaveValidator(carDto);
        return super.save(carDto);
    }

    /**
     * @param carDto
     * @return
     */
    public CarDto updateCar(CarDto carDto) {

        return super.save(carDto);
    }

    public List<CarDto> saveAllCars(List<CarDto> carDtoList) {

        return super.saveAll(carDtoList);
    }

    /**
     * @return
     */
    @Override
    public List<CarDto> findAll() {

        return super.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<CarDto> findByCategory(String categoryName) {

        return mapper.entityListToDtoList(carRepository.findByCategory(categoryName));
    }

    /**
     * @return
     */
    @Override
    public List<CarDto> findByName(String name) {

        return mapper.entityListToDtoList(carRepository.findByTitleContainingIgnoreCase(name));
    }

    /**
     * @param uuid
     * @return
     */
    @Override
    public CarDto findById(UUID uuid) {

        return super.findById(uuid);
    }

    /**
     * @param carDto
     */
    @Override
    public void delete(CarDto carDto) {

        super.delete(carDto);
    }

    /**
     * @param uuid
     */
    @Override
    public void deleteById(UUID uuid) {

        super.deleteById(uuid);
    }

    /**
     * @param carDtos
     */
    @Override
    public void deleteAll(List<CarDto> carDtos) {

        super.deleteAll(carDtos);
    }
}
