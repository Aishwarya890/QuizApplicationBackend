//package com.quizapplication.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.quizapplication.DTO.UserHistoryDto;
//import com.quizapplication.service.UserHistoryService;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/user-history")
//public class UserHistoryController {
//    private final UserHistoryService userHistoryService;
//
//    @Autowired
//    public UserHistoryController(UserHistoryService userHistoryService) {
//        this.userHistoryService = userHistoryService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserHistoryDto>> getUserHistory(@RequestParam Long userId) {
//        List<UserHistoryDto> history = userHistoryService.getUserHistory(userId);
//        return ResponseEntity.ok(history);
//    }
//}
//
