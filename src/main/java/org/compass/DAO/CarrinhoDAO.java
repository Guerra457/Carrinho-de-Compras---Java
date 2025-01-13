package org.compass.DAO;



import org.compass.Entidades.Carrinho;

import java.util.List;

public interface CarrinhoDAO {
    void inserir(String nome, int quantidade);
    void atualizar(String nome, int quantidade);
    void deletarPorNome(String nome);
    Carrinho buscarPorNome(String nome);
    void buscarTodos();
    double calcularValorTotal();
}
