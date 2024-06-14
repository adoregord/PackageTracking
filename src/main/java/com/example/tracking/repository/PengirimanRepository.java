package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Pengiriman;
import java.util.List;

@Repository
public interface PengirimanRepository extends JpaRepository<Pengiriman, Long> {
    
    @Query("SELECT p FROM Pengiriman p WHERE p.isReceived = true")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public List<Pengiriman> findByStatusPengiriman();
}
