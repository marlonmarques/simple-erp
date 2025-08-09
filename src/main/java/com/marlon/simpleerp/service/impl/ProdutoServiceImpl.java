package com.marlon.simpleerp.service.impl;

import com.marlon.simpleerp.dto.ProdutoDTO;
import com.marlon.simpleerp.exception.RecursoNaoEncontradoException;
import com.marlon.simpleerp.model.Produto;
import com.marlon.simpleerp.model.Fornecedor;
import com.marlon.simpleerp.repository.ProdutoRepository;
import com.marlon.simpleerp.repository.FornecedorRepository;
import com.marlon.simpleerp.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = new Produto();
        copiarParaEntidade(dto, produto);
        Produto salvo = produtoRepository.save(produto);
        return new ProdutoDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getCodigoBarras(),
                salvo.getPreco(),
                salvo.getQuantidadeEstoque(),
                salvo.getFornecedor() != null ? salvo.getFornecedor().getId() : null,
                salvo.getDescricao()
        );
    }

    @Override
    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + id));

        // Verifica duplicidade de código de barras
        if (dto.codigoBarras() != null && !dto.codigoBarras().equals(produto.getCodigoBarras())) {
            if (produtoRepository.existsByCodigoBarrasAndIdNot(dto.codigoBarras(), id)) {
                throw new IllegalArgumentException("Código de barras já cadastrado.");
            }
        }

        copiarParaEntidade(dto, produto);
        Produto atualizado = produtoRepository.save(produto);
        return new ProdutoDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getCodigoBarras(),
                atualizado.getPreco(),
                atualizado.getQuantidadeEstoque(),
                atualizado.getFornecedor() != null ? atualizado.getFornecedor().getId() : null,
                atualizado.getDescricao()
        );
    }

    @Override
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado: " + id);
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + id));
        return converterParaDTO(produto);
    }

    @Override
    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDTO> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares
    private void copiarParaEntidade(ProdutoDTO dto, Produto produto) {
        produto.setNome(dto.nome());
        produto.setCodigoBarras(dto.codigoBarras());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());
        produto.setDescricao(dto.descricao());

        if (dto.fornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: " + dto.fornecedorId()));
            produto.setFornecedor(fornecedor);
        } else {
            produto.setFornecedor(null);
        }
    }

    private ProdutoDTO converterParaDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getCodigoBarras(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                produto.getFornecedor() != null ? produto.getFornecedor().getId() : null,
                produto.getDescricao()
        );
    }
}