package com.marlon.simpleerp.service;

import com.marlon.simpleerp.dto.ProdutoDTO;
import java.util.List;

public interface ProdutoService {
    ProdutoDTO salvar(ProdutoDTO dto);
    ProdutoDTO atualizar(Long id, ProdutoDTO dto);
    void deletar(Long id);
    ProdutoDTO buscarPorId(Long id);
    List<ProdutoDTO> listarTodos();
    List<ProdutoDTO> buscarPorNome(String nome);
}