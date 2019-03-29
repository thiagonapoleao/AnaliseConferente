/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.Micro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author thiago.napoleao
 */
public class MicroJpaDAO {
       private static MicroJpaDAO instance;
    protected EntityManager entityManager;
    
    public static MicroJpaDAO getInstance(){
    	if (instance == null){
    		instance = new MicroJpaDAO();
        }
    	return instance;
    }

    public MicroJpaDAO() {
    	entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
        	entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Micro getById(final Long id) {
    	return getById(id);
    }

    public Micro getById(final int id) {
        return entityManager.find(Micro.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Micro> findAll() {
        entityManager.clear();
      return entityManager.createQuery("FROM " + Micro.class.getName()).getResultList();
       
    }

    public void persist(Micro produto) {
    	try {
    		entityManager.getTransaction().begin();
    		entityManager.persist(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Micro produto) {
    	try {
    		entityManager.getTransaction().begin();
            entityManager.merge(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Micro produto) {
    	try {
    		entityManager.getTransaction().begin();
            produto = entityManager.find(Micro.class, produto.getId());
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int id) {
    	try {
    		Micro produto = getById(id);
    		remove(produto);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }   
    
     public void removeAll() {
        try {
            
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("TRUNCATE micro").executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
   
     
    public List<Micro> findByMicro(String micro) {
        try {
            entityManager.clear();
            Query query = entityManager.createQuery("from Micro e where e.micro = :micro");
            query.setParameter("micro", micro);
            List<Micro> produtos = query.getResultList();
            return produtos; 
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
}
    
    public List<Micro> findByMtr(String codigo) {
        try {
          
            
            Query query = entityManager.createQuery("from Micro e where e.codigo = :codigo");
            query.setParameter("codigo", codigo);
            List<Micro> produtos = query.getResultList();
            return produtos;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
}

   
}
