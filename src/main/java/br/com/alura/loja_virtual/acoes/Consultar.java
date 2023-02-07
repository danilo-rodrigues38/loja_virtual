package br.com.alura.loja_virtual.acoes;

import br.com.alura.loja_virtual.dao.CategoriaDao;
import br.com.alura.loja_virtual.dao.ProdutoDao;
import br.com.alura.loja_virtual.modulos.Categoria;
import br.com.alura.loja_virtual.modulos.Produto;
import br.com.alura.loja_virtual.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class Consultar {
    public static void consultar() {
        while (true) {
            Scanner teclado = new Scanner(System.in);
            menuConsultar1();
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

    public static void menuConsultar1() {
        System.out.println("\nO que quer consultar");
        System.out.println("--------------------\n");
        System.out.println("1 - Produtos");
        System.out.println("2 - Categoria");
        System.out.println("3 - Sair");
    }

    public static void menuConsultar2() {
        System.out.println("\nConsulta produtos por:");
        System.out.println("---------------------\n");
        System.out.println("1 - ID");
        System.out.println("2 - Nome");
        System.out.println("3 - Categoria");
        System.out.println("4 - Listar todos");
        System.out.println("5 - sair");
    }

    public static void consultarProduto() {
        while (true) {
            EntityManager em = JPAUtil.getEntityManager();
            ProdutoDao produtoDao = new ProdutoDao(em);

            Scanner teclado = new Scanner(System.in);

            menuConsultar2();

            System.out.print("\nDigite sua escolha: ");
            int opcao = teclado.nextInt();

            if (opcao == 1) {
                Alterar.listarPorId();
            } else if (opcao == 2) {
                System.out.print("\nDigite o nome do produto: ");
                String nome = teclado.next().toUpperCase();

                List<Produto> todos = produtoDao.buscarPorNome(nome);
                System.out.printf("%-4s%-25s%-25s%-15s%-10s%-20s\n","ID", "NOME", "DESCRIÇÃO", "DATA", "VALOR", "CATEGORIA");
                System.out.println("-----------------------------------------------------------------------------------------------");
                todos.forEach(produto -> System.out.printf("%-4s%-25s%-25s%-15s%-10s%-20s\n", produto.getId(),
                        produto.getNome(), produto.getDescricao(), produto.getDataCadastro(), produto.getPreco(),
                        produto.getCategoria().getNome()));
            } else if (opcao == 3) {
                System.out.print("\nDigitte a categoria:");
                String categoria = teclado.next();

                List<Produto> todos = produtoDao.buscaPorNomeCategoria(categoria);
                System.out.printf("%-4s%-25s%-25s%-15s%-10s%-20s\n","ID", "NOME", "DESCRIÇÃO", "DATA", "VALOR", "CATEGORIA");
                System.out.println("-----------------------------------------------------------------------------------------------");
                todos.forEach(produto -> System.out.printf("%-4s%-25s%-25s%-15s%-10s%-20s\n", produto.getId(),
                        produto.getNome(), produto.getDescricao(), produto.getDataCadastro(), produto.getPreco(),
                        produto.getCategoria().getNome()));
            } else if (opcao == 4) {
                System.out.println("Listando todos os produtos cadastrados:\n");

                List<Produto> todos = produtoDao.buscarTodos();
                System.out.printf("%-4s%-25s%-25s%-15s%-10s%-20s\n","ID", "NOME", "DESCRIÇÃO", "DATA", "VALOR", "CATEGORIA");
                System.out.println("-----------------------------------------------------------------------------------------------");
                todos.forEach(produto -> System.out.printf("%-4s%-25s%-25s%-15s%-10s%-20s\n", produto.getId(),
                        produto.getNome(), produto.getDescricao(), produto.getDataCadastro(), produto.getPreco(),
                        produto.getCategoria().getNome()));
            } else if (opcao == 5) {
                break;
            } else {
                System.out.println("\nOpção inválida!!!");
                System.out.println("Digite somente valores entre 1 e 6.\n");
            }
        }
    }

    public static void consultarCategoria() {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        List<Categoria> todos = categoriaDao.buscarTodos();
        System.out.println("Listando as categorias do banco de dados.\n");
        System.out.printf("%-4s%-20s\n", "ID", "NOME");
        System.out.println("-------------------------");
        todos.forEach(c -> System.out.printf("%-4s%-20s\n", c.getId(), c.getNome()));
    }
}
