package com.example.tracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tracking.exception.PaketException;
import com.example.tracking.model.Paket;
import com.example.tracking.repository.PaketRepository;

import jakarta.transaction.Transactional;

@Service
public class PaketService {

    @Autowired
    private PaketRepository paketRepository;
    
    public List<Paket> getAllPaket() throws PaketException {
        if(paketRepository.findAll() == null || paketRepository.findAll().isEmpty()){
            throw new PaketException("Paket tidak ditemukan");
        }
        return paketRepository.findAll();

    }
    
    @Transactional
    public Paket addPaket(@RequestBody Paket paket) throws PaketException{
        if(paket.getPengirim() == null){
            throw new PaketException("Pengirim harus diisi");
        }else if(paket.getPenerima() == null){
            throw new PaketException("Penerima harus diisi");
        }else if(paket.getBeratPaket() == null){
            throw new PaketException("Berat paket harus diisi");
        }else if(paket.getTujuanPengiriman() == null){
            throw new PaketException("Tujuan pengiriman harus diisi");
        }
        return paketRepository.save(paket);
    }

    public List<Object> getCheckpointPaket(String checkpoint) throws PaketException{
        if(checkpoint == null){
            throw new PaketException("Checkpoint harus diisi");
        }else if(paketRepository.getCheckpointPaket(checkpoint).isEmpty()){
            throw new PaketException("Paket tidak ditemukan");
        }
        return paketRepository.getCheckpointPaket(checkpoint);
    }
}
