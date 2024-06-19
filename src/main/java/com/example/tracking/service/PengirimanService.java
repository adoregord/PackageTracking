package com.example.tracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tracking.DTO.CheckpointDTO;
import com.example.tracking.exception.PengirimanException;
import com.example.tracking.model.Paket;
import com.example.tracking.model.Pengirim;
import com.example.tracking.model.Pengiriman;
import com.example.tracking.model.Servis;
import com.example.tracking.repository.PaketRepository;
import com.example.tracking.repository.PengirimanRepository;
import com.example.tracking.repository.ServisRepository;



@Service
public class PengirimanService {

    @Autowired
    private PengirimanRepository pengirimanRepository;

    @Autowired
    private ServisRepository servisRepository;

    @Autowired
    private PaketRepository paketRepository;

    public List<Pengiriman> getAllPengiriman() throws PengirimanException {
        if(pengirimanRepository.findAll().isEmpty() || pengirimanRepository.findAll() == null){
            throw new PengirimanException("Data pengiriman paket tidak ditemukan");
        }
        return pengirimanRepository.findAll();
    }
    
    public List<Pengiriman> addPengiriman(Pengiriman pengiriman) throws PengirimanException{

        if(pengiriman.getPaket() == null){
            throw new PengirimanException("Paket tidak boleh kosong");
        }else if(pengiriman.getCheckpointPengiriman() == null){
            throw new PengirimanException("Checkpoint tidak boleh kosong");
        }else if(pengiriman.getHargaPengiriman() < 0 ){
            throw new PengirimanException("Harga tidak boleh kurang dari 0");
        }else if(pengiriman.getPaket() == null){
            throw new PengirimanException("Paket ID tidak boleh kosong");
        }else if(pengiriman.getServis() == null){
            throw new PengirimanException("Servis ID tidak boleh kosong");
        }
            pengirimanRepository.save(pengiriman);
            // log.info("Pengiriman ID: " + pengiriman.getId_pengiriman() + pengiriman.getIsReceived());

            //update isReceived mengecek dan mengubah nilai isReceived sesuai dengan tujuan pengiriman paket
            pengirimanRepository.updateIsReceived(pengiriman.getId_pengiriman()); 
            return pengirimanRepository.printByIdPengiriman(pengiriman.getId_pengiriman());
        }

    public List<Pengiriman> pengirimanSampai() throws PengirimanException {
        if(pengirimanRepository.findAll() == null || pengirimanRepository.findAll().isEmpty()){
            throw new PengirimanException("Data pengiriman paket tidak ditemukan");
        }
        return pengirimanRepository.findByStatusPengiriman();
    }

    public List<Pengiriman> addCheckpoint(CheckpointDTO checkpointDTO) throws PengirimanException {
        if(checkpointDTO.getLokasi_id() == null){
            throw new PengirimanException("Lokasi ID tidak boleh kosong");
        }else if(checkpointDTO.getPengiriman_id() == null){
            throw new PengirimanException("Pengiriman ID tidak boleh kosong");
        }
        pengirimanRepository.insertCheckpoint(checkpointDTO.getPengiriman_id(), checkpointDTO.getLokasi_id());
        pengirimanRepository.updateIsReceived(checkpointDTO.getPengiriman_id());
        return pengirimanRepository.findAll();
    }

}
