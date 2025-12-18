const API_URL = "http://localhost:8081";

document.addEventListener("DOMContentLoaded", () => {

    /* =======================
       🔒 PROTEÇÃO DE ROTAS
    ======================= */
    const usuario = JSON.parse(sessionStorage.getItem("usuario"));
    const paginaAtual = location.pathname.toLowerCase();

    const paginasPublicas = ["login.html", "cadastro.html"];
    const isPublica = paginasPublicas.some(p => paginaAtual.endsWith(p));

    if (!usuario && !isPublica) {
        window.location.href = "login.html";
        return;
    }

    /* =======================
       🔹 CADASTRO
    ======================= */
    const cadastroForm = document.getElementById("cadastroForm");

    if (cadastroForm) {
        cadastroForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const novoUsuario = {
                nome: document.getElementById("nome").value,
                email: document.getElementById("email").value,
                senha: document.getElementById("senha").value
            };

            const response = await fetch(`${API_URL}/usuario/cadastro`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(novoUsuario)
            });

            if (!response.ok) {
                alert(await response.text());
                return;
            }

            alert("Cadastro realizado com sucesso!");
            window.location.href = "login.html";
        });
    }

    /* =======================
       🔹 LOGIN
    ======================= */
    const loginForm = document.getElementById("loginForm");

    if (loginForm) {
        loginForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const credenciais = {
                email: document.getElementById("email").value,
                senha: document.getElementById("senha").value
            };

            const response = await fetch(`${API_URL}/usuario/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(credenciais)
            });

            if (!response.ok) {
                alert("Email ou senha inválidos");
                return;
            }

            const user = await response.json();
            sessionStorage.setItem("usuario", JSON.stringify(user));
            window.location.href = "tarefas.html";
        });
    }

    /* =======================
       🔹 PERFIL
    ======================= */
    const perfilNome = document.getElementById("nome");
    const perfilEmail = document.getElementById("email");

    if (perfilNome && perfilEmail && usuario) {
        perfilNome.textContent = usuario.nome;
        perfilEmail.textContent = usuario.email;
    }

    /* =======================
       🔹 TAREFAS
    ======================= */
    const tarefaForm = document.getElementById("tarefaForm");
    const lista = document.getElementById("listaTarefas");

    if (tarefaForm && lista && usuario) {

        tarefaForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const tarefa = {
                titulo: document.getElementById("titulo").value,
                prioridade: document.getElementById("prioridade").value
            };

            await fetch(`${API_URL}/tarefas/usuario/${usuario.id}`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(tarefa)
            });

            tarefaForm.reset();
            carregarTarefas();
        });

        carregarTarefas();
    }

    async function carregarTarefas() {
        const res = await fetch(`${API_URL}/tarefas/usuario/${usuario.id}`);
        const tarefas = await res.json();

        lista.innerHTML = "";

        tarefas.forEach(t => {
            const li = document.createElement("li");
            li.innerHTML = `
                <span>
                    ${t.titulo}
                    <span class="badge ${t.prioridade}">${t.prioridade}</span>
                    - ${t.status}
                </span>
                <div>
                    <button onclick="concluir(${t.id})">✔</button>
                    <button onclick="excluir(${t.id})">❌</button>
                </div>
            `;
            lista.appendChild(li);
        });
    }
});

/* =======================
   🔹 FUNÇÕES GLOBAIS
======================= */
async function concluir(id) {
    await fetch(`http://localhost:8081/tarefas/${id}/concluir`, { method: "PUT" });
    location.reload();
}

async function excluir(id) {
    await fetch(`http://localhost:8081/tarefas/${id}`, { method: "DELETE" });
    location.reload();
}

function logout() {
    sessionStorage.clear();
    window.location.href = "login.html";
}
