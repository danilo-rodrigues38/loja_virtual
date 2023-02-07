package br.com.alura.loja_virtual.acoes;

import br.com.alura.loja_virtual.dao.CategoriaDao;
import br.com.alura.loja_virtual.dao.ProdutoDao;
import br.com.alura.loja_virtual.modulos.Categoria;
import br.com.alura.loja_virtual.modulos.Produto;
import br.com.alura.loja_virtual.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Excluir {
    public static void excluir() {
        while (true) {
            Scanner teclado = new Scanner(System.in);

            menuExcluir();
            System.out.print("\nDigite sua escolha: ");
            int opcao = teclado.nextInt();

            if (opcao == 1) {
                excluirProduto();
            } else if (opcao == 2) {
                excluirCategoria();
            } else if (opcao == 3) {
                break;
            } else {
                System.out.println("\nOpção inválida!!!");
                System.out.println("Digite somente valores entre 1 e 3.\n");
            }
        }
    }

    public static void menuExcluir() {
        System.out.println("\nO que quer excluir");
        System.out.println("--------------------\n");
        System.out.println("1 - Produtos");
        System.out.println("2 - Categoria");
        System.out.println("3 - Sair");
    }

    public static void excluirProduto() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Scanner teclado = new Scanner(System.in);

        System.out.print("\nDigite o ID do produto a ser excluido: ");
        Long id = teclado.nextLong();

        em.getTransaction().begin();

        Produto produto = produtoDao.buscarPorId(id);
        produtoDao.remover(produto);

        em.getTransaction().commit();
        em.close();
    }

    public static void excluirCategoria() {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        Consultar.consultarCategoria();

        Scanner teclado = new Scanner(System.in);

        System.out.print("\nDigite o ID da categoria a ser excluida: ");
        Long id = teclado.nextLong();

        em.getTransaction().begin();

        Categoria categoria = categoriaDao.buscarCategoriaId(id);
        categoriaDao.remover(categoria);

        em.getTransaction().commit();
        em.close();
    }
}
