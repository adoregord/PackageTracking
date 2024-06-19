package com.example.tracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tracking.exception.PenerimaException;
import com.example.tracking.model.Penerima;
import com.example.tracking.repository.PenerimaRepository;

import jakarta.transaction.Transactional;

@Service
public class PenerimaService {

    @Autowired
    PenerimaRepository penerimaRepository;

    public List<Penerima> getAllPenerima() throws PenerimaException {
        if(penerimaRepository.findAll() == null || penerimaRepository.findAll().isEmpty()){
            throw new PenerimaException("Penerima tidak ditemukan");
        }
        return penerimaRepository.findAll();
    }

    @Transactional
    public Penerima addPenerima(Penerima penerima) throws PenerimaException {
        if(penerima.getNamaPenerima() == null || penerima.getNamaPenerima().isEmpty()){
            throw new PenerimaException("Nama penerima harus diisi");
        }else if(penerima.getNoTelp() == null || penerima.getNoTelp().isEmpty()){
            throw new PenerimaException("No telp penerima harus diisi");
        }
        return penerimaRepository.save(penerima);
    }
}
