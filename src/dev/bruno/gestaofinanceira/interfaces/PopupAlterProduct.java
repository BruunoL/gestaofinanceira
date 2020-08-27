/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.bruno.gestaofinanceira.interfaces;

import dev.bruno.gestaofinanceira.entity.Buyer;
import dev.bruno.gestaofinanceira.entity.Product;
import dev.bruno.gestaofinanceira.entity.dao.ProductDAO;
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
public class PopupAlterProduct {
    
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JTextField textLucro;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textPrice;
    
    public void initComponentsPopup(JFrame frame, JTable table, List<Product> list, DefaultTableModel modeloTable1, String nameSelected, String priceSelected, String lucroSelected, int id){
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textLucro = new javax.swing.JTextField();
        registerLabel = new javax.swing.JLabel();

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        frame.setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(49, 56, 65));

        jPanel2.setBackground(new java.awt.Color(28, 36, 47));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("deseja para:");

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Insira os novos dados que");

        nameLabel.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 102, 102));
        nameLabel.setText(nameSelected);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt, frame);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Nome:");

        textName.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        textName.setText("Insira um novo nome");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Preço:");

        textPrice.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        textPrice.setText("Insira um novo preço");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Lucro:");

        textLucro.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        textLucro.setText("Insira um novo lucro");

        registerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/alterar.png"))); // NOI18N
        registerLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               registerLabelMouseClicked(evt, nameSelected, priceSelected, lucroSelected, id, list, modeloTable1, frame);
            }
       });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registerLabel)
                .addGap(95, 95, 95))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5)))))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(registerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        frame.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 450));

        frame.pack();
    }// </editor-fold>                        

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt, JFrame frame) {                                     
        frame.dispose();
    }
    
    private boolean isInt(String text) {
        boolean bool = true;
        
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException ex) {
            bool = false;
        }
        
        if (text.contains(",")) {
            bool = true;
        }
        
        return bool;
    }
    
    private void registerLabelMouseClicked(java.awt.event.MouseEvent evt, String name, String price, String lucro, int id, List<Product> list, DefaultTableModel modeloTable1, JFrame frame) {
        ProductDAO productDAO = new ProductDAO();
        
        Product product = productDAO.readProduct(id);

        if (textName.getText().equals("Insira um novo nome") && textPrice.getText().equals("Insira um novo preço") && (textLucro.getText().equals("Insira um novo lucro"))) {
            JOptionPane.showMessageDialog(null, "Insira novos dados.", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
        } else if (textName.getText().length() == 0 || textPrice.getText().length() == 0 || textLucro.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo de nome,preço ou lucro sem caracteres suficientes.", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
        } else if (textName.getText().length() > 50 || textPrice.getText().length() > 20 || textLucro.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Nome,preço ou lucro muito grande!.", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
        } else if (textName.getText().equals(name) && (textPrice.getText().equals(price) && (textLucro.getText().equals(lucro)))) {
            JOptionPane.showMessageDialog(null, "Nome,preço e lucro continuam iguais.", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
        } else if (!isInt(textPrice.getText())) {
            JOptionPane.showMessageDialog(null, "O preço informado não é um número!", "Erro ao registrar", JOptionPane.ERROR_MESSAGE);
        } else if (!isInt(textLucro.getText())) {
            JOptionPane.showMessageDialog(null, "O lucro informado não é um número!", "Erro ao registrar", JOptionPane.ERROR_MESSAGE);
        } else {
            
            if (productDAO.existsProductName(textName.getText())) {
                JOptionPane.showMessageDialog(null, "Produto já existe em nosso banco de dados.", "Erro ao registrar", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso.", "Alteração feita com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            product.setName(textName.getText());

            String priceFinal = textPrice.getText().replaceAll(",", ".");
            String lucrofinal = textLucro.getText().replaceAll(",", ".");

            product.setPrice(Double.parseDouble(priceFinal));
            product.setProductionProfit(Double.parseDouble(lucrofinal));

            productDAO.updateProduct(product);
            frame.dispose();

            Util.readTableProducts(modeloTable1, list, true);
        }
    } 
}