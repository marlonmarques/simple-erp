package com.marlon.simpleerp.service;

import com.marlon.simpleerp.dto.ContaReceberDTO;
import java.time.LocalDate;
import java.util.List;

public interface ContaReceberService {
    ContaReceberDTO salvar(ContaReceberDTO dto);
    ContaReceberDTO atualizar(Long id, ContaReceberDTO dto);
    void receber(Long id);
    List<ContaReceberDTO> listarAtrasadas();
    List<ContaReceberDTO> listarPorStatus(String status);
    Double getTotalAReceberNoMes(int ano, int mes);
}