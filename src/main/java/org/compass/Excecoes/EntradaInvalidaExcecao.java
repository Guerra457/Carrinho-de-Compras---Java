package org.compass.Excecoes;

public class EntradaInvalidaExcecao extends RuntimeException {
    public EntradaInvalidaExcecao(String msg) {
        super(msg);
    }

    public static void tratarEntradaInvalida() {
        System.out.println("Erro: Entrada inválida! Por favor, tente novamente.");
    }
}
