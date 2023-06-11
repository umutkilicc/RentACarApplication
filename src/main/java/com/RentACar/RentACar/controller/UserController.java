package com.RentACar.RentACar.controller;

import com.RentACar.RentACar.dto.CarDto;
import com.RentACar.RentACar.dto.UserDto;
import com.RentACar.RentACar.service.CarService;
import com.RentACar.RentACar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;
    private final CarService carService;

    public UserController(UserService userService, CarService carService) {

        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping("/get-all-users")
    ResponseEntity<List<UserDto>> getAllUsers() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    ResponseEntity<UserDto> getById(@PathVariable UUID id) {

        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/get-by-username")
    ResponseEntity<UserDto> getByUsername(@RequestParam(value = "username") String username) {

        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/delete-user-by-id/{id}")
    void deleteUserById(@PathVariable UUID id) {

        userService.deleteById(id);
    }

    @GetMapping("/disable-user/{id}")
    void disableUser(@PathVariable UUID id) {

        userService.disableUser(id);
    }

    @GetMapping("/enable-user/{id}")
    void enableUser(@PathVariable UUID id) {

        userService.enableUser(id);
    }

    @GetMapping("/make-admin/{id}")
    void makeAdmin(@PathVariable UUID id) {

        userService.makeAdmin(id);
    }

    @GetMapping("/remove-admin/{id}")
    void removeAdmin(@PathVariable UUID id) {

        userService.removeAdmin(id);
    }

    @GetMapping("/delete-car/{id}")
    void deleteCars(@PathVariable UUID id) {

        carService.deleteById(id);
    }

    @PostMapping("/add-user-action")
    String saveUser(@Valid @ModelAttribute(value = "user") UserDto user,
                    BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "error";

        if (userService.findByUsername(user.getUsername()) != null)
            return "duplicate";

        userService.registerUser(user);

        return "added";
    }

    @PostMapping("/add-car-action")
    public String register(@Valid @ModelAttribute(value = "car") CarDto car,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "error";

        carService.saveCar(car);
        return "added";
    }
}