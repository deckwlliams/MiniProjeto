package io.github.derick.MiniProjeto.Repository;

import io.github.derick.MiniProjeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer  > {


        Usuario findByEmail(String email);

        Usuario findByEmailAndSenha(String email, String senha);
    }


