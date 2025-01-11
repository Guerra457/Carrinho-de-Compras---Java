package org.compass.DAO.Implementacao;

import org.compass.BD.ConexaoBD;
import org.compass.DAO.CarrinhoDAO;
import org.compass.Entidades.Carrinho;
import org.compass.Entidades.Produto;
import org.compass.Excecoes.BdExcecao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarrinhoDaoJDBC implements CarrinhoDAO {

    private Connection conn;

    public CarrinhoDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void inserir(Carrinho obj) {

    }

    @Override
    public void atualizar(Carrinho obj) {

    }

    @Override
    public void deletarPorId(Integer id) {

    }

    @Override
    public Carrinho buscarPorId(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement("select Produtos.nome, Produtos.categoria, CarrinhoDeCompras.quantidade, Produtos.valor, CarrinhoDeCompras.valor_total\n" +
                    "from Produtos inner join CarrinhoDeCompras\n" +
                    "on CarrinhoDeCompras.produto_id = Produtos.id\n" +
                    "where CarrinhoDeCompras.id = ?");

            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setValor(rs.getDouble("valor"));

                Carrinho carrinho = new Carrinho();
                carrinho.setProduto(produto);
                carrinho.setQuantidade(rs.getInt("quantidade"));
                carrinho.setValorTotal(rs.getDouble("valor_total"));

                return carrinho;
            }
            return null;
        }
        catch (SQLException e){
            throw new BdExcecao(e.getMessage());
        }
        finally {
            ConexaoBD.fecharStatement(pst);
            ConexaoBD.fecharResultSet(rs);
        }
    }

    @Override
    public List<Carrinho> buscarTodos() {
        return null;
    }
}
