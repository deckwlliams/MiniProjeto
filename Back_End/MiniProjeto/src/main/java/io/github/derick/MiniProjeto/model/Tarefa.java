package io.github.derick.MiniProjeto.model;

// Importações do JPA (biblioteca que liga Java com o banco de dados)
import jakarta.persistence.*;

// Importação para trabalhar com data e hora
import java.time.LocalDateTime;

// Diz ao JPA que essa classe representa uma TABELA no banco de dados
@Entity

// Define o nome da tabela no banco como "tarefa"
@Table(name = "tarefa")
public class Tarefa {

    // =========================
    // 🔹 IDENTIFICADOR (ID)
    // =========================

    // Diz que esse campo é a chave primária da tabela
    @Id

    // Diz que o ID será gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // 🔹 DADOS DA TAREFA
    // =========================

    // Título da tarefa (ex: "Estudar Java")
    private String titulo;

    // Descrição mais detalhada da tarefa
    private String descricao;

    // =========================
    // 🔹 PRIORIDADE DA TAREFA
    // =========================

    // Diz que o enum será salvo como TEXTO no banco (ex: "ALTA")
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    // =========================
    // 🔹 STATUS DA TAREFA
    // =========================

    // Diz que o enum será salvo como TEXTO no banco (ex: "PENDENTE")
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    // =========================
    // 🔹 DATAS IMPORTANTES
    // =========================

    // Data e hora em que a tarefa foi criada
    private LocalDateTime dataCriacao;

    // Data e hora em que a tarefa foi concluída
    private LocalDateTime dataConclusao;

    // =========================
    // 🔹 RELAÇÃO COM USUÁRIO
    // =========================

    // Muitas tarefas podem pertencer a UM usuário
    @ManyToOne

    // Cria a coluna "usuario_id" no banco
    // Essa coluna guarda o ID do usuário dono da tarefa
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // =========================
    // 🔹 CONSTRUTOR
    // =========================

    // Construtor padrão (executa quando a tarefa é criada)
    public Tarefa() {

        // Define automaticamente a data de criação como agora
        this.dataCriacao = LocalDateTime.now();

        // Define o status inicial da tarefa como PENDENTE
        this.status = StatusTarefa.PENDENTE;
    }

    // =========================
    // 🔹 GETTERS E SETTERS
    // =========================

    // Retorna o ID da tarefa
    public Long getId() {
        return id;
    }

    // Define o ID da tarefa
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o título da tarefa
    public String getTitulo() {
        return titulo;
    }

    // Define o título da tarefa
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Retorna a descrição da tarefa
    public String getDescricao() {
        return descricao;
    }

    // Define a descrição da tarefa
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a prioridade da tarefa
    public Prioridade getPrioridade() {
        return prioridade;
    }

    // Define a prioridade da tarefa
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    // Retorna o status da tarefa
    public StatusTarefa getStatus() {
        return status;
    }

    // Define o status da tarefa
    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    // Retorna a data de criação
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    // Define manualmente a data de criação (se necessário)
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Retorna a data de conclusão
    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    // Define a data de conclusão
    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    // Retorna o usuário dono da tarefa
    public Usuario getUsuario() {
        return usuario;
    }

    // Define o usuário dono da tarefa
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
