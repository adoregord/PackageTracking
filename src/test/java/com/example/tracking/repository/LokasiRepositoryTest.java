package com.example.tracking.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.tracking.model.Lokasi;


@DataJpaTest
public class LokasiRepositoryTest {

    @Autowired
    private LokasiRepository lokasiRepository;

    @BeforeEach
    public void setUp() {
        // lokasiRepository.deleteAll();
        Lokasi lokasi1 = new Lokasi(1L, "Lokasi 1", "<Lokasi 1-0001>");
        Lokasi lokasi2 = new Lokasi(2L, "Lokasi 2", "<Lokasi 2-0002>");
        lokasiRepository.save(lokasi1);
        lokasiRepository.save(lokasi2);
    }

    @AfterEach
    public void tearDown() {
        lokasiRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        List<Lokasi> lokasiList = lokasiRepository.findAll();
        assertEquals(2, lokasiList.size());
    }

    @Test
    public void testGetCheckpointLokasi() {

        List<Object> checkpoints = lokasiRepository.getCheckpointLokasi();
        assertEquals(0, checkpoints.size()); // checkpoint pada query mengambil nilai dari tabel check_point, karena
                                             // kosong maka expectednya harusnya 0
        assertEquals(2, lokasiRepository.findAll().size());

    }

    @Test
    public void testFindById() {
        Optional<Lokasi> lokasi = lokasiRepository.findById(5L);
        assertTrue(lokasi.isPresent());
        assertEquals("Lokasi 1", lokasi.get().getNamaLokasi());
        assertEquals(2, lokasiRepository.findAll().size());
    }
}
