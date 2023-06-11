package com.RentACar.RentACar.controller;

import com.RentACar.RentACar.dto.CarDto;
import com.RentACar.RentACar.service.CarService;
import com.RentACar.RentACar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CarController {

    private final CarService carService;
    private final UserService userService;

    public CarController(CarService carService, UserService userService) {

        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/get-all-cars")
    ResponseEntity<List<CarDto>> allCars() {

        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @GetMapping("get-cars-by-category/{category}")
    ResponseEntity<List<CarDto>> getByCategory(@PathVariable String category) {

        return new ResponseEntity<>(carService.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/search-cars-by-name")
    ResponseEntity<List<CarDto>> getByName(@RequestParam("name") String name) {

        return new ResponseEntity<>(carService.findByName(name), HttpStatus.OK);
//        return ResponseEntity.ok(carService.findByName(name));
    }

    @PostMapping("/add-car")
    ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {

        return new ResponseEntity<>(carService.saveCar(carDto), HttpStatus.CREATED);
    }

    @GetMapping("/payment-action")
    void paymentAction(HttpSession session) {

        int spent = Integer.parseInt(session.getAttribute("price").toString());
        userService.makePayment(spent);
        session.removeAttribute("price");
    }
}
