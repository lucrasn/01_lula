# 📚 Gerenciador de Disciplinas em Java

Este repositório contém a solução de um exercício prático de **Linguagem de Programação II (LPII)**, ministrada pelo professor **Janderson**. A atividade foi desenvolvida em dupla por **Lucas** e **Allan**.

## 📄 Descrição do Exercício

O objetivo do exercício é criar um programa em Java para gerenciar disciplinas, incluindo funcionalidades como cadastro, consulta e exibição de dados.

---

## 🔹 Estrutura do Código

O código foi dividido em duas classes para melhor organização e separação de responsabilidades:

- **`GerenciadorBasico`**: Contém as funcionalidades principais da Parte 1 do exercício.
- **`GerenciadorAvancado`**: Expande a funcionalidade da primeira classe, incluindo recursividade e persistência de dados da Parte 2 do exercício.

---

## 🔹 Funcionalidades

### 🚀 Parte 1 - Funcionalidades Básicas *(Classe: `GerenciadorBasico`)*

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

### 🔥 Parte 2 - Expansão do Programa *(Classe: `GerenciadorAvancado`)*

#### 🔁 Recursividade
- Alteração da funcionalidade **"Consultar disciplina"** para realizar a busca dentro da matriz de disciplinas utilizando **recursão** (sem utilizar `for` ou `while`).

#### 💾 Persistência de Dados
- O programa deve verificar se já existe um arquivo **"historico_notas.txt"** contendo dados salvos anteriormente. Caso exista, os dados devem ser carregados para a matriz antes da inserção de novas informações.
  - Cada linha do arquivo representa uma disciplina, com valores separados por um caractere delimitador (exemplo: `'|'`).
- O histórico atualizado deve ser salvo no arquivo ao finalizar a execução do programa.

---

## 🤝 Colaboradores

- [lucrasn](https://github.com/lucrasn)
- [allangrm](https://github.com/allangrm)

## 🎓 Professor

- **Janderson** – Linguagem de Programação II (LPII)  
