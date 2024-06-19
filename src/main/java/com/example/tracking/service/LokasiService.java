package com.example.tracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tracking.exception.LocationIsEmpty;
import com.example.tracking.model.Lokasi;
import com.example.tracking.repository.LokasiRepository;

import jakarta.transaction.Transactional;

@Service
public class LokasiService {

    @Autowired 
    private LokasiRepository lokasiRepository;

    public List<Lokasi> getAllLoc() throws LocationIsEmpty{
        if(lokasiRepository.findAll().isEmpty() || lokasiRepository.findAll() == null){
            throw new LocationIsEmpty("Lokasi tidak ditemukan");
        }
        return lokasiRepository.findAll();
    }

    @Transactional
    public Lokasi addLoc(Lokasi lokasi) throws LocationIsEmpty {
        if(lokasi.getNamaLokasi() == null || lokasi.getNamaLokasi().isBlank()){
            throw new LocationIsEmpty("Nama lokasi harus diisi");
        }
        return lokasiRepository.save(lokasi);
    }
    
    public List<Object> getCheckpointLokasi() throws LocationIsEmpty{
        if(lokasiRepository.findAll().isEmpty() || lokasiRepository.findAll() == null){
            throw new LocationIsEmpty("Lokasi tidak ditemukan");
        }
        return lokasiRepository.getCheckpointLokasi();
    }
}
