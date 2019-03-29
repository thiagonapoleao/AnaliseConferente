/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import com.bean.Login;

/**
 *
 * @author thiago.napoleao
 */
public class User {
    
    public static String MATRICULA_USUARIO;
    public static String NOME_USUARIO;
    
    public User(Login login){
        MATRICULA_USUARIO = login.getUser();
        NOME_USUARIO = login.getNome();
    }
    
}
