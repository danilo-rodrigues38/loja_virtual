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

public class Alterar {
    public static void alterar() {
        while (true) {
            menuAlterar();
            Scanner teclado = new Scanner(System.in);
            System.out.print("Digite a opção desejada: ");
            int opcao = teclado.nextInt();
            if (opcao > 3) {
                System.out.println("\nOpção inválida!!!");
                System.out.println("Digite somente valores entre 1 e 3.\n");
            } else if (opcao == 1) {
                alterarProduto();
            } else if (opcao == 2) {
                alterarCategoria();
            } else if (opcao == 3) {
                break;
            }
        }
    }

    public static void menuAlterar() {
        System.out.println("\nO que quer alterar");
        System.out.println("------------------");
        System.out.println("1 - Produtos");
        System.out.println("2 - Categorias");
        System.out.println("3 - Voltar");

    }

    private static void alterarProduto() {
        Scanner teclado = new Scanner(System.in);
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        while (true) {
            Long id = listarPorId();
            System.out.println("\nOs dados do ID selecionado serão alterados.");
            System.out.print("Deseja continuar!!! Tecle [Sim/Não]: ");
            String resp = teclado.next().toUpperCase().substring(0, 1);

            if (resp.equals("S")) {
                List<Categoria> todos = categoriaDao.buscarTodos();
                System.out.println("ID\t\t\tNOME");
                todos.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));

                System.out.print("\nNome: ");
                String nome = teclado.next().toUpperCase();
                System.out.print("Descrição: ");
                String descricao = teclado.next().toUpperCase();
                System.out.print("Preço: ");
                BigDecimal preco = teclado.nextBigDecimal();
                System.out.print("Categoria: ");
                Long ctgr = teclado.nextLong();

                em.getTransaction().begin();

                Categoria categoria = categoriaDao.buscarCategoriaId(ctgr);
                Produto produto = new Produto();
                produto.setId(id);
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setCategoria(categoria);

                produtoDao.atualizar(produto);

                em.getTransaction().commit();
                em.close();
                break;
            } else {
                break;
            }
        }
    }

    private static void alterarCategoria() {
        Scanner teclado = new Scanner(System.in);
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        List<Categoria> todos = categoriaDao.buscarTodos();
        System.out.println("Listando as categorias do banco de dados.");
        System.out.println("ID\t\t\tNOME");
        todos.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));

        while (true) {
            System.out.print("\nDigite o ID da categoria: ");
            Long id = teclado.nextLong();

            Categoria c = categoriaDao.buscarCategoriaId(id);
            System.out.println("ID\t\t\tNOME");
            System.out.println(c.getId() + " - " + c.getNome());

            System.out.println("\nOs dados da categoria selecionada serão alterados.");
            System.out.print("Deseja continuar? [Sim/Não]: ");
            String resp = teclado.next().toUpperCase().substring(0, 1);

            if (resp.equals("S")){
                System.out.print("Digite a alteração: ");
                String nome = teclado.next().toUpperCase();

                em.getTransaction().begin();

                Categoria categoria = new Categoria();
                categoria.setId(id);
                categoria.setNome(nome);
                categoriaDao.atualizar(categoria);

                em.getTransaction().commit();
                em.close();
                break;
            }else {
                break;
            }
        }
    }

    public static Long listarPorId(){
        EntityManager em = JPAUtil.getEntityManager();
        Scanner teclado = new Scanner(System.in);
        ProdutoDao produtoDao = new ProdutoDao(em);

        System.out.print("\nDigite o ID do produto: ");
        Long id = teclado.nextLong();
        Produto p = produtoDao.buscarPorId(id);

        System.out.println("ID: " + p.getId());
        System.out.println("Nome: " + p.getNome());
        System.out.println("Descrição: " + p.getDescricao());
        System.out.println("Preço: " + p.getPreco());
        System.out.println("Data cadastro: " + p.getDataCadastro());
        System.out.println("Categoria: " + p.getCategoria().getNome());

        return id;
    }
}
