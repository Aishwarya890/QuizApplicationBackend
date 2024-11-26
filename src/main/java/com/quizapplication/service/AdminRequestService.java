package com.quizapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapplication.entity.AdminRequest;
import com.quizapplication.entity.RequestStatus;
import com.quizapplication.entity.User;
import com.quizapplication.repository.AdminRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminRequestService {

    @Autowired
    private AdminRequestRepository adminRequestRepository;

    public AdminRequest save(AdminRequest adminRequest) {
        return adminRequestRepository.save(adminRequest); // Save to the database
    }
    
    public List<AdminRequest> getAllPendingRequests() {
        return adminRequestRepository.findByStatus(RequestStatus.PENDING);
    }

    public void approveRequest(Long requestId) {
        AdminRequest request = adminRequestRepository.findById(requestId).orElseThrow();
        request.setStatus(RequestStatus.APPROVED);
        adminRequestRepository.save(request);
    }

    public void rejectRequest(Long requestId) {
        AdminRequest request = adminRequestRepository.findById(requestId).orElseThrow();
        request.setStatus(RequestStatus.REJECTED);
        adminRequestRepository.save(request);
    }

    public AdminRequest findById(Long id) {
        // Find the admin request by ID, and return it if present
        Optional<AdminRequest> adminRequest = adminRequestRepository.findById(id);
        return adminRequest.orElse(null); // Return null if not found
    }
    
    public List<AdminRequest> getPendingRequestsForUser(User user) {
        return adminRequestRepository.findByUserAndStatus(user, RequestStatus.PENDING);
    }
    
 // New method to get all request history (approved and rejected)
    public List<AdminRequest> getAllRequestHistory() {
        return adminRequestRepository.findAll(); // Fetch all requests from the database
    }

	
}
