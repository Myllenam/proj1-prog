package dao;

import model.Pagamento;
import model.Produto;
import model.Venda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

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

    public List<Pagamento> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Pagamento> pagamentos = null;
        try {
            pagamentos = em.createQuery("SELECT p FROM Pagamento p", Pagamento.class).getResultList();
        } finally {
            em.close();
        }
        return pagamentos;
    }
}
