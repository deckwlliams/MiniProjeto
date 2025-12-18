package io.github.derick.MiniProjeto.controller;

import io.github.derick.MiniProjeto.Repository.TarefaRepository;
import io.github.derick.MiniProjeto.Repository.UsuarioRepository;
import io.github.derick.MiniProjeto.model.StatusTarefa;
import io.github.derick.MiniProjeto.model.Tarefa;
import io.github.derick.MiniProjeto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 🔹 CRIAR TAREFA
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> criarTarefa(
            @PathVariable Long usuarioId,
            @RequestBody Tarefa tarefa) {

        Usuario usuario = usuarioRepository.findById(Math.toIntExact(usuarioId)).orElse(null);

        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        tarefa.setUsuario(usuario);
        Tarefa salva = tarefaRepository.save(tarefa);

        return ResponseEntity.ok(salva);
    }

    // 🔹 LISTAR TAREFAS DO USUÁRIO
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Tarefa>> listarPorUsuario(@PathVariable Long usuarioId) {

        List<Tarefa> tarefas = tarefaRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(tarefas);
    }

    // 🔹 CONCLUIR TAREFA
    @PutMapping("/{id}/concluir")
    public ResponseEntity<?> concluirTarefa(@PathVariable Long id) {

        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);

        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        }

        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        return ResponseEntity.ok(tarefaRepository.save(tarefa));
    }

    // 🔹 EXCLUIR TAREFA
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        if (!tarefaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        tarefaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(Math.toIntExact(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
