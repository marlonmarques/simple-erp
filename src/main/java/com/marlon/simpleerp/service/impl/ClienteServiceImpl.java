package com.marlon.simpleerp.service.impl;

import com.marlon.simpleerp.dto.ClienteDTO;
import com.marlon.simpleerp.exception.RecursoNaoEncontradoException;
import com.marlon.simpleerp.model.Cliente;
import com.marlon.simpleerp.repository.ClienteRepository;
import com.marlon.simpleerp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        copiarParaEntidade(dto, cliente);
        Cliente salvo = clienteRepository.save(cliente);
        return converterParaDTO(salvo);
    }

    @Override
    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + id));

        if (dto.cpfCnpj() != null && !dto.cpfCnpj().equals(cliente.getCpfCnpj())) {
            if (clienteRepository.findByCpfCnpj(dto.cpfCnpj()).isPresent()) {
                throw new IllegalArgumentException("CPF/CNPJ já cadastrado.");
            }
        }

        copiarParaEntidade(dto, cliente);
        Cliente atualizado = clienteRepository.save(cliente);
        return converterParaDTO(atualizado);
    }

    @Override
    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado: " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + id));
        return converterParaDTO(cliente);
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDTO> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscarPorCpfCnpj(String cpfCnpj) {
        return clienteRepository.findByCpfCnpj(cpfCnpj)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com CPF/CNPJ: " + cpfCnpj));
    }

    // Métodos auxiliares
    private void copiarParaEntidade(ClienteDTO dto, Cliente cliente) {
        cliente.setNome(dto.nome());
        cliente.setCpfCnpj(dto.cpfCnpj());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setCep(dto.cep());
        cliente.setLogradouro(dto.logradouro());
        cliente.setNumero(dto.numero());
        cliente.setBairro(dto.bairro());
        cliente.setCidade(dto.cidade());
        cliente.setEstado(dto.estado());
    }

    private ClienteDTO converterParaDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpfCnpj(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCep(),
                cliente.getLogradouro(),
                cliente.getNumero(),
                cliente.getBairro(),
                cliente.getCidade(),
                cliente.getEstado()
        );
    }
}