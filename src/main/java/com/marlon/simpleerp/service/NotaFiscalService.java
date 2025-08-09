package com.marlon.simpleerp.service;

import com.marlon.simpleerp.dto.NotaFiscalDTO;
import java.time.LocalDate;
import java.util.List;

public interface NotaFiscalService {
    NotaFiscalDTO emitir(NotaFiscalDTO dto);
    NotaFiscalDTO buscarPorId(Long id);
    List<NotaFiscalDTO> listarPorTipo(String tipo);
    List<NotaFiscalDTO> listarPorPeriodo(LocalDate inicio, LocalDate fim);
    void cancelar(Long id);
}