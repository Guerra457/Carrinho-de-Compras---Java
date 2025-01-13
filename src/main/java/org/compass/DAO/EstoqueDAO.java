package org.compass.DAO;

import org.compass.Entidades.Estoque;

import java.util.List;

public interface EstoqueDAO {
    void atualizar(String nome, int quantidade);
    Estoque buscarPorNome(String nome);
    void buscarTodos();
}
