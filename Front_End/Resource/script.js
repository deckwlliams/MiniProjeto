const API_URL = "http://localhost:8081/usuario";

// 🔒 PROTEÇÃO DE ROTAS
const usuario = JSON.parse(sessionStorage.getItem("usuario"));

const paginasPublicas = ["login.html", "cadastro.html"];

if (!usuario && !paginasPublicas.some(p => location.pathname.toLowerCase().includes(p))) {
    window.location.href = "login.html";
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

        const response = await fetch(`${API_URL}/cadastro`, {
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

        const response = await fetch(`${API_URL}/login`, {
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
   🔹 TAREFAS
======================= */
const tarefaForm = document.getElementById("tarefaForm");

if (tarefaForm && usuario) {
    tarefaForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const tarefa = {
            titulo: document.getElementById("titulo").value,
            prioridade: document.getElementById("prioridade").value
        };

        await fetch(`http://localhost:8081/tarefas/usuario/${usuario.id}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(tarefa)
        });

        carregarTarefas();
        tarefaForm.reset();
    });
}
async function carregarTarefas() {
    if (!usuario) return;

    const res = await fetch(`http://localhost:8081/tarefas/usuario/${usuario.id}`);

    const data = await res.json();

    // 🛡️ GARANTIA DE ARRAY
    const tarefas = Array.isArray(data) ? data : data.content || [];

    const lista = document.getElementById("listaTarefas");
    lista.innerHTML = "";

    tarefas.forEach(t => {
        const li = document.createElement("li");
        li.innerHTML = `
            ${t.titulo} - ${t.prioridade} - ${t.status}
            <button onclick="concluir(${t.id})">✔</button>
            <button onclick="excluir(${t.id})">❌</button>
        `;
        lista.appendChild(li);
    });
}


async function concluir(id) {
    await fetch(`http://localhost:8081/tarefas/${id}/concluir`, { method: "PUT" });
    carregarTarefas();
}

async function excluir(id) {
    await fetch(`http://localhost:8081/tarefas/${id}`, { method: "DELETE" });
    carregarTarefas();
}

/* =======================
   🔹 LOGOUT
======================= */
function logout() {
    sessionStorage.clear();
    window.location.href = "login.html";
}

/* =======================
   🔄 AUTO LOAD
======================= */
if (document.getElementById("listaTarefas")) {
    carregarTarefas();
}
