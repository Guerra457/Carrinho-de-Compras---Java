package org.compass.BD;

import org.compass.Excecoes.BdExcecao;

import java.sql.*;

public class ConexaoBD {

    // Nome do usuário do PostgreSQL
    private static final String USERNAME = "postgres";

    // Senha do PostgreSQL
    private static final String PASSWORD = "464743";

    // Dados de caminho, porta e nome da base de dados
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/DesafioCarrinhoCompras";

    public static Connection conexaoComPostgresql() {
        try {
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new BdExcecao(e.getMessage());
        }
    }

    public static void fecharStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new BdExcecao(e.getMessage());
            }
        }
    }

    public static void fecharResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new BdExcecao(e.getMessage());
            }
        }
    }
}
