/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.Login;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



/**
 *
 * @author thiago.napoleao
 */
public class LoginJpaDAO {
    
   private static LoginJpaDAO instance;
    protected EntityManager entityManager;
    
    public static LoginJpaDAO getInstance(){
    	if (instance == null){
    		instance = new LoginJpaDAO();
        }
    	return instance;
    }

    private LoginJpaDAO() {
    	entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernateLogin");
        if (entityManager == null) {
        	entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Login getById(final int id) {
    	return entityManager.find(Login.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Login> findAll() {
    	return entityManager.createQuery("FROM " + Login.class.getName()).getResultList();
    }

    public void persist(Login login) {
    	try {
    		entityManager.getTransaction().begin();
    		entityManager.persist(login);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Login login) {
    	try {
    		entityManager.getTransaction().begin();
            entityManager.merge(login);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Login login) {
    	try {
    		entityManager.getTransaction().begin();
            login = entityManager.find(Login.class, login.getId());
            entityManager.remove(login);
            entityManager.getTransaction().commit();
    	} catch (Exception ex) {
    		ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int id) {
    	try {
    		Login login = getById(id);
    		remove(login);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }

    
    public List<Login> findByUser(String user) {
        try {
            entityManager.clear();
            Query query = entityManager.createQuery("from Login e where e.user = :user");
            query.setParameter("user", user);
            List<Login> logins = query.getResultList();
            return logins;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public List<Login> findByCpf(String cpf) {
        try {
            Query query = entityManager.createQuery("from Login e where e.cpf = :cpf");
            query.setParameter("cpf", cpf);
            List<Login> logins = query.getResultList();
            return logins;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  
    public List<Login> login(String user, String pass) {
        try {
        
            Query query = entityManager.createQuery("from Login e where e.user = :user and e.password = :pass");
            query.setParameter("user", user); 
            query.setParameter("pass", pass); 

            List<Login> login = query.getResultList();
            return login;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }    

        
  
    
}
  
