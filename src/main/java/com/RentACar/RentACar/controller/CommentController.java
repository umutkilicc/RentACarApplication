package com.RentACar.RentACar.controller;

import com.RentACar.RentACar.dto.CommentDto;
import com.RentACar.RentACar.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    @GetMapping("/get-all-comments")
    public ResponseEntity<List<CommentDto>> getAllComments() {

        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-car")
    public ResponseEntity<List<CommentDto>> getByCar(UUID carId) {

        return new ResponseEntity<>(commentService.findByCarId(carId), HttpStatus.OK);
    }

    @GetMapping("/get-comments")
    String getCommentsHTML(HttpSession session) {
        UUID carId = session.getAttribute("carId") == null ? UUID.fromString("") : (UUID) session.getAttribute("carId");
        return commentService.loadComments(carId);
    }
}