package com.RentACar.RentACar.repository;

import com.RentACar.RentACar.common.repository.BaseRepository;
import com.RentACar.RentACar.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID> {

}
