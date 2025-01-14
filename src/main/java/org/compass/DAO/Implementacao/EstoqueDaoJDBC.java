package org.compass.DAO.Implementacao;

import org.compass.BD.ConexaoBD;
import org.compass.DAO.EstoqueDAO;
import org.compass.Entidades.Estoque;
import org.compass.Excecoes.BdExcecao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDaoJDBC implements EstoqueDAO {

    @Override
    public void atualizar(String nome, int quantidade, boolean exibirMensagem) {
        String sql = "UPDATE Estoque SET quantidade = ? WHERE LOWER(nome) = LOWER(?)";
        try (Connection conn = ConexaoBD.conexaoComPostgresql();
            PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setInt(1, quantidade);
            pst.setString(2, nome);
            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0 && exibirMensagem) {
                System.out.println("Quantidade atualizada com sucesso!");
            } else if (linhasAfetadas == 0){
                System.out.println("Produto não encontrado ou quantidade inválida!");
            }

            ConexaoBD.fecharStatement(pst);
        }
        catch (SQLException e){
            throw new BdExcecao(e.getMessage());
        }
    }

    @Override
    public Estoque buscarPorNome(String nome) {
        String sql = "SELECT * FROM Estoque WHERE LOWER(nome) = LOWER(?)";
        Estoque estoque = null;

        try(Connection conn = ConexaoBD.conexaoComPostgresql();
            PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                String categoria = rs.getString("categoria");
                double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");
                estoque = new Estoque(quantidade, nome, categoria, valor);
            }
        }
        catch (SQLException e){
            throw new BdExcecao(e.getMessage());
        }
        return estoque;
    }

    @Override
    public void buscarTodos() {
        String sql = "SELECT * FROM Estoque";

        try (Connection conn = ConexaoBD.conexaoComPostgresql();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()){

            while (rs.next()){
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");
                Estoque estoque = new Estoque(quantidade, nome, categoria, valor);
                System.out.printf(estoque.toString());
            }

            ConexaoBD.fecharStatement(pst);
            ConexaoBD.fecharResultSet(rs);
        }
        catch (SQLException e){
            throw new BdExcecao(e.getMessage());
        }
    }
}
