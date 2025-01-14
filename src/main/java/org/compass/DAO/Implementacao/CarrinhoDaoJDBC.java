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
                String sql = "INSERT INTO Carrinho (nome, categoria, valor, quantidade, valor_total) VALUES (?, ?, ?, ?, ?)";
                try (Connection conn = ConexaoBD.conexaoComPostgresql();
                    PreparedStatement pst = conn.prepareStatement(sql)) {

                    double valor = estoque.getValor();
                    double valorTotal = quantidade * valor;

                    pst.setString(1, estoque.getNome());
                    pst.setString(2, estoque.getCategoria());
                    pst.setDouble(3, valor);
                    pst.setInt(4, quantidade);
                    pst.setDouble(5, valorTotal);

                    pst.executeUpdate();

                    int novaQuantidadeEstoque = estoque.getQuantidade() - quantidade;
                    estoqueDaoJDBC.atualizar(nome, novaQuantidadeEstoque, false);

                    ConexaoBD.fecharStatement(pst);
                    System.out.println("Produto adicionado ao carrinho com sucesso!");
                }
                catch (SQLException e){
                    throw new BdExcecao(e.getMessage());
                }
            } else {
                System.out.println("Quantidade insuficiente no estoque!");
            }
        }else {
            System.out.println("Produto Não encontrado");
        }

    }

    @Override
    public void atualizar(String nome, int quantidade) {
        EstoqueDaoJDBC estoqueDaoJDBC = new EstoqueDaoJDBC();
        Estoque estoque = estoqueDaoJDBC.buscarPorNome(nome);
        Carrinho carrinho = buscarPorNome(nome);

        if (estoque != null && carrinho != null){
            int difQuantidade = quantidade - carrinho.getQuantidade();

            if (estoque.getQuantidade() >= difQuantidade) {
                String sql = "UPDATE Carrinho SET quantidade = ? WHERE LOWER(nome) = LOWER(?)";

                try (Connection conn = ConexaoBD.conexaoComPostgresql();
                     PreparedStatement pst = conn.prepareStatement(sql)) {

                    pst.setInt(1, quantidade);
                    pst.setString(2, nome);

                    int linhasAfetadas = pst.executeUpdate();

                    int novaQuantidadeEstoque = estoque.getQuantidade() - difQuantidade;
                    estoqueDaoJDBC.atualizar(nome, novaQuantidadeEstoque, false);

                    if (linhasAfetadas > 0) {
                        System.out.println("Quantidade do produto atualizada com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado!");
                    }

                    ConexaoBD.fecharStatement(pst);
                } catch (SQLException e) {
                    throw new BdExcecao(e.getMessage());
                }
            }else {
                System.out.println("Estoque insuficiente");
            }
        }else {
            System.out.println("Produto não encontrado no estoque ou no carrinho!");
        }
    }


    @Override
    public void deletarPorNome(String nome) {
        EstoqueDaoJDBC estoqueDaoJDBC = new EstoqueDaoJDBC();
        Carrinho carrinho = buscarPorNome(nome);

        if (carrinho != null){
            String sql = "DELETE FROM Carrinho WHERE LOWER(nome) = LOWER(?)";

            try (Connection conn = ConexaoBD.conexaoComPostgresql();
                PreparedStatement pst = conn.prepareStatement(sql)){

                Estoque estoque = estoqueDaoJDBC.buscarPorNome(nome);
                if (estoque != null) {
                    int novaQuantidadeEstoque = estoque.getQuantidade() + carrinho.getQuantidade();
                    estoqueDaoJDBC.atualizar(nome, novaQuantidadeEstoque, false);
                }

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
    }

    @Override
    public Carrinho buscarPorNome(String nome) {
        String sql = "SELECT * FROM Carrinho WHERE LOWER(nome) = LOWER(?)";
        Carrinho carrinho = null;

        try (Connection conn = ConexaoBD.conexaoComPostgresql();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String categoria = rs.getString("categoria");
                double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");
                carrinho = new Carrinho(quantidade, nome, categoria, valor);
            }

            ConexaoBD.fecharStatement(pst);
            ConexaoBD.fecharResultSet(rs);
        } catch (SQLException e) {
            throw new BdExcecao(e.getMessage());
        }
        return carrinho;
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
