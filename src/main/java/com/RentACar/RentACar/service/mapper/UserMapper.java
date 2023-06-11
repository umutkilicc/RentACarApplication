package com.RentACar.RentACar.service.mapper;

import com.RentACar.RentACar.common.mapper.BaseMapper;
import com.RentACar.RentACar.dto.UserDto;
import com.RentACar.RentACar.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder)
public interface UserMapper extends BaseMapper<UserDto, User>{

}
