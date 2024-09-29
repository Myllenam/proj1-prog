package dao;

import model.Cliente;
import model.Venda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class RepoVenda {
    private EntityManagerFactory emf= new ConectaDB().getConexao();

    public Venda save(Venda obj) {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(obj);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {
            em.close();
        }
        return obj;
    }


}

