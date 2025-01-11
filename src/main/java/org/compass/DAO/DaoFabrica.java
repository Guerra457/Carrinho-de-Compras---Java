package org.compass.DAO;

import org.compass.BD.ConexaoBD;
import org.compass.DAO.Implementacao.CarrinhoDaoJDBC;

public class DaoFabrica {
    public static CarrinhoDAO criarCarrinhoDAO(){
        return new CarrinhoDaoJDBC(ConexaoBD.conexaoComPostgresql());
    }
}
