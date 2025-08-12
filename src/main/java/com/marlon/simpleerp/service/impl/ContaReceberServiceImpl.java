package com.marlon.simpleerp.service.impl;

import com.marlon.simpleerp.dto.ContaReceberDTO;
import com.marlon.simpleerp.exception.RecursoNaoEncontradoException;
import com.marlon.simpleerp.model.ContaReceber;
import com.marlon.simpleerp.model.Cliente;
import com.marlon.simpleerp.repository.ClienteRepository;
import com.marlon.simpleerp.repository.ContaReceberRepository;
import com.marlon.simpleerp.service.ContaReceberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaReceberServiceImpl implements ContaReceberService {

    @Autowired
    private ContaReceberRepository contaReceberRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ContaReceberDTO salvar(ContaReceberDTO dto) {
        ContaReceber conta = new ContaReceber();
        copiarParaEntidade(dto, conta);
        ContaReceber salva = contaReceberRepository.save(conta);
        return converterParaDTO(salva);
    }

    @Override
    public ContaReceberDTO atualizar(Long id, ContaReceberDTO dto) {
        ContaReceber conta = contaReceberRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta a receber não encontrada: " + id));
        copiarParaEntidade(dto, conta);
        ContaReceber atualizada = contaReceberRepository.save(conta);
        return converterParaDTO(atualizada);
    }

    @Override
    public void receber(Long id) {
        ContaReceber conta = contaReceberRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta a receber não encontrada: " + id));
        if (conta.getStatus() == ContaReceber.StatusRecebimento.RECEBIDO) {
            throw new IllegalArgumentException("Conta já foi recebida.");
        }
        conta.setStatus(ContaReceber.StatusRecebimento.RECEBIDO);
        conta.setDataRecebimento(LocalDate.now());
        contaReceberRepository.save(conta);
    }

    @Override
    public List<ContaReceberDTO> listarAtrasadas() {
        return contaReceberRepository.findAtrasadas(LocalDate.now()).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContaReceberDTO> listarPorStatus(String status) {
        ContaReceber.StatusRecebimento statusEnum = ContaReceber.StatusRecebimento.valueOf(status.toUpperCase());
        return contaReceberRepository.findByStatus(statusEnum).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalAReceberNoMes(int ano, int mes) {
        Double total = contaReceberRepository.sumByMesAndAno(ano, mes);
        return total != null ? total : 0.0;
    }

    private void copiarParaEntidade(ContaReceberDTO dto, ContaReceber conta) {
        conta.setDescricao(dto.descricao());
        conta.setValor(dto.valor());
        conta.setDataVencimento(dto.dataVencimento());
        conta.setDataRecebimento(dto.dataRecebimento());
        conta.setStatus(ContaReceber.StatusRecebimento.valueOf(dto.status().toUpperCase()));

        if (dto.clienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.clienteId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + dto.clienteId()));
            conta.setCliente(cliente);
        }
    }

    private ContaReceberDTO converterParaDTO(ContaReceber conta) {
        return new ContaReceberDTO(
                conta.getId(),
                conta.getDescricao(),
                conta.getValor(),
                conta.getDataVencimento(),
                conta.getDataRecebimento(),
                conta.getStatus().name(),
                conta.getCliente() != null ? conta.getCliente().getId() : null
        );
    }
}