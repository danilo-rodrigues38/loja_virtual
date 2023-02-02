package br.com.alura.loja_virtual.acoes;


import br.com.alura.loja_virtual.dao.CategoriaDao;
import br.com.alura.loja_virtual.dao.ProdutoDao;
import br.com.alura.loja_virtual.modulos.Categoria;
import br.com.alura.loja_virtual.modulos.Produto;
import br.com.alura.loja_virtual.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class cadastar {
    public static void cadastrar() {
        while (true) {
            System.out.println("\nO que quer cadastrar?");
            System.out.println("----------------------\n");
            System.out.println("1 - Produtos");
            System.out.println("2 - Categoria");
            System.out.println("3 - Voltar");
            Scanner teclado = new Scanner(System.in);
            System.out.print("\nDigite a opção desejada: ");
            int opcao = teclado.nextInt();
            if (opcao > 3) {
                System.out.println("\nOpção inválida!!!");
                System.out.println("Digite somente valores entre 1 e 3.\n");
            } else if (opcao == 1) {
                System.out.println("\nInsira os dados do produto:");
                System.out.println("---------------------------\n");
                System.out.print("Nome: ");
                String nome = teclado.next().toUpperCase(Locale.ROOT);
                System.out.print("Descrição: ");
                String descricao = teclado.next().toUpperCase();
                System.out.print("Preço: ");
                String preco = teclado.next();

                EntityManager em = JPAUtil.getEntityManager();
                CategoriaDao categoriaDao = new CategoriaDao(em);
                List<Categoria> todos = categoriaDao.buscarTodos();
                todos.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));

                System.out.print("Categoria: ");
                Long ctgr = teclado.nextLong();

                //Categoria cat = new Categoria(ctgr);
                //Produto produto = new Produto(nome, descricao, new BigDecimal(preco), categoria);

                ProdutoDao produtoDao = new ProdutoDao(em);

                em.getTransaction().begin();

                Categoria categoria = categoriaDao.buscarCategoriaId(ctgr);
                Produto produto = new Produto(nome, descricao, new BigDecimal(preco), categoria);
                produtoDao.cadastro(produto);

                em.getTransaction().commit();
                em.close();
            } else if (opcao == 2) {
                System.out.println("\nInsira a nova categoria");
                System.out.println("-----------------------\n");
                System.out.print("Categoria: ");
                String ctgr = teclado.next().toUpperCase();

                Categoria categoria = new Categoria(ctgr);

                EntityManager em = JPAUtil.getEntityManager();
                CategoriaDao categoriaDao = new CategoriaDao(em);

                em.getTransaction().begin();
                categoriaDao.cadastro(categoria);

                em.getTransaction().commit();
                em.close();
            } else if (opcao == 3) {
                break;
            }
        }
    }
}
