package com.example.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Penerima;
import com.example.tracking.repository.PenerimaRepository;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/penerima")
public class PenerimaController {
    @Autowired
    PenerimaRepository penerimaRepository;

    @GetMapping
    public List<Penerima> getAllPenerima() {
        return penerimaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addPenerima(@RequestBody Penerima penerima) {
        penerimaRepository.save(penerima);
        return ResponseEntity.ok("Success adding\n" + penerima);
    }
}
