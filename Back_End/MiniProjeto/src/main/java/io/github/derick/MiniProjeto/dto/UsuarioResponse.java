package io.github.derick.MiniProjeto.dto;

// Esse arquivo define um DTO (Data Transfer Object)
// Um DTO é usado para CONTROLAR quais dados serão enviados para o front-end
public record UsuarioResponse(

        // ID do usuário (vem do banco de dados)
        Long id,

        // Nome do usuário
        String nome,

        // Email do usuário
        String email

) {
    // Esse record NÃO tem corpo porque:
    // - Ele já cria automaticamente:
    //   ✔ construtor
    //   ✔ getters
    //   ✔ equals
    //   ✔ hashCode
    //   ✔ toString
}
