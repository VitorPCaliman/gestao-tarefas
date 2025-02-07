package com.api.gestao_tarefas.service;

import com.api.gestao_tarefas.dto.TarefaDTO;
import com.api.gestao_tarefas.model.Tarefa;
import com.api.gestao_tarefas.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa criarTarefa(TarefaDTO dto) {
        Tarefa tarefa = new Tarefa(dto.titulo(),dto.descricao(),dto.status());
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefa(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Tarefa atualizarStatus(Long id, Map<String, String> requestBody) {
        String novoStatus = requestBody.get("status");
        if (novoStatus == null || novoStatus.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O status não pode ser vazio.");
        }
        Tarefa tarefa = buscarTarefa(id);
        tarefa.setStatus(novoStatus);
        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        Tarefa tarefa = buscarTarefa(id);
        tarefaRepository.delete(tarefa);
    }
}
