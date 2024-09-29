package dao;

import interfaces.IRepoGet;
import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class RepoProduto implements IRepoGet<Produto, Long> {
    private EntityManagerFactory emf= new ConectaDB().getConexao();

    @Override
    public Produto get(Long id) {
        EntityManager em = emf.createEntityManager();
        Produto produto = null;
        try {
            produto = em.find(Produto.class, id);
        } finally {
            em.close();
        }
        return produto;
    }



    @Override
    public List<Produto> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = null;
        try {
            produtos = em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        } finally {
            em.close();
        }
        return produtos;
    }


}
