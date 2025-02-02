package com.example.crmSystem.repository;

import com.example.crmSystem.entity.ApplicationRequest;

import java.util.List;

public interface ApplicationRequestRepository {

    ApplicationRequest save(ApplicationRequest request);

    List<ApplicationRequest> findAll();

    ApplicationRequest findById(Long id);

    void update(ApplicationRequest request);

    void deleteById(Long id);

}
