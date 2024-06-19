package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Pengiriman;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface PengirimanRepository extends JpaRepository<Pengiriman, Long> {
    
    @Query("SELECT p FROM Pengiriman p WHERE p.isReceived = true")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public List<Pengiriman> findByStatusPengiriman();

    @Modifying
    @Transactional
    @Query(value="update " + //
                "pengiriman as p " + //
                "set  " + //
                "is_received = " + //
                "case when exists " + //
                "(select * from paket as a " + //
                "inner join pengiriman as b on b.paket_id=a.id_paket " + //
                "inner join checkpoint_pengiriman as c on c.pengiriman_id=b.id_pengiriman " + //
                "inner join lokasi as d on d.id_lokasi=c.checkpoint_pengiriman_id_lokasi " + //
                "where a.id_tujuan_pengiriman = c.checkpoint_pengiriman_id_lokasi) then true " + //
                "else false " + //
                "end " + //
                "where p.id_pengiriman = ?1 ", nativeQuery = true)
    public void updateIsReceived(Long pengirimanId);


    @Modifying
    @Transactional
    @Query(value=
    "insert into checkpoint_pengiriman "+
    "(pengiriman_id, checkpoint_pengiriman_id_lokasi) "+
    "values(?1, ?2);"
    , nativeQuery = true)
    public void insertCheckpoint(Long pengirimanId, Long lokasiId);


    @Query(value="""
            select
                *
            from 
                pengiriman
            where
                id_pengiriman = ?1;
            """, nativeQuery = true)
    List<Pengiriman> printByIdPengiriman(Long id_pengiriman);
}
