package com.api.gestao_tarefas.controller;

import com.api.gestao_tarefas.dto.TarefaDTO;
import com.api.gestao_tarefas.model.Tarefa;
import com.api.gestao_tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody TarefaDTO dto) {
        return ResponseEntity.ok(tarefaService.criarTarefa(dto));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarTarefa(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        return ResponseEntity.ok(tarefaService.atualizarStatus(id, requestBody));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
