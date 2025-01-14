package org.compass.DAO;

import org.compass.Entidades.Estoque;

import java.util.List;

public interface EstoqueDAO {
    void atualizar(String nome, int quantidade, boolean exibirMensagem);
    Estoque buscarPorNome(String nome);
    void buscarTodos();
}
