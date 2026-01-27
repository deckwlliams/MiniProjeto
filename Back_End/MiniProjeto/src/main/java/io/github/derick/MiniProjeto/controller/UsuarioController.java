package io.github.derick.MiniProjeto.controller;

// Importa o repositório que acessa a tabela de usuários no banco
import io.github.derick.MiniProjeto.Repository.UsuarioRepository;

// Importa o DTO que será usado para devolver apenas dados seguros do usuário
import io.github.derick.MiniProjeto.dto.UsuarioResponse;

// Importa o modelo Usuario (representa a tabela de usuários)
import io.github.derick.MiniProjeto.model.Usuario;

// Importações do Spring para criar a API
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Permite que o front-end rodando nesse endereço acesse essa API
@CrossOrigin(origins = "http://127.0.0.1:5500")

// Diz ao Spring que essa classe é um Controller REST
@RestController

// Define a rota base: tudo aqui começa com /usuario
@RequestMapping("/usuario")
public class UsuarioController {

    // O Spring cria automaticamente esse objeto para acessar o banco de usuários
    @Autowired
    private UsuarioRepository usuarioRepository;

    /* =========================
       🔹 LOGIN DO USUÁRIO
       ========================= */

    // Essa rota responde a POST /usuario/login
    @PostMapping("/login")
    public ResponseEntity<?> login(

            // Recebe os dados enviados pelo front (email e senha)
            @RequestBody Usuario usuario) {

        // Busca no banco um usuário com o email e senha informados
        Usuario u = usuarioRepository
                .findByEmailAndSenha(
                        usuario.getEmail(),
                        usuario.getSenha()
                );

        // Se não encontrar ninguém com esses dados, login inválido
        if (u == null) {
            return ResponseEntity
                    .status(401) // código HTTP de não autorizado
                    .body("Login inválido");
        }

        // Se encontrar o usuário, retorna apenas os dados necessários
        // (NÃO retorna senha por segurança)
        return ResponseEntity.ok(
                new UsuarioResponse(
                        (long) u.getId(), // ID do usuário
                        u.getNome(),      // Nome do usuário
                        u.getEmail()      // Email do usuário
                )
        );
    }

    /* =========================
       🔹 CADASTRO DE USUÁRIO
       ========================= */

    // Essa rota responde a POST /usuario/cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(

            // Recebe os dados do novo usuário enviados pelo front
            @RequestBody Usuario usuario) {

        // Verifica se já existe um usuário com esse email
        Usuario existente =
                usuarioRepository.findByEmail(usuario.getEmail());

        // Se já existir, não permite o cadastro
        if (existente != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Email já cadastrado");
        }

        // Se não existir, salva o usuário no banco
        Usuario salvo = usuarioRepository.save(usuario);

        // Retorna os dados do usuário cadastrado (sem senha)
        return ResponseEntity.ok(
                new UsuarioResponse(
                        (long) salvo.getId(), // ID gerado pelo banco
                        salvo.getNome(),      // Nome
                        salvo.getEmail()      // Email
                )
        );
    }
}
