package com.example.crmSystem.repository;

import com.example.crmSystem.entity.ApplicationRequest;

import java.util.List;
import java.util.Optional;

public interface ApplicationRequestRepository {

    ApplicationRequest save(ApplicationRequest request);

    List<ApplicationRequest> findAll();

    Optional<ApplicationRequest> findById(Long id);

    void update(ApplicationRequest request);
    void markAsHandled(Long id);

    void deleteById(Long id);

}
