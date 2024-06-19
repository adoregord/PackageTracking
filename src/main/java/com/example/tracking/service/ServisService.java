package com.example.tracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tracking.exception.ServisException;
import com.example.tracking.model.Servis;
import com.example.tracking.repository.ServisRepository;

import jakarta.transaction.Transactional;

@Service
public class ServisService {
    
    @Autowired
    ServisRepository servisRepository;

    @GetMapping
    public List<Servis> getAllService() throws ServisException {
        if(servisRepository.findAll() == null || servisRepository.findAll().isEmpty() ){
            throw new ServisException("Data Service tidak ditemukan");
        }
        return servisRepository.findAll();
    }

    @Transactional
    @PostMapping
    public Servis addServis(@RequestBody Servis servis) throws ServisException{

        if(servis.getNamaService() == null || servis.getNamaService().isBlank() ){
            throw new ServisException("Nama servis tidak boleh kosong");
        }
        else if(servis.getHargaPerKg() == null || servis.getHargaPerKg().isNaN() ){
            throw new ServisException("Harga service tidak boleh kosong dan harus angka");
        }
        return servisRepository.save(servis);
    }
}
