package com.example.tracking.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.tracking.exception.LocationIsEmpty;
import com.example.tracking.model.Lokasi;
import com.example.tracking.repository.LokasiRepository;
import com.example.tracking.service.LokasiService;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class LokasiServiceTest {

    @Mock
    private LokasiRepository lokasiRepository;

    @InjectMocks
    private LokasiService lokasiService;

    private Lokasi lokasi1;
    private Lokasi lokasi2;
    private Lokasi lokasi3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GetAllocSuccess() throws LocationIsEmpty {

        lokasi1 = new Lokasi();
        lokasi1.setId_lokasi(1L);
        lokasi1.setNamaLokasi("Lokasi 1");
        lokasi1.setAlamat("<Lokasi 1-0001>");

        lokasi2 = new Lokasi();
        lokasi2.setId_lokasi(2L);
        lokasi2.setNamaLokasi("Lokasi 2");
        lokasi2.setAlamat("<Lokasi 2-0002>");

        lokasi3 = new Lokasi();
        lokasi3.setId_lokasi(3L);
        lokasi3.setNamaLokasi("Lokasi 3");
        lokasi3.setAlamat("<Lokasi 3-0003>");

        lenient().when(lokasiRepository.findAll()).thenReturn(Arrays.asList(lokasi1, lokasi2, lokasi3));

        List<Lokasi> result = lokasiService.getAllLoc();

        assertEquals(3, result.size());
        assertEquals("Lokasi 1", result.getFirst().getNamaLokasi());
        assertEquals("Lokasi 2", result.get(1).getNamaLokasi());
        assertEquals("<Lokasi 3-0003>", result.get(2).getAlamat());
        verify(lokasiRepository, times(3)).findAll();
    }

    @Test
    public void AddLocSuccess() throws LocationIsEmpty{
        lokasi1 = new Lokasi();
        lokasi1.setId_lokasi(1L);
        lokasi1.setNamaLokasi("Lokasi 1");
        lokasi1.setAlamat("<Lokasi 1-0001>");

        lenient().when(lokasiRepository.save(lokasi1)).thenReturn(lokasi1);

        Lokasi result = lokasiService.addLoc(lokasi1);
        assertEquals("Lokasi 1", result.getNamaLokasi());
        verify(lokasiRepository, times(1)).save(any());
    }

    @Test
    public void AddLocNullName(){
        Lokasi lokasiFail = new Lokasi();

        Exception exception = assertThrows(LocationIsEmpty.class, () -> lokasiService.addLoc(lokasiFail));

        log.info("For checking " + exception.getMessage());
        assertEquals("Nama lokasi harus diisi", exception.getMessage());
        verify(lokasiRepository, times(0)).save(any());
    }

    @Test
    public void GetCheckpointLokasiSuccess() throws LocationIsEmpty{

        lokasi1 = new Lokasi();
        lokasi1.setId_lokasi(1L);
        lokasi1.setNamaLokasi("Lokasi 1");
        lokasi1.setAlamat("<Lokasi 1-0001>");

        lokasi2 = new Lokasi();
        lokasi2.setId_lokasi(2L);
        lokasi2.setNamaLokasi("Lokasi 2");
        lokasi2.setAlamat("<Lokasi 2-0002>");

        lokasi3 = new Lokasi();
        lokasi3.setId_lokasi(3L);
        lokasi3.setNamaLokasi("Lokasi 3");
        lokasi3.setAlamat("<Lokasi 3-0003>");

        lenient().when(lokasiRepository.findAll()).thenReturn(Arrays.asList(lokasi1, lokasi2, lokasi3));
        lenient().when(lokasiRepository.getCheckpointLokasi())
        .thenReturn(Arrays.asList(new Object(), new Object(), new Object()));
        
        List<Object> result = lokasiService.getCheckpointLokasi();

        assertEquals(3, result.size());
        verify(lokasiRepository, times(1)).getCheckpointLokasi();
    }

    @Test
    public void getCheckpointLokasiFail() throws LocationIsEmpty{
        when(lokasiRepository.findAll()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(LocationIsEmpty.class, () -> lokasiService.getCheckpointLokasi());

        assertEquals("Lokasi tidak ditemukan", exception.getMessage());
        verify(lokasiRepository, times(0)).getCheckpointLokasi();
    }
}
