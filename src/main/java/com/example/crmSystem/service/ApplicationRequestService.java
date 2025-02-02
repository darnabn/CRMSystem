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
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Заявка с ID " + id + " не найдена")
        );
    }


    public ApplicationRequest createRequest(ApplicationRequest request) {
        return repository.save(request);
    }

    public void markAsProcessed(Long id) {
        ApplicationRequest request = getRequestById(id); // уже проверяет наличие
        request.setHandled(true);
        repository.update(request);
    }



    public void updateRequest(ApplicationRequest request) {
        if (repository.findById(request.getId()) == null) {
            throw new IllegalArgumentException("Request with ID " + request.getId() + " not found");
        }
        repository.update(request);
    }


    public void deleteRequest(Long id) {
        if (repository.findById(id) == null) {
            throw new IllegalArgumentException("Request with ID " + id + " not found");
        }
        repository.deleteById(id);
    }
}
