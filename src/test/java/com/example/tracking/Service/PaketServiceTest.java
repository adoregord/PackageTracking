package com.example.tracking.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.tracking.exception.PaketException;
import com.example.tracking.model.Lokasi;
import com.example.tracking.model.Paket;
import com.example.tracking.model.Penerima;
import com.example.tracking.model.Pengirim;
import com.example.tracking.repository.PaketRepository;
import com.example.tracking.service.PaketService;

public class PaketServiceTest {

    @Mock
    private PaketRepository paketRepository;

    @InjectMocks
    private PaketService paketService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GetAllPaketSuccess() throws PaketException {
        List<Paket> paketList = new ArrayList<>();
        paketList.add(new Paket());
        paketList.add(new Paket());
        when(paketRepository.findAll()).thenReturn(paketList);

        List<Paket> result = paketService.getAllPaket();

        assertEquals(2, result.size());
        verify(paketRepository, times(3)).findAll();
    }

    @Test
    public void GetAllPaketFail() throws PaketException {
        List<Paket> paketLists = new ArrayList<>();
        when(paketRepository.findAll()).thenReturn(paketLists);
        Exception exception = assertThrows(PaketException.class, () -> paketService.getAllPaket());

        assertEquals("Paket tidak ditemukan", exception.getMessage());
        verify(paketRepository, times(2)).findAll();
    }

    @Test
    public void AddPaketSuccess() throws PaketException {
        Pengirim pengirim1 = new Pengirim();
        pengirim1.setId_pengirim(1L);

        Penerima penerima1 = new Penerima();
        penerima1.setId_penerima(1L);

        Lokasi tujuanPengiriman = new Lokasi();
        tujuanPengiriman.setId_lokasi(1L);

        Paket paket = new Paket();
        paket.setId_paket(1L);
        paket.setPengirim(pengirim1);
        paket.setPenerima(penerima1);
        paket.setBeratPaket(50.5);
        paket.setTujuanPengiriman(tujuanPengiriman);


        when(paketRepository.save(paket)).thenReturn(paket);

        Paket result = paketService.addPaket(paket);

        assertEquals(1, result.getId_paket());
        verify(paketRepository, times(1)).save(paket);
    }

    @Test void AddPaketFail_NoPengirim() throws PaketException {
        Pengirim pengirim1 = new Pengirim();
        pengirim1.setId_pengirim(1L);

        Penerima penerima1 = new Penerima();
        penerima1.setId_penerima(1L);

        Lokasi tujuanPengiriman = new Lokasi();
        tujuanPengiriman.setId_lokasi(1L);

        Paket paket = new Paket();
        paket.setId_paket(1L);
        // paket.setPengirim(pengirim1);
        paket.setPenerima(penerima1);
        paket.setBeratPaket(50.5);
        paket.setTujuanPengiriman(tujuanPengiriman);
        Exception exception = assertThrows(PaketException.class, () -> paketService.addPaket(paket));

        assertEquals("Pengirim harus diisi", exception.getMessage());
        verify(paketRepository, times(0)).save(paket);
    }

    @Test void AddPaketFail_NoPenerima() throws PaketException {
        Pengirim pengirim1 = new Pengirim();
        pengirim1.setId_pengirim(1L);

        Penerima penerima1 = new Penerima();
        penerima1.setId_penerima(1L);

        Lokasi tujuanPengiriman = new Lokasi();
        tujuanPengiriman.setId_lokasi(1L);

        Paket paket = new Paket();
        paket.setId_paket(1L);
        paket.setPengirim(pengirim1);
        // paket.setPenerima(penerima1);
        paket.setBeratPaket(50.5);
        paket.setTujuanPengiriman(tujuanPengiriman);
        Exception exception = assertThrows(PaketException.class, () -> paketService.addPaket(paket));

        assertEquals("Penerima harus diisi", exception.getMessage());
        verify(paketRepository, times(0)).save(paket);
    }

    @Test void AddPaketFail_NoLokasi() throws PaketException {
        Pengirim pengirim1 = new Pengirim();
        pengirim1.setId_pengirim(1L);

        Penerima penerima1 = new Penerima();
        penerima1.setId_penerima(1L);

        Lokasi tujuanPengiriman = new Lokasi();
        tujuanPengiriman.setId_lokasi(1L);

        Paket paket = new Paket();
        paket.setId_paket(1L);
        paket.setPengirim(pengirim1);
        paket.setPenerima(penerima1);
        paket.setBeratPaket(50.5);
        // paket.setTujuanPengiriman(tujuanPengiriman);
        Exception exception = assertThrows(PaketException.class, () -> paketService.addPaket(paket));

        assertEquals("Tujuan pengiriman harus diisi", exception.getMessage());
        verify(paketRepository, times(0)).save(paket);
    }

    @Test void AddPaketFail_NoBeratPaket() throws PaketException {
        Pengirim pengirim1 = new Pengirim();
        pengirim1.setId_pengirim(1L);

        Penerima penerima1 = new Penerima();
        penerima1.setId_penerima(1L);

        Lokasi tujuanPengiriman = new Lokasi();
        tujuanPengiriman.setId_lokasi(1L);

        Paket paket = new Paket();
        paket.setId_paket(1L);
        paket.setPengirim(pengirim1);
        paket.setPenerima(penerima1);
        // paket.setBeratPaket(50.5);
        paket.setTujuanPengiriman(tujuanPengiriman);
        Exception exception = assertThrows(PaketException.class, () -> paketService.addPaket(paket));

        assertEquals("Berat paket harus diisi", exception.getMessage());
        verify(paketRepository, times(0)).save(paket);
    }
}
