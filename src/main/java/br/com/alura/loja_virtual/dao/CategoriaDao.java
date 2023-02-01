package br.com.alura.loja_virtual.dao;

import br.com.alura.loja_virtual.modulos.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastro(Categoria categoria){
        this.em.persist(categoria);
    }

}
