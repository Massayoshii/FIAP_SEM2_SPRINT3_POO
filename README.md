# MOTIVA — Sprint 3

Sistema desenvolvido para o projeto MOTIVA, com foco no monitoramento de trechos de rodovia, análise do nível de vegetação e geração de relatórios de prioridade para intervenção.

Nesta Sprint, o projeto foi evoluído para utilizar **persistência de dados em banco Oracle através de JDBC puro**.

---

## 📌 Objetivo

O sistema permite cadastrar e gerenciar trechos de rodovia, considerando:

* Quilômetro inicial e final;
* Nível de vegetação;
* Tipo de clima;
* Classificação de prioridade para intervenção.

Com base no nível de vegetação, o sistema identifica a necessidade de intervenção:

| Nível de vegetação | Prioridade      | Intervenção       |
| ------------------ | --------------- | ----------------- |
| ≥ 25 cm            | Alta            | Roçada mecanizada |
| ≥ 12 cm e < 25 cm  | Média           | Pulverização      |
| < 12 cm            | Sem intervenção | Nenhuma           |

Os resultados da análise são armazenados no banco de dados para permitir consultas ao histórico de relatórios.

---

## 🛠️ Tecnologias utilizadas

* Java
* JDBC
* Oracle Database
* Oracle JDBC Driver (`ojdbc17.jar`)
* IntelliJ IDEA

---

## 📂 Estrutura do projeto

```text
src/
├── db/
│   └── ConexaoBD.java
│
├── dao/
│   ├── TrechoRodoviaDAO.java
│   └── RelatorioPrioridadeDAO.java
│
├── model/
│   ├── TrechoRodovia.java
│   ├── RelatorioPrioridade.java
│   ├── IntervencaoOperacional.java
│   ├── RocadaMecanizada.java
│   ├── Pulverizacao.java
│   └── MonitoravelViaIoT.java
│
├── service/
│   └── GeradorRelatorio.java
│
└── main/
    └── Main.java

sql/
├── seu-script-criacao.sql
└── seu-script-dados.sql

lib/
└── ojdbc17.jar
```

---

## 🗄️ Banco de dados

O projeto utiliza duas tabelas principais.

### TRECHO_RODOVIA

Armazena os dados dos trechos monitorados.

Principais campos:

* `ID`
* `QUILOMETRO_INICIAL`
* `QUILOMETRO_FINAL`
* `NIVEL_VEGETACAO_CM`
* `TIPO_CLIMA`

### RELATORIO_PRIORIDADE

Armazena os resultados das análises realizadas.

Principais campos:

* `ID`
* `QT_PRIORIDADE_ALTA`
* `QT_PRIORIDADE_MEDIA`
* `QT_SEM_INTERVENCAO`
* `RESUMO`
* `DATA_GERACAO`

---

## 🔌 Conexão com Oracle

A conexão com o banco é centralizada na classe:

```text
db.ConexaoBD
```

A classe utiliza o padrão Singleton para manter uma única conexão durante a execução da aplicação.

As informações de conexão utilizadas são:

```text
Host: oracle.fiap.com.br
Porta: 1521
SID: ORCL
```

O usuário e a senha devem ser configurados localmente na classe `ConexaoBD`.

> Não publique usuário e senha do Oracle no GitHub.

---

## 💾 Persistência com JDBC

A comunicação com o banco é realizada utilizando JDBC puro.

Os DAOs são responsáveis pelas operações de persistência.

### TrechoRodoviaDAO

Implementa:

* Inserção;
* Busca por ID;
* Listagem;
* Atualização;
* Exclusão.

### RelatorioPrioridadeDAO

Implementa:

* Inserção;
* Busca por ID;
* Listagem;
* Atualização;
* Exclusão.

As operações utilizam `PreparedStatement` e `ResultSet` para comunicação com o banco.

---

## 📊 Geração de relatórios

A classe:

```text
service.GeradorRelatorio
```

é responsável por analisar os trechos e determinar suas prioridades.

O fluxo de análise é:

```text
TrechoRodovia
      ↓
Análise do nível de vegetação
      ↓
Classificação da prioridade
      ↓
Definição da intervenção
      ↓
RelatorioPrioridade
      ↓
Persistência no Oracle
```

As intervenções disponíveis são:

### Roçada mecanizada

Utilizada quando o nível de vegetação é igual ou superior a 25 cm.

### Pulverização

Utilizada quando o nível de vegetação está entre 12 cm e 24,99 cm.

### Sem intervenção

Quando o nível de vegetação é inferior a 12 cm.

---

## 🧪 Testes realizados

A classe `Main` executa um fluxo completo de testes da aplicação.

### Conexão

Verifica se a conexão com o Oracle foi estabelecida corretamente.

### Create

Insere novos trechos de rodovia no banco.

### Read

Realiza:

* Busca de trecho por ID;
* Listagem de todos os trechos;
* Busca de relatórios;
* Listagem do histórico de relatórios.

### Update

Atualiza um trecho de rodovia e confirma a alteração realizando uma nova consulta ao banco.

Também testa a atualização de um relatório.

### Delete

Remove um trecho e realiza uma nova consulta para confirmar a exclusão.

### Relatório

Os trechos são recuperados do banco antes da geração do relatório.

O relatório gerado também é persistido no Oracle.

---

## ▶️ Como executar

### 1. Configurar o Oracle

Utilize o banco Oracle disponibilizado pela FIAP.

Configure suas credenciais na classe:

```text
src/db/ConexaoBD.java
```

---

### 2. Adicionar o driver JDBC

Certifique-se de que o arquivo:

```text
ojdbc17.jar
```

está disponível na pasta:

```text
lib/
```

e configurado como dependência do projeto.

---

### 3. Criar as tabelas

Execute o arquivo:

```text
sql/seu-script-criacao.sql
```

Esse script cria as tabelas necessárias para a aplicação.

---

### 4. Inserir dados iniciais

Execute:

```text
sql/seu-script-dados.sql
```

Esse script insere dados iniciais na tabela `TRECHO_RODOVIA`.

---

### 5. Executar a aplicação

Execute a classe:

```text
src/main/Main.java
```

O programa realizará o fluxo de testes e exibirá os resultados no console.

---

## 🔐 Segurança

As credenciais utilizadas para acesso ao banco não devem ser versionadas no Git.

Antes de realizar o commit do projeto, verifique se não existem:

* Usuário do Oracle;
* Senha;
* Tokens;
* Outras credenciais pessoais.

---

## 📚 Conceitos praticados

Este projeto utiliza conceitos de:

* Programação Orientada a Objetos;
* Interfaces;
* Classes abstratas;
* Herança;
* Polimorfismo;
* Encapsulamento;
* JDBC;
* DAO;
* Singleton;
* CRUD;
* SQL;
* Oracle Database;
* `PreparedStatement`;
* `ResultSet`;
* Tratamento de `SQLException`;
* Persistência de dados.

---

## 👨‍💻 Projeto acadêmico

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos — Sprint 3.

**MOTIVA — Sistema de monitoramento e priorização de intervenções em rodovias.**
