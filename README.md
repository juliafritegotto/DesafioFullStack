# DesafioFullStack

Esse foi um desafio desenvolvido para gerenciamento de fornecedores e empresas.

<details>
<summary>Backend</summary>

Este é o backend da aplicação, ele permite a criação, listagem, atualização e exclusão de fornecedores e empresas, além de associar fornecedores a empresas e vice-versa.

## Rotas

### Fornecedores

#### Listar Fornecedores

**Endpoint:** GET /api/fornecedores

Esta rota retorna a lista de todos os fornecedores cadastrados.

#### Criar Fornecedor

**Endpoint:** POST /api/fornecedores

Esta rota permite criar um novo fornecedor.

**Corpo da requisição:**

```json
{
  "documento": "987654321",
  "nome": "Fornecedor A",
  "email": "fornecedorA@mail.com",
  "cep": "98765-4321"
}
```

#### Atualizar Fornecedor

**Endpoint:** PUT /api/fornecedores/{fornecedorId}

Esta rota permite atualizar os dados de um fornecedor existente.

**Corpo da requisição:**

```json
{
  "documento": "123456789",
  "nome": "Fornecedor B",
  "email": "fornecedorB@mail.com",
  "cep": "54321-0987"
}
```

#### Excluir Fornecedor

**Endpoint:** DELETE /api/fornecedores/{fornecedorId}

Esta rota permite excluir um fornecedor existente.

### Empresas

#### Listar Empresas

**Endpoint:** GET /api/empresas

Esta rota retorna a lista de todas as empresas cadastradas.

#### Criar Empresa

**Endpoint:** POST /api/empresas

Esta rota permite criar uma nova empresa.

**Corpo da requisição:**

```json
{
  "nomeFantasia": "Empresa XYZ",
  "cnpj": "123456789",
  "cep": "12345-678"
}
```

É importante ressaltar que é possível no momento da criação de uma empresa associar fornecedores a ela, seja ele existentes ou não. Como no exemplo abaixo:

```json
{
  "nomeFantasia": "Empresa XYZ",
  "cnpj": "123456789",
  "cep": "12345-678",
  "fornecedores": [
    {
      "documento": "987654321",
      "nome": "Fornecedor A",
      "email": "fornecedorA@mail.com",
      "cep": "98765-4321"
    },
    {
      "documento": "567890123",
      "nome": "Fornecedor B",
      "email": "fornecedorB@mail.com",
      "cep": "54321-0987"
    }
  ]
}
```

Se o fornecedor já existisse bastava adicionar o id e os dados do mesmo na requisição.

#### Atualizar Empresa

**Endpoint:** PUT /api/empresas/{empresaId}

Esta rota permite atualizar os dados de uma empresa existente.

**Corpo da requisição:**

```json
{
  "nomeFantasia": "Empresa XYZ atualizada",
  "cnpj": "987654321",
  "cep": "54321-0987"
}
```

#### Excluir Empresa

**Endpoint:** DELETE /api/empresas/{empresaId}

Esta rota permite excluir uma empresa existente.

### Associações

#### Associar Fornecedores a uma Empresa

**Endpoint:** POST /api/empresas/{empresaId}/fornecedores

Esta rota permite associar fornecedores a uma empresa existente.

**Corpo da requisição:**

```json
[
  {
    "documento": "39845039845000",
    "nome": "Fornecedor C",
    "email": "fornecedorC@mail.com",
    "cep": "12345-6789"
  },
  {
    "documento": "12345678901234",
    "nome": "Fornecedor D",
    "email": "fornecedorD@mail.com",
    "cep": "98765-4321"
  }
]
```

#### Associar Empresas a um Fornecedor

**Endpoint:** POST /api/fornecedores/{fornecedorId}/empresas

Esta rota permite associar empresas a um fornecedor existente.

**Corpo da requisição:**

```json
[
  {
    "id": 1,
    "nomeFantasia": "Empresa A",
    "cnpj": "123456789",
    "cep": "12345-6789"
  },
  {
    "id": 2,
    "nomeFantasia": "Empresa B",
    "cnpj": "987654321",
    "cep": "54321-0987"
  }
]
```

### Backend - Guia de Instalação e Execução

O backend foi desenvolvido utilizando as seguintes tecnologias:

- Java
- Spring Boot
- Hibernate
- Maven
- MySQL

Para instalar e executar o backend, siga as etapas abaixo:

### Pré-requisitos

Certifique-se de ter as seguintes tecnologias instaladas em sua máquina:

- Java Development Kit (JDK)
- MySQL

### Passo 1: Clonar o repositório

Clone este repositório para o seu ambiente de desenvolvimento local.

### Passo 2: Configurar o banco de dados

Certifique-se de ter um servidor MySQL em execução em sua máquina. Crie um banco de dados vazio para o projeto.

### Passo 3: Configurar as propriedades do banco de dados

Abra o arquivo `application.properties` localizado em `src/main/resources` e atualize as informações de conexão do banco de dados:

```

spring.datasource.url=jdbc:mysql://localhost:3306/{nome_do_banco_de_dados}
spring.datasource.username={seu_usuario}
spring.datasource.password={sua_senha}

```

Substitua `{nome_do_banco_de_dados}` pelo nome do banco de dados que você criou, e `{seu_usuario}` e `{sua_senha}` pelas suas credenciais de acesso ao MySQL.

### Passo 4: Executar o projeto

Abra o projeto em uma IDE de sua escolha e navegue até a classe `DesafioApplication.java` e rode o projeto

### Passo 5: Verificar a execução

Após a inicialização bem-sucedida, o backend estará em execução no endereço `http://localhost:8080`.

Parabéns! Agora você tem o backend configurado e em execução em sua máquina. Você pode testar as diferentes rotas e funcionalidades usando ferramentas como o Postman ou qualquer cliente HTTP, ou o frontend desenvolvido nesse projeto.

</details>
<details>
<summary>Frontend</summary>

Este é o frontend do projeto do DesafioFullStack

## Tecnologias

- React
- React Router DOM
- React Bootstrap
- Axios
- Vite

## Funcionalidades

- Listagem de fornecedores: Visualize todos os fornecedores cadastrados no sistema.
- Filtragem de fornecedores: Realize pesquisas por nome e documento para encontrar fornecedores específicos.
- Criação de fornecedores: Adicione novos fornecedores ao sistema.

- Listagem de empresas: Veja todas as empresas registradas no sistema.
- Filtragem de empresas: Faça buscas por nome e CNPJ para encontrar empresas específicas.
- Criação de empresas: Cadastre novas empresas no sistema.

## Instalação

Certifique-se de ter o Node.js instalado na sua máquina. Em seguida, execute o seguinte comando para instalar as dependências:

```shell
npm install
```

## Executando o projeto

Para iniciar o servidor de desenvolvimento e executar o projeto, use o seguinte comando:

```shell
npm run dev
```

Pronto o aplicativo está pronto para ser utilizado!

</details>
