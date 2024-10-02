package dao;

import model.Pagamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class RepoPagamento {
    private EntityManagerFactory emf= new ConectaDB().getConexao();


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
