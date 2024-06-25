package com.example.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Paket;

@Repository
public interface PaketRepository extends JpaRepository<Paket, Long> {

// 	@Query(value = """
// select 
// 	A.id_paket,
// 	STRING_AGG(D.nama_lokasi , ', ') AS checkpoint,
// 	B.is_received 
// from 
// 	Paket as A 
// 	inner join Pengiriman as B on A.id_paket = B.paket_id 
// 	inner join checkpoint_pengiriman C ON B.id_pengiriman = C.pengiriman_id 
//     INNER JOIN Lokasi D ON C.checkpoint_pengiriman_id_lokasi = D.id_lokasi 
// group by 
// 	A.id_paket, B.is_received
// 	""", nativeQuery = true)
//     @Modifying(clearAutomatically = true, flushAutomatically = true)
//     public List<Object> getCheckpointPaket();

@Query(value = """
		select 
			A.id_paket,
			A.berat_paket,
			E.nama_pengirim ,
			F.nama_penerima ,
			STRING_AGG(D.nama_lokasi , ', ') AS checkpoint,
			B.is_received 
		from 
			Paket as A 
			inner join Pengiriman as B on A.id_paket = B.paket_id 
			inner join pengiriman_checkpoint_pengiriman as C ON B.id_pengiriman = C.pengiriman_id_pengiriman
			INNER JOIN Lokasi as D ON C.checkpoint_pengiriman_id_lokasi = D.id_lokasi
			inner join pengirim as E on A.id_pengirim = E.id_pengirim
			inner join penerima as F on A.id_penerima = F.id_penerima 
		where 
			lower(D.nama_lokasi) LIKE lower(concat('%', ?1, '%'))
		group by 
			A.id_paket, B.is_received, E.nama_pengirim, F.nama_penerima
		""", nativeQuery = true)
		public List<Object> getCheckpointPaket(String checkpoint);


}
