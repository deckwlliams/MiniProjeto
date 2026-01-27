package io.github.derick.MiniProjeto.controller;

// Importa os repositórios que fazem a comunicação com o banco de dados
import io.github.derick.MiniProjeto.Repository.TarefaRepository;
import io.github.derick.MiniProjeto.Repository.UsuarioRepository;

// Importa os modelos (classes que representam tabelas do banco)
import io.github.derick.MiniProjeto.model.StatusTarefa;
import io.github.derick.MiniProjeto.model.Tarefa;
import io.github.derick.MiniProjeto.model.Usuario;

// Importações do Spring para criar APIs REST
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Permite que o front-end rodando nesse endereço acesse a API
@CrossOrigin(origins = "http://127.0.0.1:5500")

// Diz ao Spring que essa classe é um controller REST (API)
@RestController

// Define a rota base: tudo aqui começa com /tarefas
@RequestMapping("/tarefas")
public class TarefaController {

    // O Spring cria automaticamente esse objeto para acessar tarefas no banco
    @Autowired
    private TarefaRepository tarefaRepository;

    // O Spring cria automaticamente esse objeto para acessar usuários no banco
    @Autowired
    private UsuarioRepository usuarioRepository;

    // ============================
    // 🔹 CRIAR UMA NOVA TAREFA
    // ============================

    // Essa rota responde a POST /tarefas/usuario/{usuarioId}
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> criarTarefa(

            // Pega o ID do usuário que vem pela URL
            @PathVariable Long usuarioId,

            // Pega os dados da tarefa enviados em JSON pelo front-end
            @RequestBody Tarefa tarefa) {

        // Busca o usuário no banco pelo ID
        Usuario usuario = usuarioRepository
                .findById(Math.toIntExact(usuarioId))
                .orElse(null);

        // Se o usuário não existir, retorna erro
        if (usuario == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Usuário não encontrado");
        }

        // Associa a tarefa ao usuário encontrado
        tarefa.setUsuario(usuario);

        // Salva a tarefa no banco
        Tarefa salva = tarefaRepository.save(tarefa);

        // Retorna a tarefa salva como resposta
        return ResponseEntity.ok(salva);
    }

    // =====================================
    // 🔹 LISTAR TODAS AS TAREFAS DO USUÁRIO
    // =====================================

    // Essa rota responde a GET /tarefas/usuario/{usuarioId}
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Tarefa>> listarPorUsuario(

            // Pega o ID do usuário da URL
            @PathVariable Long usuarioId) {

        // Busca no banco todas as tarefas desse usuário
        List<Tarefa> tarefas = tarefaRepository.findByUsuarioId(usuarioId);

        // Retorna a lista de tarefas
        return ResponseEntity.ok(tarefas);
    }

    // ============================
    // 🔹 CONCLUIR UMA TAREFA
    // ============================

    // Essa rota responde a PUT /tarefas/{id}/concluir
    @PutMapping("/{id}/concluir")
    public ResponseEntity<?> concluirTarefa(

            // Pega o ID da tarefa da URL
            @PathVariable Long id) {

        // Busca a tarefa no banco
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);

        // Se a tarefa não existir, retorna 404
        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        }

        // Muda o status da tarefa para CONCLUIDA
        tarefa.setStatus(StatusTarefa.CONCLUIDA);

        // Salva a alteração no banco e retorna a tarefa atualizada
        return ResponseEntity.ok(tarefaRepository.save(tarefa));
    }

    // ============================
    // 🔹 EXCLUIR UMA TAREFA
    // ============================

    // Essa rota responde a DELETE /tarefas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(

            // Pega o ID da tarefa da URL
            @PathVariable Long id) {

        // Verifica se a tarefa existe no banco
        if (!tarefaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Remove a tarefa do banco
        tarefaRepository.deleteById(id);

        // Retorna sucesso sem conteúdo
        return ResponseEntity.ok().build();
    }

    // =================================
    // 🔹 BUSCAR USUÁRIO PELO ID
    // =================================

    // Essa rota responde a GET /tarefas/{id}
    // (OBS: essa rota busca USUÁRIO, não tarefa)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(

            // Pega o ID do usuário da URL
            @PathVariable Long id) {

        // Busca o usuário no banco e retorna se existir
        return usuarioRepository.findById(Math.toIntExact(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
