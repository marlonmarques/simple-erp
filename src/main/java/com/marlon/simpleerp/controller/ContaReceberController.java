package com.marlon.simpleerp.controller;

import com.marlon.simpleerp.dto.ContaReceberDTO;
import com.marlon.simpleerp.service.ContaReceberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas-receber")
public class ContaReceberController {

    @Autowired
    private ContaReceberService contaReceberService;

    @PostMapping
    public ResponseEntity<ContaReceberDTO> salvar(@Valid @RequestBody ContaReceberDTO dto) {
        ContaReceberDTO salvo = contaReceberService.salvar(dto);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaReceberDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ContaReceberDTO dto) {
        ContaReceberDTO atualizado = contaReceberService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/{id}/receber")
    public ResponseEntity<Void> receber(@PathVariable Long id) {
        contaReceberService.receber(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContaReceberDTO>> listarTodos(
            @RequestParam(required = false) String status) {
        List<ContaReceberDTO> lista = status != null ?
                contaReceberService.listarPorStatus(status) :
                contaReceberService.listarAtrasadas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalAReceberNoMes(
            @RequestParam int ano,
            @RequestParam int mes) {
        Double total = contaReceberService.getTotalAReceberNoMes(ano, mes);
        return ResponseEntity.ok(total != null ? total : 0.0);
    }
}