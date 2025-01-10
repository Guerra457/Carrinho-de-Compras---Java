package org.compass.DAO;



import org.compass.Entidades.Carrinho;

import java.util.List;

public interface CarrinhoDAO {
    void inserir(Carrinho obj);
    void atualizar(Carrinho obj);
    void deletarPorId(Integer id);
    Carrinho buscarPorId(Integer id);
    List<Carrinho> buscarTodos();
}
