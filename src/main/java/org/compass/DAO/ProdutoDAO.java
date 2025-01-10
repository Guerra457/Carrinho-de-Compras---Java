package org.compass.DAO;



import org.compass.Entidades.Produto;

import java.util.List;

public interface ProdutoDAO {
    void inserir(Produto obj);
    void atualizar(Produto obj);
    void deletarPorId(Integer id);
    Produto buscarPorId(Integer id);
    List<Produto> buscarTodos();
}
