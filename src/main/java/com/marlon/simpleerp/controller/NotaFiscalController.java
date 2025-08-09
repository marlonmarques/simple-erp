package com.marlon.simpleerp.controller;

import com.marlon.simpleerp.dto.NotaFiscalDTO;
import com.marlon.simpleerp.service.NotaFiscalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/api/notas-fiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping("/emitir")
    public ResponseEntity<NotaFiscalDTO> emitir(@Valid @RequestBody NotaFiscalDTO dto) {
        NotaFiscalDTO emitida = notaFiscalService.emitir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(emitida.id())
                .toUri();
        return ResponseEntity.created(uri).body(emitida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscalDTO> buscarPorId(@PathVariable Long id) {
        NotaFiscalDTO nota = notaFiscalService.buscarPorId(id);
        return ResponseEntity.ok(nota);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<NotaFiscalDTO>> listarPorTipo(@PathVariable String tipo) {
        List<NotaFiscalDTO> lista = notaFiscalService.listarPorTipo(tipo);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<NotaFiscalDTO>> listarPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        List<NotaFiscalDTO> lista = notaFiscalService.listarPorPeriodo(inicio, fim);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<NotaFiscalDTO> cancelar(@PathVariable Long id) {
        notaFiscalService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}