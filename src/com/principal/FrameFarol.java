/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.principal;

import com.bean.Atz;
import com.bean.AtzH;
import com.dao.AtzHJpaDAO;
import com.dao.AtzJpaDAO;
import com.utils.DataHora;
import com.utils.Tabela;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import static java.lang.annotation.RetentionPolicy.CLASS;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author thiago.napoleao
 */
public class FrameFarol extends javax.swing.JPanel {

   
    DataHora dataHora;
 

    /**
     * Creates new form GraficoUpm
     */
    public FrameFarol() {
        initComponents();
      
        DataHora dataHora = new DataHora();
        hora();
        defaults();
        
        
          

    }

   
        
   public void defaults() {
        
        hora();
    
     
      
    }
    
     public void hora(){
         
        
           new Thread() {
             public void run() {
                while (true){
                atz();
                preencherTabela1();  
               
           
               
            try{
             Thread.sleep(40000);
             
            }catch(Exception e){
                   e.printStackTrace();  
           }
          } 
          }
         }.start(); 
       
     }
              
     
     
     public void atz(){
         
     System.out.println("atz horario");   
      try{
     List<AtzH> atz = AtzHJpaDAO.getInstance().findAll();
     
     for(int b = 0; b < atz.size(); b ++){
     String data = "";
     data = atz.get(b).getData().replace("", "");
     lblData.setText(data);
         System.out.println("lbldata " + data);
     String hora = "";
     hora = atz.get(b).getHora().replace("", "");
     lblHora.setText(hora);
         System.out.println("lblhora " + hora);
     }
     }catch(Exception ex){
         ex.printStackTrace();
     }    
     }
   
    public void contarRegistro(){
        
        int filas = tblRelatorio.getRowCount();
        lblRegistro.setText(" " + filas);
        
    }
      
     
      
        public void preencherTabela1() {
         try {
             
             ArrayList dados = new ArrayList();
             
             String[] Colunas = new String[]{"Micro", "Nome", "Media", "Total", "%", "Hora"};
             List<Atz> prt = AtzJpaDAO.getInstance().findAll();
             for(int i = 0; i < prt.size(); i++) {
             dados.add(new Object[]{prt.get(i).getMicro(), prt.get(i).getNome(), prt.get(i).getMedia(), prt.get(i).getTotal(), prt.get(i).getPorcen(), prt.get(i).getHora()});
            }

            Tabela tabela = new Tabela(dados, Colunas);
             tblRelatorio.setModel(tabela);
             tblRelatorio.setRowSorter(new TableRowSorter(tabela));
            
            tblRelatorio.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(0).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblRelatorio.getColumnModel().getColumn(1).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblRelatorio.getColumnModel().getColumn(2).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblRelatorio.getColumnModel().getColumn(3).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblRelatorio.getColumnModel().getColumn(4).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblRelatorio.getColumnModel().getColumn(5).setResizable(false);
            
       
                                 
            tblRelatorio.getTableHeader().setReorderingAllowed(false);
            tblRelatorio.setAutoResizeMode(tblRelatorio.AUTO_RESIZE_OFF);
            tblRelatorio.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
            contarRegistro();
            
        }catch(Exception ex) {
            ex.printStackTrace();
        } 
               
         
    }
      
        
        public void CorLinha(){
            
          // CLASS = (String) NOME.getSelectedItem(); // precisa criar a caixa de combinação  para selecionar o criteio para mudar a cor
          tblRelatorio.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
           
              @Override
              public Component getTableCellRendererComponent(JTable table, Object value,
                      boolean isSelected, boolean hasFaocus, int row, int column){
                  JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,  hasFaocus, row, column);
          
           /*       
            //*********************************************************************      
            // Colre a linha conforme a seleção em uma caixa de combinação.
            Color c = Color.WHITE;
            Object texto = table.getValueAt(row, 2);
            if(texto != null && CLASS.equals(texto.toString()))
                c = Color.RED;
            label.setBackground(c);
            tblRelatorio.setSelectionForeground(Color.GREEN);
            
            //*********************************************************************      
             */
           
          /*
           //*********************************************************************      
            // Colre as letras conforme a seleção em uma caixa de combinação.
            Color c = Color.BLACK;
            Object texto = table.getValueAt(row, 2);
            if(texto != null && CLASS.equals(texto.toString()))
                c = Color.RED;
            label.setBackground(c);
            //*********************************************************************     
         */
          
          //*********************************************************************      
            // Colre as letras da coluna conforme a seleção em uma caixa de combinação.
           if(value.equals(CLASS)){
               setForeground(Color.red);
           }else{
               setForeground(Color.BLACK);
           }
           tblRelatorio.setSelectionBackground(new java.awt.Color(51,153,255));
           
            //*********************************************************************  
           
                  return label;
                 }
              });         
           }
    
        
        public void arquivoCsv(){
          boolean geraArq = false;
        
        File arquivo;
        String patch = "";
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int x = file.showSaveDialog(null);
        if(x == 1){
            JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado!");
        }else{
            arquivo = file.getSelectedFile();
            patch = arquivo.getPath();
            patch +=".csv";
            geraArq = true;
        }
        if(geraArq){
            try{
                 FileWriter writer = new FileWriter(patch);
                 writer.append("Micro");
                 writer.append(";");
                 writer.append("Matricula");
                 writer.append(";");
                 writer.append("Nome");
                 writer.append(";");
                 writer.append("Media");
                 writer.append(";");
                 writer.append("Total");
                 writer.append(";");
                 writer.append("%");
                 writer.append(";");
                 writer.append("Hora");
                 writer.append("\n");
                 
                 List<Atz> pick = AtzJpaDAO.getInstance().findAll();
                 
                  for(int b = 0; b < pick.size(); b++) {
                     writer.append(pick.get(b).getMicro());
                     writer.append(";");
                     writer.append(pick.get(b).getCodigo());
                     writer.append(";");
                     writer.append(pick.get(b).getNome());
                     writer.append(";");
                     writer.append(pick.get(b).getMedia());
                     writer.append(";");
                     writer.append(pick.get(b).getTotal());
                     writer.append(";");
                     writer.append(pick.get(b).getPorcen());
                     writer.append(";");
                     writer.append(pick.get(b).getHora());
                     writer.append("\n");
                    
                }
                 writer.flush();
                 writer.close();
                 JOptionPane.showMessageDialog(null, "Arquivo Gerado com sucesso!");                 
            }catch(Exception e){
                e.printStackTrace();
            }       
        }           
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRelatorio = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblMensagem = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblRegistro = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setBackground(new java.awt.Color(18, 33, 71));

        jLabel25.setFont(new java.awt.Font("Lucida Fax", 1, 55)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 51));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/logo1.png"))); // NOI18N

        tblRelatorio.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        tblRelatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblRelatorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblRelatorio.setGridColor(new java.awt.Color(255, 255, 255));
        tblRelatorio.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tblRelatorio);

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produção atualiazada:");

        lblData.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 51, 51));
        lblData.setText("................");

        lblHora.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 51, 51));
        lblHora.setText("....................");

        jButton1.setFont(new java.awt.Font("Lucida Fax", 3, 11)); // NOI18N
        jButton1.setText("Limpar Analise");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblMensagem.setFont(new java.awt.Font("Lucida Fax", 3, 24)); // NOI18N
        lblMensagem.setForeground(new java.awt.Color(255, 255, 255));
        lblMensagem.setText("Analise de Produtividade da Conferencia");

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lblData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lblHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lblMensagem, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHora)
                    .addComponent(jButton1)
                    .addComponent(lblMensagem))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/logo15.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuários Conectados:");

        lblRegistro.setFont(new java.awt.Font("Lucida Fax", 3, 18)); // NOI18N
        lblRegistro.setForeground(new java.awt.Color(255, 51, 51));
        lblRegistro.setText(".......");

        jButton2.setFont(new java.awt.Font("Lucida Fax", 3, 11)); // NOI18N
        jButton2.setText("ArquivoCsv");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel25)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(377, 377, 377))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblRegistro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      try{
        AtzJpaDAO.getInstance().removeAll();
        preencherTabela1();
        jLabel1.requestFocus(true);
      }catch(Exception ex){
          ex.printStackTrace();
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        arquivoCsv();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JTable tblRelatorio;
    // End of variables declaration//GEN-END:variables

  
}
