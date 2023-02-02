package br.com.alura.loja_virtual.dao;

import br.com.alura.loja_virtual.modulos.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {

        this.em = em;
    }

    public void cadastro(Categoria categoria){
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria) {
        categoria = em.merge(categoria);
        this.em.remove(categoria);
    }

    public Categoria buscarCategoriaId(Long id) {
        return em.find(Categoria.class, id);
    }

    public Categoria buscarNomeCategoria(String nome){
        return em.find(Categoria.class, nome);
    }
    public List<Categoria> buscarTodas(){
        String jpql = "SELECT p FROM categoria p";
        return em.createQuery(jpql, Categoria.class).getResultList();
    }
}
