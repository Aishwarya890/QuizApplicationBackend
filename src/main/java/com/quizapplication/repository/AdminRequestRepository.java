package com.quizapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapplication.entity.AdminRequest;
import com.quizapplication.entity.RequestStatus;
import com.quizapplication.entity.User;

public interface AdminRequestRepository extends JpaRepository<AdminRequest, Long> {
    List<AdminRequest> findByStatus(RequestStatus status);

	List<AdminRequest> findByUserAndStatus(User user, RequestStatus pending);
}

