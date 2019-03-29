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
@Table(name="monitoramento")
public class Atz implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
   
    
    private int id;
    private String codigo;
    private String nome;
    private String total;
    private String media;
    private String porcen; 
    private String hora;
    private String micro;
    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getPorcen() {
        return porcen;
    }

    public void setPorcen(String porcen) {
        this.porcen = porcen;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMicro() {
        return micro;
    }

    public void setMicro(String micro) {
        this.micro = micro;
    }
    
    
    
    
}
