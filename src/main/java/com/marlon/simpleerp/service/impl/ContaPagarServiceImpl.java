package com.marlon.simpleerp.service.impl;

import com.marlon.simpleerp.dto.ContaPagarDTO;
import com.marlon.simpleerp.exception.RecursoNaoEncontradoException;
import com.marlon.simpleerp.model.ContaPagar;
import com.marlon.simpleerp.model.Fornecedor;
import com.marlon.simpleerp.repository.ContaPagarRepository;
import com.marlon.simpleerp.repository.FornecedorRepository;
import com.marlon.simpleerp.service.ContaPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaPagarServiceImpl implements ContaPagarService {

    @Autowired
    private ContaPagarRepository contaPagarRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public ContaPagarDTO salvar(ContaPagarDTO dto) {
        ContaPagar conta = new ContaPagar();
        copiarParaEntidade(dto, conta);
        ContaPagar salva = contaPagarRepository.save(conta);
        return converterParaDTO(salva);
    }

    @Override
    public ContaPagarDTO atualizar(Long id, ContaPagarDTO dto) {
        ContaPagar conta = contaPagarRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta a pagar não encontrada: " + id));
        copiarParaEntidade(dto, conta);
        ContaPagar atualizada = contaPagarRepository.save(conta);
        return converterParaDTO(atualizada);
    }

    @Override
    public void pagar(Long id) {
        ContaPagar conta = contaPagarRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta a pagar não encontrada: " + id));
        if (conta.getStatus() == ContaPagar.StatusPagamento.PAGO) {
            throw new IllegalArgumentException("Conta já está paga.");
        }
        conta.setStatus(ContaPagar.StatusPagamento.PAGO);
        conta.setDataPagamento(LocalDate.now());
        contaPagarRepository.save(conta);
    }

    @Override
    public List<ContaPagarDTO> listarVencidas() {
        return contaPagarRepository.findVencidas(LocalDate.now()).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContaPagarDTO> listarPorStatus(String status) {
        ContaPagar.StatusPagamento statusEnum = ContaPagar.StatusPagamento.valueOf(status.toUpperCase());
        return contaPagarRepository.findByStatus(statusEnum).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalAPagarNoMes(int ano, int mes) {
        Double total = contaPagarRepository.sumByMesAndAno(ano, mes);
        return total != null ? total : 0.0;
    }

    private void copiarParaEntidade(ContaPagarDTO dto, ContaPagar conta) {
        conta.setDescricao(dto.descricao());
        conta.setValor(dto.valor());
        conta.setDataVencimento(dto.dataVencimento());
        conta.setDataPagamento(dto.dataPagamento());
        conta.setStatus(ContaPagar.StatusPagamento.valueOf(dto.status().toUpperCase()));

        if (dto.fornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: " + dto.fornecedorId()));
            conta.setFornecedor(fornecedor);
        }
    }

    private ContaPagarDTO converterParaDTO(ContaPagar conta) {
        return new ContaPagarDTO(
                conta.getId(),
                conta.getDescricao(),
                conta.getValor(),
                conta.getDataVencimento(),
                conta.getDataPagamento(),
                conta.getStatus().name(),
                conta.getFornecedor() != null ? conta.getFornecedor().getId() : null
        );
    }
}