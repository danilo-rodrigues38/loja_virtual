package br.com.alura.loja_virtual.dao;

import br.com.alura.loja_virtual.modulos.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {

        this.em = em;
    }

    public void cadastro(Produto produto){
        this.em.persist(produto);
    }

    public void atualizar(Produto produto){
        this.em.merge(produto);
    }

    public void remover(Produto produto){
        produto = em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String sql = "SELECT p FROM Produto p";
        return em.createQuery(sql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome){
        String sql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(sql, Produto.class).setParameter("nome", nome).getResultList();
    }

    public List<Produto> buscaPorNomeCategoria(String nome){
        String sql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        return em.createQuery(sql, Produto.class).setParameter("nome", nome).getResultList();
    }

    public BigDecimal buscarPrecoDoProduto(String nome){
        String sql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(sql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
    }
}

