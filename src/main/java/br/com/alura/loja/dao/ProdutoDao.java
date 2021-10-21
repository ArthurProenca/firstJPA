package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = em.merge(categoria); //Garantir que esteja attached.
        this.em.remove(categoria);
    }

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto AS p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Produto AS p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        String jpql = "SELECT p FROM Produto AS p WHERE p.categoria.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }

    public BigDecimal buscarPreçoDoProdutoComNome(String nome){
        String jpql = "SELECT p.preco FROM Produto AS p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
    }
}
