/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.Atz;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author thiago.napoleao
 */
public class AtzJpaDAO {
     private static AtzJpaDAO instance;
    protected EntityManager entityManager;
    
    public static AtzJpaDAO getInstance(){
    	if (instance == null){
    		instance = new AtzJpaDAO();
        }
    	return instance;
    }

    public AtzJpaDAO() {
    	entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
     
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
        	entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Atz getById(final Long id) {
    	return entityManager.find(Atz.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Atz> findAll() {
             entityManager.clear();
      return entityManager.createQuery("FROM " + Atz.class.getName()).getResultList();
       
    }

    public void persist(Atz produto) {
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

    public void merge(Atz produto) {
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

    public void remove(Atz produto) {
    	try {
    		entityManager.getTransaction().begin();
            produto = entityManager.find(Atz.class, produto.getId());
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
    	try {
    		Atz produto = getById(id);
    		remove(produto);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }   
    
     public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("TRUNCATE monitoramento").executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
   
     
    public List<Atz> findByCodigo(String codigo) {
        try {
            entityManager.clear();
            Query query = entityManager.createQuery("from Atz e where e.codigo = :codigo");
            query.setParameter("codigo", codigo);
            List<Atz> produtos = query.getResultList();
            return produtos; 
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
       
}

