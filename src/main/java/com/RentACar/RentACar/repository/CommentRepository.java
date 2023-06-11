package com.RentACar.RentACar.repository;

import com.RentACar.RentACar.common.repository.BaseRepository;
import com.RentACar.RentACar.entity.Comment;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends BaseRepository<Comment, UUID> {

    /**
     * Example of derived query in spring data jpa
     *
     * @param carId id of car which has comments
     * @return list of comments related with selected car
     */
    List<Comment> findByCarIdOrderByCreateDateDesc(UUID carId);

//    /**
//     * Example of jpql in spring data jpa
//     *
//     * @param carId
//     * @return
//     */
//    @Query("SELECT c from Comment c where c.car =:carId")
//    List<String> findByCar(@Param("carId") UUID carId);
//    @Query("SELECT c from Comment c where c.car = :carId")
//    List<String> findByCar(@RequestParam(name = "carId") UUID carId);


}
