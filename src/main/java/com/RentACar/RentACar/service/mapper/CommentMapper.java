package com.RentACar.RentACar.service.mapper;

import com.RentACar.RentACar.common.mapper.BaseMapper;
import com.RentACar.RentACar.dto.CommentDto;
import com.RentACar.RentACar.entity.Comment;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {CarMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder)
public interface CommentMapper extends BaseMapper<CommentDto, Comment> {

    @Mapping(target ="car" ,source = "carId")
    Comment dtoToEntity(CommentDto commentDto);

}
