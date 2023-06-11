package com.RentACar.RentACar.repository;

import com.RentACar.RentACar.common.repository.BaseRepository;
import com.RentACar.RentACar.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends BaseRepository<Car, UUID> {

    /**
     * Example of derived query in spring data jpa
     *
     * @param title of car which has comments
     * @return Car object related with selected title
     */
    List<Car> findByTitleContainingIgnoreCase(String title);

    /**
     * Example of derived query in spring data jpa
     *
     * @param category of car which has category
     * @return list of Car objects related with selected category
     */
    List<Car> findByCategory(String category);

    /**
     * Example of derived query in spring data jpa
     *
     * @param price of car which has less than price
     * @return list of Car objects related with selected price
     */
    List<Car> findByPriceLessThan(int price);

    /**
     * Example of derived query in spring data jpa
     *
     * @param price of car which has greater than price
     * @return list of Car objects related with selected price
     */
    List<Car> findByPriceGreaterThan(int price);
}
