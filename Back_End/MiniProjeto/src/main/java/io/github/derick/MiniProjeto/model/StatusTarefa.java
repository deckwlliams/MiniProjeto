package io.github.derick.MiniProjeto.model;

// Esse ENUM define o STATUS de uma tarefa
// Ele controla em que "fase" a tarefa está
public enum StatusTarefa {

    // A tarefa foi criada, mas ainda NÃO foi iniciada
    PENDENTE,

    // A tarefa já começou, está sendo executada
    EM_ANDAMENTO,

    // A tarefa já foi finalizada
    CONCLUIDA
}
