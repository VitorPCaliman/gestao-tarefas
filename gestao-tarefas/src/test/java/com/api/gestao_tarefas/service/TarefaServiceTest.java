package com.api.gestao_tarefas.service;

import com.api.gestao_tarefas.dto.TarefaDTO;
import com.api.gestao_tarefas.model.Tarefa;
import com.api.gestao_tarefas.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa;
    private TarefaDTO tarefaDTO;

    @BeforeEach
    void setUp() {
        tarefa = new Tarefa(1l,"Nova Tarefa", "Descrição", "Pendente");
        tarefaDTO = new TarefaDTO("Nova Tarefa", "Descrição", "Pendente");
    }

    @Test
    void deveCriarTarefa() {
        when(tarefaRepository.save(any())).thenReturn(tarefa);
        Tarefa resposta = tarefaService.criarTarefa(tarefaDTO);
        assertNotNull(resposta);
        assertEquals("Nova Tarefa", resposta.getTitulo());
        verify(tarefaRepository, times(1)).save(any());
    }

    @Test
    void deveListarTodasAsTarefas() {
        when(tarefaRepository.findAll()).thenReturn(List.of(tarefa));
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        assertFalse(tarefas.isEmpty());
        assertEquals(1, tarefas.size());
        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarTarefaPorId() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        Tarefa resposta = tarefaService.buscarTarefa(1L);
        assertNotNull(resposta);
        assertEquals(1L, resposta.getId());
        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTarefaNaoEncontrada() {
        when(tarefaRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> tarefaService.buscarTarefa(2L));
    }

    @Test
    void deveAtualizarStatusDaTarefa() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any())).thenReturn(tarefa);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "CONCLUIDA");

        Tarefa resposta = tarefaService.atualizarStatus(1L, requestBody);

        assertNotNull(resposta);
        assertEquals("CONCLUIDA", resposta.getStatus());
        verify(tarefaRepository, times(1)).save(any());
    }

    @Test
    void deveExcluirTarefa() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        doNothing().when(tarefaRepository).delete(any());

        assertDoesNotThrow(() -> tarefaService.excluirTarefa(1L));
        verify(tarefaRepository, times(1)).delete(any());
    }
}
