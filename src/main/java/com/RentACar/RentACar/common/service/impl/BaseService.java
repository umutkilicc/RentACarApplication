package com.RentACar.RentACar.common.service.impl;

import com.RentACar.RentACar.common.dto.BaseDto;
import com.RentACar.RentACar.common.entity.BaseEntity;
import com.RentACar.RentACar.common.mapper.BaseMapper;
import com.RentACar.RentACar.common.repository.BaseRepository;
import com.RentACar.RentACar.common.service.BaseServiceInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseService<
        RepositoryType extends BaseRepository<EntityType, UUID>,
        MapperType extends BaseMapper<DtoType, EntityType>,
        EntityType extends BaseEntity,
        DtoType extends BaseDto> implements BaseServiceInterface<DtoType> {

    protected final MapperType mapper;

    protected final RepositoryType repository;

    public DtoType save(DtoType dto) {

        return mapper.entityToDto(repository.save(mapper.dtoToEntity(dto)));
    }

    public List<DtoType> saveAll(List<DtoType> dtoList) {

        return mapper.entityListToDtoList(repository.saveAll(mapper.dtoListToEntityList(dtoList)));
    }

    public List<DtoType> findAll() {

        return mapper.entityListToDtoList(repository.findAll());
    }

    public DtoType findById(UUID uuid) {

        return mapper.entityToDto(repository.findById(uuid).orElseThrow(() -> new RuntimeException("Kayıt Bulunamadı")));
    }

    public void delete(DtoType dto) {

        repository.delete(mapper.dtoToEntity(dto));
    }

    public void deleteById(UUID uuid) {

        repository.deleteById(uuid);
    }

    public void deleteAll(List<DtoType> dtoTypeList) {

        repository.deleteAll(mapper.dtoListToEntityList(dtoTypeList));
    }

}
