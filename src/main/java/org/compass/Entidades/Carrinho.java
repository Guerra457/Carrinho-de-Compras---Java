package org.compass.Entidades;

import org.compass.Excecoes.BdExcecao;

public class Carrinho {
    private int id, quantidade;
    private String nome, categoria;
    private double valor, valorTotal;

    public Carrinho(int quantidade, String nome, String categoria, double valor, double valorTotal){}

    public Carrinho(int quantidade, String nome, String categoria, double valor){
        this.quantidade = quantidade;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.valorTotal = quantidade * valor;
    }

    public Carrinho(int id, int quantidade, String nome, String categoria, double valor, double valorTotal) {
        this.id = id;
        this.quantidade = quantidade;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.valorTotal = valorTotal;
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
        if (valor < 0 ) throw new BdExcecao("Valor não pode ser menor que zero");
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        if (valorTotal < 0 ) throw new BdExcecao("Valor total não pode ser menor que zero");
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return
                nome +
                ", categoria = " + categoria +
                ", valor = " + valor +
                ", quantidade = " + quantidade +
                ", valorTotal = " + (valor * quantidade)  + "\n";
    }
}
