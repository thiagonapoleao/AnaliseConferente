/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.principal;

import com.bean.AtzH;
import com.bean.Pln0096r;
import com.dao.AtzHJpaDAO;
import com.dao.AtzJpaDAO;
import com.dao.Pln0096rJpaDAO;
import com.utils.DataHora;
import com.utils.Tabela;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.annotation.RetentionPolicy.CLASS;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author thiago.napoleao
 */
public class FrameAtualiza extends javax.swing.JPanel {
     DataHora dataHora;
     
    /**
     * Creates new form NewJPanel
     */
    public FrameAtualiza() {
        initComponents(); 
        arquivoCsvTxt.requestFocus(true);
        DataHora dataHora = new DataHora();
        defaults();
    }

   
        
   public void defaults() {
       arquivoCsvTxt.requestFocus(true);
       hatz();
       preencherTabela1();
       lblMensagem.setText("Aguardando arquivo PLN0096R!");
       lblMensagem.setForeground(Color.RED);
    }
    
                 
     
    public void hatz(){
    List<AtzH> atz = AtzHJpaDAO.getInstance().findAll();    
    for(int b = 0; b < atz.size(); b ++){
     String data = "";
     data = atz.get(b).getData().replace("", "");
     lblData.setText(data);     
     String hora = "";
     hora = atz.get(b).getHora().replace("", "");
     lblHora.setText(hora);     
    }
    } 
    
     public void atz(){      
         DataHora hora = new DataHora();
         AtzH atz = new AtzH();
         AtzJpaDAO.getInstance().removeAll();
         atz.setData(hora.Data());
         atz.setHora(hora.Hora());         
         AtzHJpaDAO.getInstance().persist(atz);      
         lblData.setText(hora.Data());
         lblHora.setText(hora.Hora());
     }
     
    
     public void txt(){        
        String Numero = arquivoCsvTxt.getText();
        File arquivoCSV = new File("K:\\spool\\"+Numero+".txt");
        System.out.println("Importando arquivo: \"" + arquivoCSV.getAbsolutePath() + "\"");
        if (arquivoCSV.exists()) {
        System.out.println("File exists!");
        lblMensagem.setText("Atualizando Produção Operacional!");
        Scanner ler = new Scanner("0096");
        String nome = ler.nextLine();
        try {
        Pln0096r pln0096r = new Pln0096r();
        Pln0096rJpaDAO.getInstance().removeAll();
        FileReader arq = new FileReader(arquivoCSV);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine(); // lê a primeira linha
        // a variável "linha" recebe o valor "null" quando o processo
        // de repetição atingir o final do arquivo texto
        while (linha != null) {
        System.out.printf("%s\n", linha);
        linha = lerArq.readLine(); // lê da segunda até a última linha
        String[] valores = linha.split(Pattern.quote("|"));           
           try{
           if(valores[1].length() > 0 ){
                     pln0096r.setCodigo(valores[1].trim());
                     pln0096r.setNome(valores[2]);
                     pln0096r.setH1(valores[3]);
                     pln0096r.setH2(valores[4]);
                     pln0096r.setH3(valores[5]);
                     pln0096r.setH4(valores[6]);
                     pln0096r.setH5(valores[7]);
                     pln0096r.setH6(valores[8]);
                     pln0096r.setH7(valores[9]);
                     pln0096r.setH8(valores[10]);
                     pln0096r.setH9(valores[11]);
                     pln0096r.setH10(valores[12]);
                     pln0096r.setH11(valores[13]);
                     pln0096r.setTotal(valores[14]);                     
                     Pln0096rJpaDAO.getInstance().merge(pln0096r);                                          
                     System.out.println(valores[0]);              }
              } catch (Exception e) {
                System.err.printf("Erro na Leitura do Arquivo: %s.\n",
                e.getMessage());
                arq.close();              
                preencherTabela1();
                atz();                
                }
              }
            System.out.println("close");
            arq.close();
            lblMensagem.setText("Importação realizado com sucesso!");
            lblMensagem.setForeground(Color.GREEN);
            preencherTabela1();
            } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
            }
           } else {
            System.out.println("File doesn't exist!");
            lblMensagem.setText("Aguardando arquivo PLN0096R");
            lblMensagem.setForeground(Color.red);
            preencherTabela1();
            }
            lblMensagem.setText("Importação realizado com sucesso!");
            lblMensagem.setForeground(Color.GREEN);
            preencherTabela1();
            arquivoCsvTxt.setText("");
            arquivoCsvTxt.requestFocus(true);
        }
         
     
        
      
        public void preencherTabela1() {
         try {
              ArrayList dados = new ArrayList();
             String[] Colunas = new String[]{"Nome", "1ª", "2ª", "3ª", "4ª", "5ª", "6ª", "7ª", "8ª", "9ª", "10ª", "11ª", "Total"};
             List<Pln0096r> prt = Pln0096rJpaDAO.getInstance().findAll();
             for(int i = 0; i < prt.size(); i++) {
             dados.add(new Object[]{prt.get(i).getNome(), prt.get(i).getH1(), prt.get(i).getH2(), prt.get(i).getH3(), prt.get(i).getH4(), prt.get(i).getH5(), prt.get(i).getH6(), prt.get(i).getH7(), prt.get(i).getH8(), prt.get(i).getH9(), prt.get(i).getH10(), prt.get(i).getH11(), prt.get(i).getTotal()});
            }

            Tabela tabela = new Tabela(dados, Colunas);
            tblRelatorio.setModel(tabela);
            tblRelatorio.setRowSorter(new TableRowSorter(tabela));
            
            tblRelatorio.getColumnModel().getColumn(0).setPreferredWidth(180);
            tblRelatorio.getColumnModel().getColumn(0).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(1).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(2).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(3).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(4).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(6).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(6).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(6).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(7).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(7).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(8).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(8).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(9).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(9).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(10).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(10).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(11).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(11).setResizable(false);
            
            tblRelatorio.getColumnModel().getColumn(12).setPreferredWidth(80);
            tblRelatorio.getColumnModel().getColumn(12).setResizable(false);
            
       
            
            tblRelatorio.getTableHeader().setReorderingAllowed(false);
            tblRelatorio.setAutoResizeMode(tblRelatorio.AUTO_RESIZE_OFF);
            tblRelatorio.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
                        
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
            //*********************************************************************      
            // Colre as letras da coluna conforme a seleção em uma caixa de combinação.
           if(value.equals(CLASS)){
               setForeground(Color.red);
           }else{
               setForeground(Color.BLACK);
           }
           tblRelatorio.setSelectionBackground(new java.awt.Color(51,153,255));
            return label;
           }
           });         
           }
       


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblRelatorio = new javax.swing.JTable();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        arquivoCsvTxt = new javax.swing.JTextField();
        btnImport = new javax.swing.JButton();

        setBackground(new java.awt.Color(18, 33, 71));

        tblRelatorio.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
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

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Produção atualiazada:");

        lblData.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 51, 51));
        lblData.setText("................");

        lblHora.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 51, 51));
        lblHora.setText("....................");

        lblMensagem.setFont(new java.awt.Font("Lucida Fax", 3, 24)); // NOI18N
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensagem.setText("......................................................................................");

        jDesktopPane5.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane5.setLayer(lblData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane5.setLayer(lblHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane5.setLayer(lblMensagem, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane5Layout = new javax.swing.GroupLayout(jDesktopPane5);
        jDesktopPane5.setLayout(jDesktopPane5Layout);
        jDesktopPane5Layout.setHorizontalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDesktopPane5Layout.setVerticalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMensagem)
                    .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHora))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Lucida Fax", 1, 55)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 51));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/logo1.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/logo15.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Arquivo PLN0096R");

        arquivoCsvTxt.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        arquivoCsvTxt.setForeground(new java.awt.Color(0, 51, 102));
        arquivoCsvTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arquivoCsvTxtKeyReleased(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane5)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(arquivoCsvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImport))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(arquivoCsvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel25)
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        // TODO add your handling code here:
         txt();       
    }//GEN-LAST:event_btnImportActionPerformed

    private void arquivoCsvTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arquivoCsvTxtKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == 10){
            try{
                txt();              
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_arquivoCsvTxtKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arquivoCsvTxt;
    private javax.swing.JButton btnImport;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JTable tblRelatorio;
    // End of variables declaration//GEN-END:variables
}
