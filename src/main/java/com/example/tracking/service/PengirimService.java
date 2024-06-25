package com.example.tracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tracking.exception.PenerimaException;
import com.example.tracking.exception.PengirimException;
import com.example.tracking.model.Pengirim;
import com.example.tracking.repository.PengirimRepository;

import jakarta.transaction.Transactional;

@Service
public class PengirimService {
    
    @Autowired
    PengirimRepository pengirimRepository;

    public List<Pengirim> AllPengirim() throws PengirimException {
        if(pengirimRepository.findAll().isEmpty() || pengirimRepository.findAll() == null){
            throw new PengirimException("Pengirim tidak ditemukan");
        }
        return pengirimRepository.findAll();
    }

    @Transactional
    public Pengirim postPengirim(Pengirim pengirim) throws PenerimaException {
        if(pengirim.getNamaPengirim() == null || pengirim.getNamaPengirim().isEmpty()){
            throw new PenerimaException("Nama pengirim harus diisi");
        }else if(pengirim.getNoTelp() == null || pengirim.getNoTelp().isEmpty()){
            throw new PenerimaException("No telp pengirim harus diisi");
        }
        return pengirimRepository.save(pengirim);
    }
}
