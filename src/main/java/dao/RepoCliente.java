package dao;

import model.Cliente;
import interfaces.IRepoCliente;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


public class RepoCliente implements IRepoCliente<Cliente> {
    
    private EntityManagerFactory emf= new ConectaDB().getConexao();
    
     @Override
    public Cliente save(Cliente obj) {
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


    public Cliente getById(int id) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = null;
        try {
            cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.id_cliente = :id", Cliente.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch ( Exception e) {
            System.out.println("Nenhum cliente encontrado com o id: " + id);
        } finally {
            em.close();
        }
        return cliente;
    }


    @Override
    public void update(Cliente obj) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(obj);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {
            em.close();
        } 
    }

    @Override
    public List<Cliente> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = null;
        try {
            clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } finally {
            em.close();
        }
        return clientes;
    }

}
