package com.marlon.simpleerp.controller;

import com.marlon.simpleerp.dto.ContaPagarDTO;
import com.marlon.simpleerp.service.ContaPagarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas-pagar")
public class ContaPagarController {

    @Autowired
    private ContaPagarService contaPagarService;

    @PostMapping
    public ResponseEntity<ContaPagarDTO> salvar(@Valid @RequestBody ContaPagarDTO dto) {
        ContaPagarDTO salvo = contaPagarService.salvar(dto);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaPagarDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ContaPagarDTO dto) {
        ContaPagarDTO atualizado = contaPagarService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<Void> pagar(@PathVariable Long id) {
        contaPagarService.pagar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContaPagarDTO>> listarTodos(
            @RequestParam(required = false) String status) {
        List<ContaPagarDTO> lista = status != null ?
                contaPagarService.listarPorStatus(status) :
                contaPagarService.listarVencidas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalAPagarNoMes(
            @RequestParam int ano,
            @RequestParam int mes) {
        Double total = contaPagarService.getTotalAPagarNoMes(ano, mes);
        return ResponseEntity.ok(total != null ? total : 0.0);
    }
}