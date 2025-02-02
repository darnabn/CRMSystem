package com.example.crmSystem.service;

import com.example.crmSystem.entity.ApplicationRequest;
import com.example.crmSystem.repository.ApplicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService {

    private final ApplicationRequestRepository repository;

    public List<ApplicationRequest> getAllRequests() {
        return repository.findAll();
    }

    public ApplicationRequest getRequestById(Long id) {
        return repository.findById(id);
    }

    public ApplicationRequest createRequest(ApplicationRequest request) {
        return repository.save(request);
    }

    public void updateRequest(ApplicationRequest request) {
        repository.update(request);
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
