package com.example.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Lokasi;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {

    @Query(value =
    "select "+ 
        "distinct L.id_lokasi, "+
        "L.nama_lokasi, "+
        "CONCAT('<', nama_lokasi, '-', LPAD(id_lokasi :: varchar, 4, '0'), '>') "+
    "from "+ 
        "checkpoint_pengiriman as CP "+
        "inner join Lokasi as L on CP.checkpoint_pengiriman_id_lokasi = L.id_lokasi "+ 
    "order by "+ 
        "L.id_lokasi ASC ", nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public List<Object> getCheckpointLokasi();

}
