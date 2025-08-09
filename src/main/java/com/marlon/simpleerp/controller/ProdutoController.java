package com.marlon.simpleerp.controller;

import com.marlon.simpleerp.dto.ProdutoDTO;
import com.marlon.simpleerp.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO salvo = produtoService.salvar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.id())
                .toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO atualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos(
            @RequestParam(required = false) String nome) {
        List<ProdutoDTO> lista = nome != null && !nome.isEmpty() ?
                produtoService.buscarPorNome(nome) :
                produtoService.listarTodos();
        return ResponseEntity.ok(lista);
    }
}