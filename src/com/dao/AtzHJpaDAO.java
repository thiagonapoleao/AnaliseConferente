/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.AtzH;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author thiago.napoleao
 */
public class AtzHJpaDAO {
      private static AtzHJpaDAO instance;
    protected EntityManager entityManager;
    
    public static AtzHJpaDAO getInstance(){
    	if (instance == null){
    		instance = new AtzHJpaDAO();
        }
    	return instance;
    }

    public AtzHJpaDAO() {
    	entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
        	entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public AtzH getById(final Long id) {
    	return entityManager.find(AtzH.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<AtzH> findAll() {
        entityManager.clear();
      return entityManager.createQuery("FROM " + AtzH.class.getName()).getResultList();
       
    }

    public void persist(AtzH produto) {
    	try {
                entityManager.clear();
    		entityManager.getTransaction().begin();
    		entityManager.persist(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(AtzH produto) {
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

    public void remove(AtzH produto) {
    	try {
    		entityManager.getTransaction().begin();
            produto = entityManager.find(AtzH.class, produto.getId());
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
    	try {
    		AtzH produto = getById(id);
    		remove(produto);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }   
    
     public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("TRUNCATE atzH").executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
   
     
    public List<AtzH> findByCodigo(String codigo) {
        try {
            entityManager.clear();
            Query query = entityManager.createQuery("from AtzH e where e.codigo = :codigo");
            query.setParameter("codigo", codigo);
            List<AtzH> produtos = query.getResultList();
            return produtos; 
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
       
}


