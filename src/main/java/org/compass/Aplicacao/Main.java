package org.compass.Aplicacao;

import org.compass.DAO.Implementacao.CarrinhoDaoJDBC;
import org.compass.DAO.Implementacao.EstoqueDaoJDBC;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EstoqueDaoJDBC estoqueDaoJDBC = new EstoqueDaoJDBC();
        CarrinhoDaoJDBC carrinhoDaoJDBC = new CarrinhoDaoJDBC();


        try(Scanner scanner = new Scanner(System.in)){
            boolean rodando = true;

            while (rodando) {
                System.out.println("\n=== Loja Virtual ===");
                System.out.println("Escolha uma opção: ");
                System.out.println("1. Adicionar produto ao carrinho");
                System.out.println("2. Atualizar quantidade no carrinho");
                System.out.println("3. Remover produto do carrinho");
                System.out.println("4. Visualizar carrinho");
                System.out.println("5. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Produtos:");
                        estoqueDaoJDBC.buscarTodos();
                        System.out.println();
                        System.out.println("Digite o nome do produto que deseja adicionar ao carrinho: ");
                        String nome = scanner.nextLine();
                        System.out.println("Digite a quantidade desejada: ");
                        int quantidade = scanner.nextInt();
                        scanner.nextLine();
                        carrinhoDaoJDBC.inserir(nome, quantidade);
                        break;
                    case 2:
                        System.out.println("Digite o nome do produto que deseja atualizar a quantidade: ");
                        String nomeATT = scanner.nextLine();
                        System.out.println("Digite a nova quantidade: ");
                        int quantidadeATT = scanner.nextInt();
                        scanner.nextLine();
                        carrinhoDaoJDBC.atualizar(nomeATT, quantidadeATT);
                        break;
                    case 3:
                        System.out.println("Digite o nome do produto que deseja remover do carrinho: ");
                        String nomeRem = scanner.nextLine();
                        carrinhoDaoJDBC.deletarPorNome(nomeRem);
                        break;
                    case 4:
                        System.out.println("Carrinho:");
                        carrinhoDaoJDBC.buscarTodos();
                        double total = carrinhoDaoJDBC.calcularValorTotal();
                        System.out.printf("Valor total: R$ %.2f\n", total);
                        break;
                    case 5:
                        rodando = false;
                        System.out.println("Obrigado por usar a Loja Virtual!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                }
            }
        }
    }
}