package com.example.tracking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.tracking.exception.LocationIsEmpty;
import com.example.tracking.model.Lokasi;
import com.example.tracking.service.LokasiService;


@ExtendWith(MockitoExtension.class)
public class LokasiControllerTest {
    
    @Mock
    private LokasiService lokasiService;

    @InjectMocks
    private LokasiController lokasiController;

    private Lokasi lokasi1;
    private Lokasi lokasi2;
    private Lokasi lokasi3;

    @BeforeEach
    void setUp() throws LocationIsEmpty {
        lokasi1 = new Lokasi();
        lokasi1.setId_lokasi(1L);
        lokasi1.setNamaLokasi("Lokasi 1");

        lokasi2 = new Lokasi();
        lokasi2.setId_lokasi(2L);
        lokasi2.setNamaLokasi("Lokasi 2");

        lokasi3 = new Lokasi();
        lokasi3.setId_lokasi(3L);
        lokasi3.setNamaLokasi("Lokasi 3");

        lenient().when(lokasiService.getAllLoc()).thenReturn(Arrays.asList(lokasi1, lokasi2, lokasi3));
        lenient().when(lokasiService.addLoc(lokasi1)).thenReturn(lokasi1);
        lenient().when(lokasiService.getCheckpointLokasi()).thenReturn(Arrays.asList(new Object(), new Object(), new Object()));
    }


    @Test
    void getAllocSuccess(){
        ResponseEntity<List<Lokasi>> response = lokasiController.getAllLoc();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
        assertEquals("Lokasi 2", response.getBody().get(1).getNamaLokasi());
    }

    @Test
    void getAllocFail() throws LocationIsEmpty{
        doThrow(new RuntimeException("Exception occured")).when(lokasiService).getAllLoc();

        ResponseEntity<List<Lokasi>> response = lokasiController.getAllLoc();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void addLocSuccess(){
        ResponseEntity<?> response = lokasiController.addLoc(lokasi1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lokasi(id_lokasi=1, namaLokasi=Lokasi 1, alamat=null)Berhasil menambahkan lokasi Lokasi 1", response.getBody());
    }

    @Test
    void addLocFail() throws LocationIsEmpty{
        doThrow(new RuntimeException("Exception occured")).when(lokasiService).addLoc(lokasi1);

        ResponseEntity<?> response = lokasiController.addLoc(lokasi1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Gagal menambahkan lokasi Exception occured", response.getBody());
    }

    @Test
    void getCheckpointLokasiSuccess(){
        ResponseEntity<List<Object>> response = lokasiController.getCheckpointLokasi();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
    }

    @Test
    void getCheckpointLokasiFail() throws LocationIsEmpty{
        doThrow(new RuntimeException("Exception occured")).when(lokasiService).getCheckpointLokasi();

        ResponseEntity<List<Object>> response = lokasiController.getCheckpointLokasi();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
