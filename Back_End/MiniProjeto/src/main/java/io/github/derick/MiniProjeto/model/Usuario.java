package io.github.derick.MiniProjeto.model;

// Importações do JPA (biblioteca que liga Java ao banco de dados)
import jakarta.persistence.*;

// Diz ao Spring/JPA que essa classe representa uma TABELA no banco
@Entity

// Define o nome da tabela no banco como "usuario"
@Table(name = "usuario")
public class Usuario {

    // =========================
    // 🔹 ID DO USUÁRIO
    // =========================

    // Indica que esse campo é a chave primária da tabela
    @Id

    // Diz que o ID será gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // =========================
    // 🔹 DADOS DO USUÁRIO
    // =========================

    // Nome do usuário
    String nome;

    // Email do usuário (usado para login)
    String email;

    // Senha do usuário
    // ⚠️ Importante: aqui está em texto puro (apenas para estudo)
    String senha;

    // =========================
    // 🔹 CONSTRUTOR
    // =========================

    // Construtor padrão exigido pelo JPA
    public Usuario() {}

    // =========================
    // 🔹 GETTERS E SETTERS
    // =========================

    // Retorna o ID do usuário
    public int getId() {
        return id;
    }

    // Define o ID do usuário
    public void setId(int id) {
        this.id = id;
    }

    // Retorna o nome do usuário
    public String getNome() {
        return nome;
    }

    // Define o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o email do usuário
    public String getEmail() {
        return email;
    }

    // Define o email do usuário
    public void setEmail(String email) {
        this.email = email;
    }

    // Retorna a senha do usuário
    public String getSenha() {
        return senha;
    }

    // Define a senha do usuário
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // =========================
    // 🔹 toString (DEPURAÇÃO)
    // =========================

    // Converte o objeto Usuario em texto
    // Usado apenas para debug (log, console, testes)
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
