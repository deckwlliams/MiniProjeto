package io.github.derick.MiniProjeto.Repository;

// Importa a entidade Tarefa (tabela do banco)
import io.github.derick.MiniProjeto.model.Tarefa;

// Importa a entidade Usuario (não é usada diretamente aqui, mas faz parte do contexto)
import io.github.derick.MiniProjeto.model.Usuario;

// Importa a interface base do Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Essa interface é um REPOSITORY
// Um Repository é responsável por acessar o banco de dados
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // =========================================
    // 🔹 BUSCAR TAREFAS PELO ID DO USUÁRIO
    // =========================================

    // Esse método NÃO precisa ser implementado
    // O Spring cria automaticamente a consulta baseada no nome do método
    //
    // Tradução do nome:
    // findBy → buscar por
    // Usuario → objeto Usuario dentro da Tarefa
    // Id → campo id do Usuario
    //
    // Em português:
    // "Buscar todas as tarefas onde o ID do usuário seja igual ao informado"
    List<Tarefa> findByUsuarioId(Long usuarioId);

}
