package com.RentACar.RentACar.common.mapper;

import com.RentACar.RentACar.common.dto.BaseDto;
import com.RentACar.RentACar.common.entity.BaseEntity;

import java.util.List;
import java.util.Set;

public interface BaseMapper<T1 extends BaseDto, T2 extends BaseEntity> {

    /**
     * BaseEntity extend alan entity parametre alır.BaseDto extend alan dto döner.
     *
     * @param entity BaseEntity extend alan entity parametre alır.
     * @return dto
     */
    T1 entityToDto(T2 entity);

    /**
     * BaseDto yu extends alan Dto nesnesi alıp entity döner
     *
     * @param dto BaseDto extend  alan Dto nesnesi
     * @return BaseEntity extend alan entity döner.
     */
    T2 dtoToEntity(T1 dto);

    /**
     * @param entitySet
     * @return
     */
    Set<T1> dtoSetToEntitySet(Set<T2> entitySet);

    /**
     * @param entitySet
     * @return
     */
    Set<T1> entitySetToDtoSet(Set<T2> entitySet);

    /**
     * @param entitySet
     * @return
     */
    List<T2> dtoListToEntityList(List<T1> entitySet);

    /**
     * @param entityList
     * @return
     */
    List<T1> entityListToDtoList(List<T2> entityList);

}
