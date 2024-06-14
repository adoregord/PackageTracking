package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Service;
import com.example.tracking.repository.ServiceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {
    
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping
    public List<Service> getAllService() {
        return serviceRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody Service service) {
        serviceRepository.save(service);
        return ResponseEntity.ok("Success adding\n" + service);
    }
    
    
}
