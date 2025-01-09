package org.compass.Entidades;

public class Produto {
    private int id;
    private String nome, categoria;
    private double valor;

    public Produto(){

    }
    public Produto(int id, String nome, String categoria, double valor) {
        this.id = id;
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
        this.valor = valor;
    }
}
