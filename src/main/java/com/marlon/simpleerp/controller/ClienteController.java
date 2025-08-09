package com.marlon.simpleerp.controller;

import com.marlon.simpleerp.dto.ClienteDTO;
import com.marlon.simpleerp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO dto) {
        ClienteDTO salvo = clienteService.salvar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.id())
                .toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        ClienteDTO atualizado = clienteService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos(
            @RequestParam(required = false) String nome) {
        List<ClienteDTO> lista = nome != null && !nome.isEmpty() ?
                clienteService.buscarPorNome(nome) :
                clienteService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public ResponseEntity<ClienteDTO> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        ClienteDTO cliente = clienteService.buscarPorCpfCnpj(cpfCnpj);
        return ResponseEntity.ok(cliente);
    }
}