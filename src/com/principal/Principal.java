/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.principal;

import com.bean.Login;
import com.dao.LoginJpaDAO;
import com.user.User;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago Napoleão
 */
public class Principal extends javax.swing.JFrame {
 
     FrameFarol farol;
     FrameCadastro cadastro;
     FrameAtualiza atz;
     FrameMetas metas;
   
    
   
    /**
     * Creates new form Principal
     */
    public Principal() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, ParseException {
     initComponents();
     carregaPainel();
     lblUser.setText(User.NOME_USUARIO);
    
    
    }
    
    
     
  
   public void carregaPainel() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, ParseException {
        farol = new FrameFarol();
        Root.add(farol, "farol");
        CardLayout card = (CardLayout) Root.getLayout();
        card.show(Root, "farol");           
     }
   
 
   
        
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Root = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu1 = new javax.swing.JMenu();
        MenuAnalise = new javax.swing.JMenuItem();
        Menu2 = new javax.swing.JMenu();
        MenuMetas = new javax.swing.JMenuItem();
        Menu3 = new javax.swing.JMenu();
        MenuCadastro = new javax.swing.JMenuItem();
        Menu4 = new javax.swing.JMenu();
        MenuAtualiza = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        Root.setBackground(new java.awt.Color(18, 33, 71));
        Root.setMinimumSize(new java.awt.Dimension(731, 451));
        Root.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(11, 40, 72));

        lblUser.setFont(new java.awt.Font("Lucida Fax", 3, 10)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUser.setText("Thiago Cesar Napoleão");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 3, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sair");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Menu1.setText("Analise  ");
        Menu1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        MenuAnalise.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        MenuAnalise.setText("Analise");
        MenuAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAnaliseActionPerformed(evt);
            }
        });
        Menu1.add(MenuAnalise);

        jMenuBar1.add(Menu1);

        Menu2.setText("Metas  ");
        Menu2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        MenuMetas.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        MenuMetas.setText("Cadastro de Metas");
        MenuMetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuMetasActionPerformed(evt);
            }
        });
        Menu2.add(MenuMetas);

        jMenuBar1.add(Menu2);

        Menu3.setText("Cadastro  ");
        Menu3.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        MenuCadastro.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        MenuCadastro.setText("Cadastro de Conferente");
        MenuCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastroActionPerformed(evt);
            }
        });
        Menu3.add(MenuCadastro);

        jMenuBar1.add(Menu3);

        Menu4.setText("Produção");
        Menu4.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N

        MenuAtualiza.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        MenuAtualiza.setText("Atualizar");
        MenuAtualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAtualizaActionPerformed(evt);
            }
        });
        Menu4.add(MenuAtualiza);

        jMenuBar1.add(Menu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Root, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Root, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAnaliseActionPerformed
        farol = new FrameFarol();
        Root.add(farol, "farol");
        CardLayout card = (CardLayout) Root.getLayout();
        card.show(Root, "farol");
    }//GEN-LAST:event_MenuAnaliseActionPerformed

    private void MenuMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuMetasActionPerformed
        // TODO add your handling code here:
        metas = new FrameMetas();
        Root.add(metas, "metas");
        CardLayout card = (CardLayout) Root.getLayout();
        card.show(Root, "metas");  
    }//GEN-LAST:event_MenuMetasActionPerformed

    private void MenuCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastroActionPerformed
        // TODO add your handling code here:
        cadastro = new FrameCadastro();
        Root.add(cadastro, "cadastro");
        CardLayout card = (CardLayout) Root.getLayout();
        card.show(Root, "cadastro");
    }//GEN-LAST:event_MenuCadastroActionPerformed

    private void MenuAtualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAtualizaActionPerformed
        // TODO add your handling code here:
        atz = new FrameAtualiza();
        Root.add(atz, "atz");
        CardLayout card = (CardLayout) Root.getLayout();
        card.show(Root, "atz");
    }//GEN-LAST:event_MenuAtualizaActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        jLabel2.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
         jLabel2.setForeground(Color.red);
    }//GEN-LAST:event_jLabel2MouseEntered

    

/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Menu1;
    private javax.swing.JMenu Menu2;
    private javax.swing.JMenu Menu3;
    private javax.swing.JMenu Menu4;
    private javax.swing.JMenuItem MenuAnalise;
    private javax.swing.JMenuItem MenuAtualiza;
    private javax.swing.JMenuItem MenuCadastro;
    private javax.swing.JMenuItem MenuMetas;
    private javax.swing.JPanel Root;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables
}
