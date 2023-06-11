package com.RentACar.RentACar.controller;

import com.RentACar.RentACar.dto.CarDto;
import com.RentACar.RentACar.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String showDashboardPage() {

        return "admin/Dashboard";
    }

    @RequestMapping(value = "/manage-roles", method = {RequestMethod.GET, RequestMethod.POST})
    public String showManageRolesPage() {

        return "admin/ManageRoles";
    }

    @RequestMapping(value = "/manage-user", method = {RequestMethod.GET, RequestMethod.POST})
    public String showManageUserPage() {

        return "admin/ManageUser";
    }

    @RequestMapping(value = "/add-user", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAddUserPage(@ModelAttribute(value = "user") UserDto userDto) {

        return "admin/AddUser";
    }

    @RequestMapping(value = "/manage-car", method = {RequestMethod.GET, RequestMethod.POST})
    public String showManageCarPage() {

        return "admin/ManageCar";
    }

    @RequestMapping(value = "/add-car", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAddCarPage(@ModelAttribute("car") CarDto carDto) {

        return "admin/AddCar";
    }
}
