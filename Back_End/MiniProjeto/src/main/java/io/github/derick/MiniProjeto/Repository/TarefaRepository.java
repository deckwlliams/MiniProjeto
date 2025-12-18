package io.github.derick.MiniProjeto.Repository;

import io.github.derick.MiniProjeto.model.Tarefa;
import io.github.derick.MiniProjeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuarioId(Long usuarioId);

}

