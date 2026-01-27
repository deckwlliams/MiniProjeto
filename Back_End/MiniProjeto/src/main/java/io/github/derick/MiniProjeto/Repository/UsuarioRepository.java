package io.github.derick.MiniProjeto.Repository;

// Importa a entidade Usuario (tabela do banco)
import io.github.derick.MiniProjeto.model.Usuario;

// Importa a interface base do Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Importa a anotação que marca isso como um Repository
import org.springframework.stereotype.Repository;

// ⚠️ Esse import está DUPLICADO no seu código original
// Não causa erro, mas pode ser removido
import org.springframework.data.jpa.repository.JpaRepository;

// Marca essa interface como um Repository do Spring
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // =========================================
    // 🔹 BUSCAR USUÁRIO PELO EMAIL
    // =========================================

    // O Spring cria automaticamente a consulta baseada no nome do método
    //
    // Tradução:
    // findBy → buscar por
    // Email → campo email do usuário
    //
    // SQL equivalente:
    // SELECT * FROM usuario WHERE email = ?
    Usuario findByEmail(String email);

    // =========================================
    // 🔹 BUSCAR USUÁRIO POR EMAIL E SENHA (LOGIN)
    // =========================================

    // Usado no login
    //
    // Tradução:
    // findBy → buscar por
    // EmailAndSenha → email E senha ao mesmo tempo
    //
    // SQL equivalente:
    // SELECT * FROM usuario WHERE email = ? AND senha = ?
    Usuario findByEmailAndSenha(String email, String senha);
}
