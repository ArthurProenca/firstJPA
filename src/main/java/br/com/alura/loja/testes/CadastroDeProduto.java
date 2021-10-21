package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        Long id = 1l;
        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(id); //Retorna um objeto instanciado!!!!! Muito foda
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(produto -> System.out.println(produto.getNome()));

        List<Produto> todosNome = produtoDao.buscarPorNome("Xiaomi Redmi");
        todosNome.forEach(produto2 -> System.out.println(produto2.getNome()));

        List<Produto> todosCategoia = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todosCategoia.forEach(produto3 -> System.out.println(produto3.getNome()));

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

//        celular.setNome("Xiaomi Redmi");
//        celular.setDescricao("Muito legal");
//        celular.setPreco(new BigDecimal("800"));

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular); //Insert sendo feito. Ele sabe que é na tabela de produto pelo @Entity.
        //celular.setNome(""); Irá funcionar, graças ao ciclo de vida de um EntityManager. Logo, teremos um update.
        //celulares = em.merge(celulares); Iria fazer um update da entidade, afinal o merge recupera o estado de Managed.
        /*
        * em.remove(em) irá deletar.
        * em.flush() sincroniza
        * em.clear() detach.
        * em.merge() retorna para managed.
        * */
        em.getTransaction().commit(); //Efetua de fato as operações
        em.close();
    }
}
