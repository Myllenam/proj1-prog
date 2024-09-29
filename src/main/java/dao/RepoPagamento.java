package dao;

import model.Pagamento;
import model.Venda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class RepoPagamento {
    private EntityManagerFactory emf= new ConectaDB().getConexao();

    public Pagamento save(Pagamento obj) {
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
