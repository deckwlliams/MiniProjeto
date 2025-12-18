package io.github.derick.MiniProjeto.controller;

import io.github.derick.MiniProjeto.Repository.UsuarioRepository;
import io.github.derick.MiniProjeto.dto.UsuarioResponse;
import io.github.derick.MiniProjeto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /* =========================
       🔹 LOGIN
    ========================= */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {

        Usuario u = usuarioRepository
                .findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

        if (u == null) {
            return ResponseEntity.status(401).body("Login inválido");
        }

        // ✅ RETORNA APENAS O NECESSÁRIO
        return ResponseEntity.ok(
                new UsuarioResponse(
                        (long) u.getId(),
                        u.getNome(),
                        u.getEmail()
                )
        );
    }

    /* =========================
       🔹 CADASTRO
    ========================= */
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {

        Usuario existente = usuarioRepository.findByEmail(usuario.getEmail());

        if (existente != null) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        Usuario salvo = usuarioRepository.save(usuario);

        return ResponseEntity.ok(
                new UsuarioResponse(
                        (long) salvo.getId(),
                        salvo.getNome(),
                        salvo.getEmail()
                )
        );
    }
}
