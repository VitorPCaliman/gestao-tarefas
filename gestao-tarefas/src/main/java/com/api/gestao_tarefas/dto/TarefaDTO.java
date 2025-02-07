package com.api.gestao_tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


public record TarefaDTO(
        @NotBlank(message = "O título é obrigatório.") String titulo,
        String descricao,
        String status
) {
}
