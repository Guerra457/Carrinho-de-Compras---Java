package org.compass.DAO.Implementacao;

import org.compass.BD.ConexaoBD;
import org.compass.DAO.CarrinhoDAO;
import org.compass.Entidades.Carrinho;
import org.compass.Entidades.Estoque;
import org.compass.Excecoes.BdExcecao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarrinhoDaoJDBC implements CarrinhoDAO {

    @Override
    public void inserir(String nome, int quantidade) {
        EstoqueDaoJDBC estoqueDaoJDBC = new EstoqueDaoJDBC();
        Estoque estoque = estoqueDaoJDBC.buscarPorNome(nome);

        if (estoque != null){
            if (estoque.getQuantidade() >= quantidade) {
                String sql = "INSERT INTO Carrinho (nome, quantidade) VALUES (?, ?)";
                try (Connection conn = ConexaoBD.conexaoComPostgresql();
                    PreparedStatement pst = conn.prepareStatement(sql)) {

                    pst.setString(1, nome);
                    pst.setInt(2, quantidade);
                    pst.executeUpdate();

                    ConexaoBD.fecharStatement(pst);
                }
                catch (SQLException e){
                    throw new BdExcecao(e.getMessage());
                }
            } else {
                System.out.println("Quantidade insuficiente!");
            }
        }else {
            System.out.println("Produto Não encontrado");
        }

    }

    @Override
    public void atualizar(String nome, int quantidade) {
        String sql = "UPDATE Carrinho SET quantidade = ? WHERE nome = ?";

        try (Connection conn = ConexaoBD.conexaoComPostgresql();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nome);
            pst.setInt(2, quantidade);

            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Quantidade do produto atualizada com sucesso!");
            } else {
                System.out.println("Produto não encontrado!");
            }

        } catch (SQLException e) {
            throw new BdExcecao(e.getMessage());
        }
    }


    @Override
    public void deletarPorNome(String nome) {
        String sql = "DELETE FROM Carrinho WHERE nome = ?";

        try (Connection conn = ConexaoBD.conexaoComPostgresql();
            PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setString(1, nome);
            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto removido com sucesso!");
            }else {
                System.out.println("Produto não encontrado!");
            }
        }
        catch (SQLException e) {
            throw new BdExcecao(e.getMessage());
        }
    }

    @Override
    public Carrinho buscarPorNome(String nome) {
        return null;
    }

    @Override
    public void buscarTodos() {
        String sql = "SELECT * FROM Carrinho";

        try (Connection conn = ConexaoBD.conexaoComPostgresql();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()){

            while (rs.next()){
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");
                double valorTotal = quantidade * valor;
                Carrinho carrinho = new Carrinho(quantidade, nome, categoria, valor);
                System.out.printf(carrinho.toString());
            }

            ConexaoBD.fecharStatement(pst);
            ConexaoBD.fecharResultSet(rs);
        }
        catch (SQLException e){
            throw new BdExcecao(e.getMessage());
        }
    }

    public double calcularValorTotal() {
        String sql = "SELECT SUM(quantidade * valor) AS valor_total FROM Carrinho";
        double valorTotal = 0.0;

        try (Connection conn = ConexaoBD.conexaoComPostgresql();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                valorTotal = rs.getDouble("valor_total");
            }

            ConexaoBD.fecharStatement(pst);
            ConexaoBD.fecharResultSet(rs);
        } catch (SQLException e) {
            throw new BdExcecao(e.getMessage());
        }

        return valorTotal;
    }

}
