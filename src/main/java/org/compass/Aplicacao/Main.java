package org.compass.Aplicacao;



import org.compass.BD.ConexaoBD;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection conn = ConexaoBD.conexaoComPostgresql();
        ConexaoBD.fecharConexaoComPostgresql();
    }
}