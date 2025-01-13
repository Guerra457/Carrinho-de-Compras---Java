package org.compass.Entidades;

import org.compass.Excecoes.BdExcecao;

import java.util.Objects;

public class Estoque {
    private int id, quantidade;
    private String nome, categoria;
    private double valor;

    public Estoque(){}

    public Estoque(int id, int quantidade, String nome, String categoria, double valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0 ) throw new BdExcecao("Quantidade não pode ser menor que zero");
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) throw new BdExcecao("Valor não pode ser menor que zero");
        this.valor = valor;
    }

    @Override
    public String toString() {
        return
                "quantidade=" + quantidade +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", valor=" + valor;
    }
}
