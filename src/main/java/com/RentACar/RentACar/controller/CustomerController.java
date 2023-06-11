package com.RentACar.RentACar.controller;

import com.RentACar.RentACar.dto.CommentDto;
import com.RentACar.RentACar.dto.UserDto;
import com.RentACar.RentACar.service.CommentService;
import com.RentACar.RentACar.service.CarService;
import com.RentACar.RentACar.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
//@RequestMapping("/customer")
public class CustomerController {
    private final UserService userService;
    private final CarService carService;
    private final CommentService commentService;

    public CustomerController(UserService userService,
                              CarService carService,
                              CommentService commentService) {
        this.userService = userService;
        this.carService = carService;
        this.commentService = commentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.GET, RequestMethod.POST})
    public String showProfilePage(Principal principal, Model model) {

        UserDto user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        return "customer/Profile";
    }

    @RequestMapping(value = "/profile-modify", method = {RequestMethod.GET, RequestMethod.POST})
    public String showProfileModifyPage(Principal principal, Model model) {

        UserDto user = userService.findByUsername(principal.getName());
        user.setPassword("");
        model.addAttribute("user", user);

        return "customer/ProfileModify";
    }

    @RequestMapping("/profile-modify-action")
    public String profileModifyAction(@Valid @ModelAttribute("user") UserDto updatedUser,
                                      BindingResult bindingResult,
                                      Principal principal) {

        if (bindingResult.hasFieldErrors("email") || bindingResult.hasFieldErrors("password")
                || bindingResult.hasFieldErrors("phoneNumber")) {
            return "redirect:/profile-modify?error";
        }

        UserDto userDto = userService.findByUsername(principal.getName());
        userDto.setEmail(updatedUser.getEmail());
        userDto.setPassword(updatedUser.getPassword());
        userDto.setPhoneNumber(updatedUser.getPhoneNumber());
        userService.saveUser(userDto);

        return "redirect:/profile?updated";
    }

    @RequestMapping(value = "/payment", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPaymentPage(@RequestParam("id") UUID id,
                                  Model model,
                                  HttpSession session) {

        model.addAttribute("car", carService.findById(id));
        session.setAttribute("price", carService.findById(id).getPrice());

        return "customer/Payment";
    }

    @GetMapping("/getUserById/{id}")
    UserDto getUserById(@PathVariable UUID id) {

        return userService.findById(id);
    }


    @RequestMapping(value = "/comments/{carId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String selectCarId(@PathVariable("carId") UUID carId,
                              HttpSession session) {

        session.setAttribute("carId", carId);
        return "redirect:/comments";
    }

    @RequestMapping(value = "/comments", method = {RequestMethod.GET, RequestMethod.POST})
    public String showCommentPage(Model model, HttpSession session) {

        UUID carId = session.getAttribute("carId") == null ? UUID.fromString("") : (UUID) session.getAttribute("carId");
        model.addAttribute("car", carService.findById(carId));

        return "customer/Comments";
    }

    @RequestMapping(value = "/post-comments")
    public String postComments(@RequestParam("commentId") int commentId,
                               @RequestParam("comment") String postedComment,
                               Principal principal,
                               HttpSession session) {

        UUID carId = session.getAttribute("carId") == null ? UUID.randomUUID() : (UUID) session.getAttribute("carId");
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(postedComment);
        commentDto.setPostedBy(principal.getName());
        commentDto.setCarId(carId);

        commentService.saveComment(commentDto);

        return "redirect:/comments?posted";
    }
}