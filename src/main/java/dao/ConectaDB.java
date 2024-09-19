
package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConectaDB {
     private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("padaria");
    public static EntityManagerFactory getConexao(){
        return emf;
    }
    
}
