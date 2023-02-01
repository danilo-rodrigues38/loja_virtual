package br.com.alura.loja_virtual.dao;

import br.com.alura.loja_virtual.modulos.Produto;

import javax.persistence.EntityManager;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {

        this.em = em;
    }

    public void cadastro(Produto produto){

        this.em.persist(produto);
    }

}
