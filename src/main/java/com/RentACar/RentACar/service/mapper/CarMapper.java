package com.RentACar.RentACar.service.mapper;

import com.RentACar.RentACar.common.mapper.BaseMapper;
import com.RentACar.RentACar.dto.CarDto;
import com.RentACar.RentACar.entity.Car;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder)
public interface CarMapper extends BaseMapper<CarDto, Car> {

    Car toEntity(UUID id);

    @AfterMapping
    default void setParentToChild(@MappingTarget Car car) {

        if (!CollectionUtils.isEmpty(car.getComments())) {
            car.getComments().forEach(comment -> {
                comment.setCar(car);
            });
        }
    }
}
