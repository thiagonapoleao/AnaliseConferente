/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author thiago.napoleao
 */
@Entity
@Table(name="micro")
public class Micro implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
   
    private int id;
    private String micro;
    private String meta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMicro() {
        return micro;
    }

    public void setMicro(String micro) {
        this.micro = micro;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
    
    
    
    
}
