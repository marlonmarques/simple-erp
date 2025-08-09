package com.marlon.simpleerp.service;

import com.marlon.simpleerp.dto.ContaPagarDTO;
import java.time.LocalDate;
import java.util.List;

public interface ContaPagarService {
    ContaPagarDTO salvar(ContaPagarDTO dto);
    ContaPagarDTO atualizar(Long id, ContaPagarDTO dto);
    void pagar(Long id);
    List<ContaPagarDTO> listarVencidas();
    List<ContaPagarDTO> listarPorStatus(String status);
    Double getTotalAPagarNoMes(int ano, int mes);
}