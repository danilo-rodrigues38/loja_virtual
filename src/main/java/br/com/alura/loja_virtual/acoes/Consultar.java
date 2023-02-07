package br.com.alura.loja_virtual.acoes;

import br.com.alura.loja_virtual.dao.ProdutoDao;
import br.com.alura.loja_virtual.modulos.Produto;
import br.com.alura.loja_virtual.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class Consultar {
    public static void consultar() {
        while (true) {
            Scanner teclado = new Scanner(System.in);
            menuConsultar();
            System.out.print("\nDigite sua escolha: ");
            int opcao = teclado.nextInt();

            if (opcao > 3) {
                System.out.println("\nOpção inválida!!!");
                System.out.println("Digite somente valores entre 1 e 3.\n");
            } else if (opcao == 1) {
                consultarProduto();
            } else if (opcao == 2) {
                consultarCategoria();
            } else if (opcao == 3) {
                break;
            }
        }
    }

    public static void menuConsultar() {
        System.out.println("\nO que quer consultar");
        System.out.println("--------------------\n");
        System.out.println("1 - Produtos");
        System.out.println("2 - Categoria");
        System.out.println("3 - Sair");
    }

    public static void consultarProduto() {
        while (true) {
            EntityManager em = JPAUtil.getEntityManager();
            ProdutoDao produtoDao = new ProdutoDao(em);

            Scanner teclado = new Scanner(System.in);
            System.out.println("\nConsulta produtos por:");
            System.out.println("---------------------\n");
            System.out.println("1 - ID");
            System.out.println("2 - Nome");
            System.out.println("3 - Categoria");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Por valor");
            System.out.println("6 - sair");
            System.out.print("\nDigite sua escolha: ");
            int opcao = teclado.nextInt();

            if (opcao == 1) {
                Alterar.listarPorId();
            } else if (opcao == 2) {

            } else if (opcao == 3) {

            } else if (opcao == 4) {

            } else if (opcao == 5) {

            } else if (opcao == 6) {
                break;
            } else {
                System.out.println("\nOpção inválida!!!");
                System.out.println("Digite somente valores entre 1 e 6.\n");
            }
        }
    }

    public static void consultarCategoria() {

    }
}
