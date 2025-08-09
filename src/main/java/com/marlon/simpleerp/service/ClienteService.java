package com.marlon.simpleerp.service;

import com.marlon.simpleerp.dto.ClienteDTO;
import java.util.List;

public interface ClienteService {
    ClienteDTO salvar(ClienteDTO dto);
    ClienteDTO atualizar(Long id, ClienteDTO dto);
    void deletar(Long id);
    ClienteDTO buscarPorId(Long id);
    List<ClienteDTO> listarTodos();
    List<ClienteDTO> buscarPorNome(String nome);
    ClienteDTO buscarPorCpfCnpj(String cpfCnpj);
}