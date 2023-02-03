package br.com.alura.loja_virtual.acoes;

import br.com.alura.loja_virtual.dao.CategoriaDao;
import br.com.alura.loja_virtual.dao.ProdutoDao;
import br.com.alura.loja_virtual.modulos.Categoria;
import br.com.alura.loja_virtual.modulos.Produto;
import br.com.alura.loja_virtual.util.JPAUtil;

import javax.persistence.EntityManager;
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

        System.out.print("\nDigite o ID do produto: ");
        Long id = teclado.nextLong();

        Produto p = produtoDao.buscarPorId(id);
        System.out.println("ID: " + p.getId());
        System.out.println("Nome: " + p.getNome());
        System.out.println("Descrição: " + p.getDescricao());
        System.out.println("Preço: " + p.getPreco());
        System.out.println("Data cadastro: " + p.getDataCadastro());
        System.out.println("Categoria: " + p.getCategoria().getNome());

    }

    private static void alterarCategoria() {

    }
}
