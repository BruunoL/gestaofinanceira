/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.bruno.gestaofinanceira.interfaces;

import dev.bruno.gestaofinanceira.entity.Payment;
import dev.bruno.gestaofinanceira.entity.dao.PaymentDAO;
import dev.bruno.gestaofinanceira.util.Util;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class PopupRemovePayment {
    
    private javax.swing.JLabel LabelNo;
    private javax.swing.JLabel LabelPopup3;
    private javax.swing.JLabel LabelPopup4;
    private javax.swing.JLabel LabelYes;
    private javax.swing.JLabel labelPopup2;
    private javax.swing.JPanel panelPopup;
    private javax.swing.JPanel panelPopup2;
    
    public void initComponentsPopup(JFrame frame, JTable table, List<Payment> list, DefaultTableModel modeloTable1) {
        panelPopup = new javax.swing.JPanel();
        panelPopup2 = new javax.swing.JPanel();
        labelPopup2 = new javax.swing.JLabel();
        LabelPopup3 = new javax.swing.JLabel();
        LabelPopup4 = new javax.swing.JLabel();
        LabelNo = new javax.swing.JLabel();
        LabelYes = new javax.swing.JLabel();

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        panelPopup.setBackground(new java.awt.Color(49, 56, 65));
        panelPopup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPopup2.setBackground(new java.awt.Color(28, 36, 47));
        panelPopup2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPopup2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        labelPopup2.setForeground(new java.awt.Color(255, 255, 255));
        labelPopup2.setText(" Tem certeza que deseja");
        panelPopup2.add(labelPopup2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 37));

        LabelPopup3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        LabelPopup3.setForeground(new java.awt.Color(255, 255, 255));
        LabelPopup3.setText("deletar essa venda?");
        panelPopup2.add(LabelPopup3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 240, 39));

        panelPopup.add(panelPopup2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 290, -1));

        LabelPopup4.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        LabelPopup4.setForeground(new java.awt.Color(248, 69, 69));
        LabelPopup4.setText("*Todos os dados dessa venda serão excluidos!*");
        panelPopup.add(LabelPopup4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 330, 40));

        LabelNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nao.png"))); // NOI18N
        LabelNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelNoMouseClicked(evt, frame);
            }
        });
        panelPopup.add(LabelNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 290, 40));

        LabelYes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sim.png"))); // NOI18N
        LabelYes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelYes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelYesMouseClicked(evt, frame, table, list, modeloTable1);
            }
        });
        panelPopup.add(LabelYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 290, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPopup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPopup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
    }// </editor-fold>                        

    private void LabelNoMouseClicked(java.awt.event.MouseEvent evt, JFrame frame) {                                     
        frame.dispose();
    }                                    

    private void LabelYesMouseClicked(java.awt.event.MouseEvent evt, JFrame frame, JTable table, List<Payment> list, DefaultTableModel modeloTable1) {                                      
        int numb = table.getSelectedRow();
         
        Object id = table.getValueAt(numb,0);
         
        PaymentDAO paymentDAO = new PaymentDAO();
        Payment payment = paymentDAO.readPayment(Integer.parseInt(id.toString()));
         
        JOptionPane.showMessageDialog(null, "Venda deletada com sucesso.", "Venda removida com sucesso!", JOptionPane.INFORMATION_MESSAGE);
        paymentDAO.deletePayment(payment);
        list.remove(payment);
        frame.dispose();

        Util.readTablePayments(paymentDAO, modeloTable1, list, true);
    } 
}