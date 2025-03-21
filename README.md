# 📚 Gerenciador de Disciplinas em Java

Este repositório contém a solução de um exercício prático de **Linguagem de Programação II (LPII)**, ministrada pelo professor **Janderson**. A atividade foi desenvolvida em dupla por **Lucas** e **Allan**.

## 📄 Descrição do Exercício

O objetivo do exercício é criar um programa em Java para gerenciar disciplinas, incluindo funcionalidades como cadastro, consulta e exibição de dados.

---

## 🔹 Estrutura do Código

O código foi dividido em nas partes da atividade com as seguintes classes principais para melhor organização e separação de responsabilidades:

- **`Parte01.GerenciadorBasico`**: Contém as funcionalidades principais da Parte 1 do exercício.
- **`Parte02.GerenciadorAvancado`**: Expande a funcionalidade da primeira classe, incluindo recursividade e persistência de dados da Parte 2 do exercício.
- **`Parte03.GerenciadorAvancadoJOP`**: Utilização do JOptionPane e remoção da opção "Exibir disciplinas".
- **`Parte04.GerenciadorAvancadoOO`**: Melhor organização do código; Facilidade de manutenção e extensibilidade; Adoção de boas práticas como encapsulamento e responsabilidade única.

---

## 🔹 Funcionalidades

### 🚀 Parte 1 - Funcionalidades Básicas *(Classe: `Parte01.GerenciadorBasico`)*

#### 🔹 Menu Interativo
O programa apresenta um menu com as seguintes opções:
- **Adicionar disciplina:** Cadastrar uma nova disciplina com nome, notas e frequência.
- **Consultar disciplina:** Buscar uma disciplina específica pelo nome.
- **Exibir disciplinas:** Listar todas as disciplinas cadastradas.
- **Sair:** Encerrar o programa.

#### 🔡 Manipulação de Strings
- Os nomes das disciplinas devem ser formatados com a primeira letra de cada palavra em maiúsculo.

#### 📊 Vetores e Matrizes
- Os dados das disciplinas são armazenados em uma matriz, incluindo:
  - Nome da disciplina
  - Nota 1
  - Nota 2
  - Frequência
  - Status (Aprovado/Reprovado)

#### ⚙️ Funções Auxiliares
Foram implementadas funções auxiliares para modularizar o código:
- `determinarStatus()`: Define o status da disciplina com base nas notas e frequência.
- `formatarNome()`: Formata os nomes das disciplinas conforme solicitado.

---

### 🔥 Parte 2 - Expansão do Programa *(Classe: `Parte02.GerenciadorAvancado`)*

#### 🔁 Recursividade
- Alteração da funcionalidade **"Consultar disciplina"** para realizar a busca dentro da matriz de disciplinas utilizando **recursão** (sem utilizar `for` ou `while`).

#### 💾 Persistência de Dados
- O programa deve verificar se já existe um arquivo **"historico_notas.txt"** contendo dados salvos anteriormente. Caso exista, os dados devem ser carregados para a matriz antes da inserção de novas informações.
  - Cada linha do arquivo representa uma disciplina, com valores separados por um caractere delimitador (exemplo: `'|'`).
- O histórico atualizado deve ser salvo no arquivo ao finalizar a execução do programa.

---

### 🧪 Parte 3 - Versão com JOP *(Classe: `Parte03.GerenciadorAvancadoJOP`)*

#### 🖼️ Interface com JOP (Java Option Pane)
- A interação com o usuário foi modificada para utilizar **caixas de diálogo gráficas (JOptionPane)** ao invés do terminal.
- O menu interativo e as mensagens de entrada/saída são exibidos por meio de janelas pop-up.

#### ❌ Remoção de Funcionalidade
- A funcionalidade de **exibir disciplinas (opção 3)** foi **removida** nesta versão, mantendo apenas:
  - Adição de nova disciplina
  - Consulta recursiva
  - Encerramento do programa com salvamento no arquivo

#### 🔄 Funcionalidades herdadas da Parte 2
- A lógica de persistência e recursividade foi mantida com adaptações para a interface gráfica.

---

### 🧱 Parte 4 - Versão Orientada a Objetos *(Classes: `Disciplina`, `Utils`, `Parte04.GerenciadorAvancadoOO`)*

#### 🧩 Abordagem Modular com POO
- Refatoração completa do projeto para aplicar os princípios da **Programação Orientada a Objetos (POO)**.
- A lógica anteriormente contida em métodos soltos foi distribuída em **classes coesas e reutilizáveis**.

#### 📌 Novas Classes

- **`Parte04.Disciplina`**:
  - Representa uma disciplina com seus atributos (`nome`, `nota1`, `nota2`, `frequencia`, `status`)
  - Possui métodos próprios para cálculo de status e exibição de dados

- **`Parte04.GerenciadorAvancadoOO`**:
  - Classe principal com o menu e controle de fluxo
  - Utiliza uma `List<Disciplina>` para armazenar os dados, substituindo matrizes

---

## 🤝 Colaboradores

- [lucrasn](https://github.com/lucrasn)
- [allangrm](https://github.com/allangrm)

## 🎓 Professor

- **Janderson** – Linguagem de Programação II (LPII)  
