package com.quizapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.quizapplication.entity.AdminRequest;
import com.quizapplication.entity.User;
import com.quizapplication.service.AdminRequestService;
import com.quizapplication.service.UserService;

import java.util.List;

@RestController

@CrossOrigin(origins = {"http://localhost:3000","https://quizapplicationbackend-production.up.railway.app"})
@RequestMapping("/admin/requests")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminApprovalController {

    @Autowired
    private AdminRequestService adminRequestService;

    @Autowired
    private UserService userService;
    @GetMapping("/pending")
    public List<AdminRequest> getPendingRequests() {
        return adminRequestService.getAllPendingRequests();
    }

//    @PostMapping("/approve/{id}")
//    public ResponseEntity<String> approveRequest(@PathVariable Long id) {
//        adminRequestService.approveRequest(id);
//        return ResponseEntity.ok("Admin request approved");
//    }
    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable Long id) {
        AdminRequest adminRequest = adminRequestService.findById(id);
        if (adminRequest == null) {
            return ResponseEntity.badRequest().body("Admin request not found.");
        }

        try {
            // Approve the admin request
            adminRequestService.approveRequest(id);

            // Update the corresponding user's isAdmin status
            User user = adminRequest.getUser();
            if (user == null) {
                return ResponseEntity.badRequest().body("User associated with this request not found.");
            }

            user.setIsAdmin(true); // Set the user as an admin
            userService.updateUser(user); // Ensure you have this method in the UserService
            
            return ResponseEntity.ok("Admin request approved, and user promoted to admin.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        }
    }
    
    @GetMapping("/history")
    public List<AdminRequest> getAllRequestHistory() {
        return adminRequestService.getAllRequestHistory();
    }

    
    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long id) throws Exception {
        try {
            adminRequestService.rejectRequest(id);
            return ResponseEntity.ok("Request rejected successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found.");
        }
    }
}

