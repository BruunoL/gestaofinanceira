package dev.bruno.gestaofinanceira.interfaces;

import dev.bruno.gestaofinanceira.entity.Buyer;
import dev.bruno.gestaofinanceira.entity.Payment;
import dev.bruno.gestaofinanceira.entity.dao.BuyerDAO;
import dev.bruno.gestaofinanceira.entity.dao.PaymentDAO;
import dev.bruno.gestaofinanceira.util.Util;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PopupRemoveBuyer  {
    
    private  javax.swing.JLabel LabelNo;
    private  javax.swing.JLabel LabelPopup3;
    private  javax.swing.JLabel LabelPopup4;
    private  javax.swing.JLabel LabelYes;
    private  javax.swing.JLabel labelPopup2;
    private  javax.swing.JPanel panelPopup;
    private  javax.swing.JPanel panelPopup2;
    
    public void initComponentsPopup(JFrame frame, JTable table, List<Buyer> list, DefaultTableModel modeloTable1, DefaultTableModel tablePayment, List<Payment> listPayments, PaymentDAO paymentDAO){
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
        panelPopup2.setBackground(new java.awt.Color(28, 36, 47));
                panelPopup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());


        labelPopup2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        labelPopup2.setForeground(new java.awt.Color(255, 255, 255));
        labelPopup2.setText(" Tem certeza que deseja");

        LabelPopup3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        LabelPopup3.setForeground(new java.awt.Color(255, 255, 255));
        if (table.getSelectedRowCount() == 1) {
            LabelPopup3.setText("deletar essa conta?");
        } else {
             LabelPopup3.setText("deletar " + table.getSelectedRowCount() + " conta(s)?");
        }

        javax.swing.GroupLayout panelPopup2Layout = new javax.swing.GroupLayout(panelPopup2);
        panelPopup2.setLayout(panelPopup2Layout);
        panelPopup2Layout.setHorizontalGroup(
            panelPopup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPopup2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPopup2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelPopup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPopup2Layout.createSequentialGroup()
                    .addContainerGap(54, Short.MAX_VALUE)
                    .addComponent(LabelPopup3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15, 15, 15)))
        );
        panelPopup2Layout.setVerticalGroup(
            panelPopup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPopup2Layout.createSequentialGroup()
                .addComponent(labelPopup2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(panelPopup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPopup2Layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(LabelPopup3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)))
        );

        panelPopup.add(panelPopup2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 290, -1));

        LabelPopup4.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        LabelPopup4.setForeground(new java.awt.Color(248, 69, 69));
        LabelPopup4.setText("*Todos os dados do comprador serão eliminados!*");
        panelPopup.add(LabelPopup4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 330, 40));

        LabelNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nao.png"))); // NOI18N
        LabelNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelNoMouseClicked(evt, frame);
            }
        });
        panelPopup.add(LabelNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 300, 40));

        LabelYes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sim.png"))); // NOI18N
        LabelYes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelYes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelYesMouseClicked(evt, frame, table, list, modeloTable1, tablePayment, listPayments, paymentDAO);
            }
        });
        panelPopup.add(LabelYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 300, 40));

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
        
    }
    
    private void LabelNoMouseClicked(java.awt.event.MouseEvent evt, JFrame frame) {                                     
        frame.dispose();
    }  

    private void LabelYesMouseClicked(java.awt.event.MouseEvent evt, JFrame frame, JTable table, List<Buyer> list, DefaultTableModel modeloTable1, DefaultTableModel tablePayment, List<Payment> listPayments, PaymentDAO paymentDAO) {                                      
        int numb = table.getSelectedRow();
        
        Object id = table.getValueAt(numb,0);
        Object name = table.getValueAt(numb,1); 
        
        BuyerDAO buyerDAO = new BuyerDAO();
        Buyer buyer = buyerDAO.readBuyer(Integer.parseInt(id.toString()));
                
        JOptionPane.showMessageDialog(null, name + " deletado com sucesso.", "Comprador deletado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
        buyerDAO.deleteBuyer(buyer);
        list.remove(buyer);
        frame.dispose();
        
        Util.readTableBuyers(modeloTable1, list, true);
        Util.readTablePayments(paymentDAO, tablePayment, listPayments, true);
    }   
}