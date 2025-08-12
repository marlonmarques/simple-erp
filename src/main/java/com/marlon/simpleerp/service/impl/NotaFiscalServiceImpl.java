package com.marlon.simpleerp.service.impl;

import com.marlon.simpleerp.dto.NotaFiscalDTO;
import com.marlon.simpleerp.exception.RecursoNaoEncontradoException;
import com.marlon.simpleerp.model.Cliente;
import com.marlon.simpleerp.model.Fornecedor;
import com.marlon.simpleerp.model.NotaFiscal;
import com.marlon.simpleerp.repository.ClienteRepository;
import com.marlon.simpleerp.repository.FornecedorRepository;
import com.marlon.simpleerp.repository.NotaFiscalRepository;
import com.marlon.simpleerp.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public NotaFiscalDTO emitir(NotaFiscalDTO dto) {
        NotaFiscal nota = new NotaFiscal();
        nota.setNumeroNf(dto.numeroNf());
        nota.setTipo(NotaFiscal.TipoNota.valueOf(dto.tipo()));
        nota.setValorTotal(dto.valorTotal());
        nota.setDataEmissao(dto.dataEmissao() != null ? dto.dataEmissao() : LocalDate.now());
        nota.setStatus(NotaFiscal.Status.EMITIDA);

        // Gera chave de acesso fictícia (44 dígitos)
        nota.setChaveAcesso(gerarChaveAcesso());

        // Cliente (para SAIDA)
        if (dto.tipo().equals("SAIDA") && dto.clienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.clienteId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + dto.clienteId()));
            nota.setCliente(cliente);
        }

        // Fornecedor (para ENTRADA)
        if (dto.tipo().equals("ENTRADA") && dto.fornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: " + dto.fornecedorId()));
            nota.setFornecedor(fornecedor);
        }

        NotaFiscal salva = notaFiscalRepository.save(nota);
        return converterParaDTO(salva);
    }

    @Override
    public NotaFiscalDTO buscarPorId(Long id) {
        NotaFiscal nota = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Nota Fiscal não encontrada: " + id));
        return converterParaDTO(nota);
    }

    @Override
    public List<NotaFiscalDTO> listarPorTipo(String tipo) {
        NotaFiscal.TipoNota tipoEnum = NotaFiscal.TipoNota.valueOf(tipo.toUpperCase());
        return notaFiscalRepository.findByTipo(tipoEnum).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotaFiscalDTO> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return notaFiscalRepository.findByPeriodo(inicio, fim).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelar(Long id) {
        NotaFiscal nota = notaFiscalRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Nota Fiscal não encontrada: " + id));
        if (nota.getStatus() == NotaFiscal.Status.CANCELADA) {
            throw new IllegalArgumentException("Nota já está cancelada.");
        }
        nota.setStatus(NotaFiscal.Status.CANCELADA);
        notaFiscalRepository.save(nota);
    }

    private String gerarChaveAcesso() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 44; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private NotaFiscalDTO converterParaDTO(NotaFiscal nota) {
        return new NotaFiscalDTO(
                nota.getId(),
                nota.getNumeroNf(),
                nota.getTipo().name(),
                nota.getCliente() != null ? nota.getCliente().getId() : null,
                nota.getFornecedor() != null ? nota.getFornecedor().getId() : null,
                nota.getValorTotal(),
                nota.getDataEmissao(),
                nota.getChaveAcesso(),
                nota.getStatus().name()
        );
    }
}