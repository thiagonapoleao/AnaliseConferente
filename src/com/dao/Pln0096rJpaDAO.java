/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.Pln0096r;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author thiago.napoleao
 */
public class Pln0096rJpaDAO {
         private static Pln0096rJpaDAO instance;
    protected EntityManager entityManager;
    
    public static Pln0096rJpaDAO getInstance(){
    	if (instance == null){
    		instance = new Pln0096rJpaDAO();
        }
    	return instance;
    }

    public Pln0096rJpaDAO() {
    	entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
        	entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Pln0096r getById(final Long id) {
    	return entityManager.find(Pln0096r.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Pln0096r> findAll() {
        entityManager.clear();
      return entityManager.createQuery("FROM " + Pln0096r.class.getName()).getResultList();
       
    }

    public void persist(Pln0096r produto) {
    	try {
    		entityManager.getTransaction().begin();
    		entityManager.persist(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Pln0096r produto) {
    	try {
            entityManager.clear();
            entityManager.getTransaction().begin();
            entityManager.merge(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Pln0096r produto) {
    	try {
    		entityManager.getTransaction().begin();
            produto = entityManager.find(Pln0096r.class, produto.getCodigo());
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
    	try {
    		Pln0096r produto = getById(id);
    		remove(produto);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }   
    
     public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("TRUNCATE pln0096r").executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
   
     
    public List<Pln0096r> findByCodigo(String codigo) {
        try {
            entityManager.clear();
            Query query = entityManager.createQuery("from Pln0096r e where e.codigo = :codigo");
            query.setParameter("codigo", codigo);
            List<Pln0096r> produtos = query.getResultList();
            return produtos; 
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
}
    
    public List<Pln0096r> findByMtr(String codigo) {
        try {
          
            
            Query query = entityManager.createQuery("from Pln0096r e where e.codigo = :codigo");
            query.setParameter("codigo", codigo);
            List<Pln0096r> produtos = query.getResultList();
            return produtos;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
}
}
