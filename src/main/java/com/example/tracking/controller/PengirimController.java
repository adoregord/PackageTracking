package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Pengirim;
import com.example.tracking.repository.PengirimRepository;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/pengirim")
public class PengirimController {

    @Autowired
    PengirimRepository pengirimRepository;

    @GetMapping
    public List<Pengirim> AllPengirim() {
        return pengirimRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> postPengirim(@RequestBody Pengirim pengirim) {
        pengirimRepository.save(pengirim);
        return ResponseEntity.ok("Success adding\n" + pengirim);
    }


}
