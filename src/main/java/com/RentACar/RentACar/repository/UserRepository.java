package com.RentACar.RentACar.repository;

import com.RentACar.RentACar.common.repository.BaseRepository;
import com.RentACar.RentACar.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {
    //Like la arama
    List<User> findByUsernameContainingIgnoreCase(String username);

    //    @Query("SELECT c FROM User c JOIN c.roles r WHERE c.username =:username")
    Optional<User> findByUsernameIgnoreCase(String username);

    //int deger varsa user icinde between ile arama
    List<User> findByAgeBetween(int ageStart, int ageEnd);

    //email parametreli distinc ile arama


    //icinde or & and in oldugu bi query

    //JPQL ile Driven query yaz
//    //Role tablosunda name i olan ve userleri getiren, role ile user arasindaki iliskiyi getiren JPQL
//
//    @Query("SELECT c FROM Role  c INNER JOIN c.userEntity WHERE c.name =?1")
//    List<Role> findByRoleName(String name);


}
