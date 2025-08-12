package com.marlon.simpleerp.service.impl;

import com.marlon.simpleerp.dto.FornecedorDTO;
import com.marlon.simpleerp.exception.RecursoNaoEncontradoException;
import com.marlon.simpleerp.model.Fornecedor;
import com.marlon.simpleerp.repository.FornecedorRepository;
import com.marlon.simpleerp.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public FornecedorDTO salvar(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        copiarParaEntidade(dto, fornecedor);
        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return converterParaDTO(salvo);
    }

    @Override
    public FornecedorDTO atualizar(Long id, FornecedorDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: " + id));

        if (dto.cnpj() != null && !dto.cnpj().equals(fornecedor.getCnpj())) {
            if (fornecedorRepository.findByCnpj(dto.cnpj()).isPresent()) {
                throw new IllegalArgumentException("CNPJ já cadastrado.");
            }
        }

        copiarParaEntidade(dto, fornecedor);
        Fornecedor atualizado = fornecedorRepository.save(fornecedor);
        return converterParaDTO(atualizado);
    }

    @Override
    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Fornecedor não encontrado: " + id);
        }
        fornecedorRepository.deleteById(id);
    }

    @Override
    public FornecedorDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: " + id));
        return converterParaDTO(fornecedor);
    }

    @Override
    public List<FornecedorDTO> listarTodos() {
        return fornecedorRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FornecedorDTO> buscarPorNome(String nome) {
        return fornecedorRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FornecedorDTO buscarPorCnpj(String cnpj) {
        return null;
    }

    private void copiarParaEntidade(FornecedorDTO dto, Fornecedor fornecedor) {
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setSite(dto.site());
    }

    private FornecedorDTO converterParaDTO(Fornecedor fornecedor) {
        return new FornecedorDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getEmail(),
                fornecedor.getTelefone(),
                fornecedor.getSite()
        );
    }
}