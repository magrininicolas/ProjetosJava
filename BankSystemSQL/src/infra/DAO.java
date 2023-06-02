package infra;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.Client;


public class DAO {
  
  private static EntityManagerFactory emf;
  private EntityManager em;

  static {
    try {
      emf = Persistence.createEntityManagerFactory("BankSystemSQL");
    } catch (Exception e) {
      System.out.println("Ocorreu um erro ao criar o Entity Manager Factory: " + e.getMessage());
    }
  }

  public DAO() {
    em = emf.createEntityManager();
  }

  public DAO openTransaction() {
    em.getTransaction().begin();
    return this;
  }

  public DAO closeTransaction() {
    em.getTransaction().commit();
    return this;
  }

  public void close() {
    em.close();
  }

  public DAO include(Client client) {
    em.persist(client);
    return this;
  }

  public DAO remove(Client client) {
    em.remove(client);
    return this;
  }

  public DAO update(Client client) {
    em.merge(client);
    return this;
  }

  public <T> T find(Class<T> clazz, Long id){
    T entity = em.find(clazz, id);
    return entity;
  }
  
  public List<Client> obtainAll() {
    String jpql = "SELECT c FROM Client c" ;
    TypedQuery<Client> query = em.createQuery(jpql, Client.class);
    return query.getResultList();
  }
  

}
