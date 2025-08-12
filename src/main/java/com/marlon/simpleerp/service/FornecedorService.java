package com.marlon.simpleerp.service;

import com.marlon.simpleerp.dto.FornecedorDTO;
import java.util.List;

public interface FornecedorService {

    /**
     * Salva um novo fornecedor ou atualiza um existente (se ID for fornecido).
     */
    FornecedorDTO salvar(FornecedorDTO dto);

    /**
     * Atualiza um fornecedor pelo ID.
     */
    FornecedorDTO atualizar(Long id, FornecedorDTO dto);

    /**
     * Deleta um fornecedor pelo ID.
     */
    void deletar(Long id);

    /**
     * Busca um fornecedor pelo ID.
     */
    FornecedorDTO buscarPorId(Long id);

    /**
     * Lista todos os fornecedores cadastrados.
     */
    List<FornecedorDTO> listarTodos();

    /**
     * Busca fornecedores pelo nome (parcial e ignorando maiúsculas).
     */
    List<FornecedorDTO> buscarPorNome(String nome);

    /**
     * Busca um fornecedor pelo CNPJ.
     */
    FornecedorDTO buscarPorCnpj(String cnpj);
}