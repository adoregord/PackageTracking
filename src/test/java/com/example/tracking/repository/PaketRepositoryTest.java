package com.example.tracking.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
public class PaketRepositoryTest {

    @Autowired
    private PaketRepository paketRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // @BeforeEach
    // public void setup() {
    //     // Insert test data
    //     Lokasi lokasi1 = new Lokasi(null, "Lokasi 1", "<Lokasi 1-0001>");
    //     Lokasi lokasi2 = new Lokasi(null, "Lokasi 2", "<Lokasi 2-0002>");
    //     entityManager.persist(lokasi1);
    //     entityManager.persist(lokasi2);

    //     Pengirim pengirim = new Pengirim();
    //     pengirim.setNamaPengirim("Pengirim 1");
    //     entityManager.persist(pengirim);

    //     Penerima penerima = new Penerima();
    //     penerima.setNamaPenerima("Penerima 1");
    //     entityManager.persist(penerima);

    //     Paket paket = new Paket(null, "10kg", pengirim, penerima, lokasi1);
    //     entityManager.persist(paket);
    //     entityManager.flush();
    // }

    @Test
    public void GetCheckpointPaket() {

        List<Object> checkpoint = paketRepository.getCheckpointPaket("");
        assertFalse(!checkpoint.isEmpty());
        log.info("assert not null" + checkpoint.size());
    }

}