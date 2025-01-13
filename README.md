# Carrinho-de-Compras---Java

## Descrição do Projeto
Este projeto é uma aplicação de Carrinho de Compras em Java

O sistema utiliza JDBC para comunicação com um banco de dados PostgreSQL.

---

## Pré-requisitos

1. Java 17
2. Banco de Dados PostgreSQL
3. Maven para as dependências 

---

## Estrutura das Tabelas

### Tabela `Estoque`
| Coluna       | Tipo       | Restrições       |
|--------------|------------|------------------|
| id           | SERIAL     | PRIMARY KEY      |
| nome         | VARCHAR    | NOT NULL         |
| categoria    | VARCHAR    | NOT NULL         |
| valor        | NUMERIC    | NOT NULL         |
| quantidade   | INT        | NOT NULL         |

### Tabela `Carrinho`
| Coluna       | Tipo       | Restrições       |
|--------------|------------|------------------|
| id           | SERIAL     | PRIMARY KEY      |
| nome         | VARCHAR    | NOT NULL         |
| categoria    | VARCHAR    | NOT NULL         |
| valor        | NUMERIC    | NOT NULL         |
| quantidade   | INT        | NOT NULL         |
| valor_total  | NUMERIC    | NOT NULL         |

---

## Configuração do Banco de Dados

1.  Nome do banco: DesafioCarrinhoCompras
2.  Usuário: postgres
3.  Senha: 464743

4. Crie as tabelas no banco de dados utilizando os comandos SQL:
   ```sql
   CREATE TABLE Estoque (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100) NOT NULL,
       categoria VARCHAR(50) NOT NULL,
       valor NUMERIC(10, 2) NOT NULL,
       quantidade INT NOT NULL
   );

   CREATE TABLE Carrinho (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100) NOT NULL,
       categoria VARCHAR(50) NOT NULL,
       valor NUMERIC(10, 2) NOT NULL,
       quantidade INT NOT NULL,
       valor_total NUMERIC(10, 2)
   );
   ```
5.1  Inserindo registros na tabela Estoque
```sql
INSERT INTO Estoque (nome, categoria, valor, quantidade) VALUES
('Notebook Dell', 'Eletrônicos', 4500.00, 10),
('Smartphone Samsung Galaxy', 'Eletrônicos', 2500.00, 15),
('Fone de Ouvido JBL', 'Acessórios', 200.00, 30),
('Cadeira Gamer', 'Móveis', 1200.00, 5),
('Mouse Logitech', 'Acessórios', 150.00, 50);
```

5.2  Inserindo registros na tabela Carrinho
```sql
INSERT INTO Carrinho (nome, categoria, valor, quantidade) VALUES
('Notebook Dell', 'Eletrônicos', 4500.00, 1),
('Fone de Ouvido JBL', 'Acessórios', 200.00, 2),
('Cadeira Gamer', 'Móveis', 1200.00, 1);
```

---

## Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/Guerra457/Carrinho-de-Compras---Java.git
   ```
2. Adicione a dependência do Postegre no arquivo pom.xml
   ```xml
   <dependencies>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.4</version>
        </dependency>
    </dependencies>
   ```

4. Execute o projeto em sua IDE:
   

5. Siga as instruções exibidas no console para navegar no sistema.

---

## Contato
- Desenvolvedor: Matheus Guerra (https://github.com/Guerra457)
- Email: matheus.guerra.pb@compasso.com.br
