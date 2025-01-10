package org.compass.DAO;

import org.compass.Entidades.Estoque;

import java.util.List;

public interface EstoqueDAO {
    void inserir(Estoque obj);
    void atualizar(Estoque obj);
    void deletarPorId(Integer id);
    Estoque buscarPorId(Integer id);
    List<Estoque> buscarTodos();
}
