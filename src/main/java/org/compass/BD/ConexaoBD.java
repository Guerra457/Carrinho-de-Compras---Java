package org.compass.BD;

import org.compass.Excecoes.BdExcecao;

import java.sql.*;

public class ConexaoBD {

    //Nome do usuário do postgresql
    private static Connection conn = null;
    private static final String USERNAME = "postgres";

    //Senha do postgresql
    private static final String PASSWORD = "464743";

    //Dados de caminho, porta e nome da base de dados que irá ser feita a conexão
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/DesafioCarrinhoCompras";

    public static Connection conexaoComPostgresql(){
        if (conn == null){
            try {
                conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            }
            catch (SQLException e){
                throw new BdExcecao(e.getMessage());
            }
        }
        return conn;
    }

    public static void fecharConexaoComPostgresql(){
        if (conn != null){
            try {
                conn.close();
            }
            catch (SQLException e){
                throw new BdExcecao(e.getMessage());
            }
        }
    }

    public static void fecharStatement(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new BdExcecao(e.getMessage());
            }
        }
    }

    public static void fecharResultSet(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new BdExcecao(e.getMessage());
            }
        }
    }
}