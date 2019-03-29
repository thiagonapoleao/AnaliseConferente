/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author thiago.napoleao
 * 
 */


public class Impressao {
    
    
    public void  Impressao() throws PrintException {
           detectaImpressoras();
    }
    
    
    public PrintService[] detectaImpressoras() {
        try {
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
            
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public PrintService selecionaImpressora(String impressora) {
        PrintService printer = null;
        try {
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
            for (PrintService p: ps) {
                if (p.getName().contains(impressora)){
                    printer = p;
                    break; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return printer;
    }
    
    public synchronized boolean imprime(String texto, String impressora) {
        
        PrintService printer = selecionaImpressora(impressora);
        
        if (printer == null) {
            System.out.println("erro na impressao");
        } else {
            try {
                DocPrintJob dpj = printer.createPrintJob();
                InputStream stream = new ByteArrayInputStream(texto.getBytes());
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(stream, flavor, null);

                dpj.print(doc, null);
                    
                return true;
                
            } catch (PrintException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
