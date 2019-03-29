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
@Table(name="pln0096r")
public class Pln0096r implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
   
    
    private int id;
    private String codigo;
    private String nome;
    private String h1;
    private String h2;
    private String h3;
    private String h4;
    private String h5;
    private String h6; 
    private String h7;
    private String h8;
    private String h9;
    private String h10;
    private String h11;
    private String total;

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

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getH4() {
        return h4;
    }

    public void setH4(String h4) {
        this.h4 = h4;
    }

    public String getH5() {
        return h5;
    }

    public void setH5(String h5) {
        this.h5 = h5;
    }

    public String getH6() {
        return h6;
    }

    public void setH6(String h6) {
        this.h6 = h6;
    }

    public String getH7() {
        return h7;
    }

    public void setH7(String h7) {
        this.h7 = h7;
    }

    public String getH8() {
        return h8;
    }

    public void setH8(String h8) {
        this.h8 = h8;
    }

    public String getH9() {
        return h9;
    }

    public void setH9(String h9) {
        this.h9 = h9;
    }

    public String getH10() {
        return h10;
    }

    public void setH10(String h10) {
        this.h10 = h10;
    }

    public String getH11() {
        return h11;
    }

    public void setH11(String h11) {
        this.h11 = h11;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

   
   
 
    

}
