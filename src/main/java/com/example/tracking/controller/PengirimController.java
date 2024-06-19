package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Pengirim;
import com.example.tracking.service.PengirimService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@Slf4j
@RequestMapping("/api/v1/pengirim")
public class PengirimController {

    @Autowired
    PengirimService pengirimService;

    @GetMapping
    public ResponseEntity<List<Pengirim>> AllPengirim() {
        try {
            log.info("Menampilkan semua pengirim");
            return ResponseEntity.ok(pengirimService.AllPengirim());
        } 
        catch (Exception e) {
            e.printStackTrace();
            log.info("Gagal menampilkan semua pengirim " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> postPengirim(@RequestBody Pengirim pengirim) {
        return pengirimService.postPengirim(pengirim);
    }


}
