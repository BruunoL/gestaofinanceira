/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.bruno.gestaofinanceira.interfaces;
import dev.bruno.gestaofinanceira.entity.Account;
import dev.bruno.gestaofinanceira.entity.Buyer;
import dev.bruno.gestaofinanceira.entity.Payment;
import dev.bruno.gestaofinanceira.entity.Product;
import dev.bruno.gestaofinanceira.entity.Shopping;
import dev.bruno.gestaofinanceira.entity.dao.BuyerDAO;
import dev.bruno.gestaofinanceira.entity.dao.PaymentDAO;
import dev.bruno.gestaofinanceira.entity.dao.ProductDAO;
import dev.bruno.gestaofinanceira.entity.dao.ShoppingDAO;
import dev.bruno.gestaofinanceira.util.Util;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListModel; 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class MainFrame extends javax.swing.JFrame {
    
    private PaymentDAO paymentDAO;
    private BuyerDAO buyerDAO;
    private ShoppingDAO shoppingDAO;
    private ProductDAO productDAO;
    private Account account;
    private List<Buyer> listBuyers;
    private List<Payment> listPayments;
    private List<Shopping> listShopping;
    private List<Product> listProducts;
    
    private final DefaultTableModel modeloTableBuyers;
    private final DefaultTableModel modeloTableShopping;
    private final DefaultTableModel modeloTablePayments;
    private final DefaultTableModel modeloTableProducts;
    
    private JFrame framePopupRemoveBuyer;
    private JFrame framePopupAddBuyer;
    private JFrame framePopupAlterBuyer;
    
    private JFrame framePopupAddProduct;
    private JFrame framePopupRemoveProduct;
    private JFrame framePopupAlterProduct;
    
    private JFrame framePopupAddPayment;
    private JFrame framePopupRemovePayment;
    
    private JFrame framePopupAddShopping;
    private JFrame framePopupRemoveShopping;
    
    /**
     * Creates new form MainFrame
     */
    
    public MainFrame() {        
        paymentDAO = new PaymentDAO();
        buyerDAO = new BuyerDAO();
        shoppingDAO = new ShoppingDAO();
        productDAO = new ProductDAO();
        account = new Account();
        
        Util.setValues(account, paymentDAO, shoppingDAO, "Dia");
                       
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
        setIconImage(new ImageIcon(getClass().getResource("/icons/icon.png")).getImage());
        initComponents();
                
        setLocationRelativeTo(null);
       
        listBuyers = buyerDAO.findAllBuyers();
        listPayments = paymentDAO.findAllPayments();
        listShopping = shoppingDAO.findAllShoppings();
        listProducts = productDAO.findAllProducts();
        
        setListAccount("Dia");
        
        modeloTableBuyers = (DefaultTableModel) jTable1.getModel();
        modeloTablePayments = (DefaultTableModel) jTable2.getModel();
        modeloTableShopping = (DefaultTableModel) jTable3.getModel();
        modeloTableProducts = (DefaultTableModel) jTable4.getModel();

        Util.readTableBuyers(modeloTableBuyers, listBuyers, false);
        Util.readTablePayments(paymentDAO, modeloTablePayments, listPayments, false);
        Util.readTableShoppings(modeloTableShopping, listShopping, false);
        Util.readTableProducts(modeloTableProducts, listProducts, false);
    }
    
    private void setListAccount(String searchType) {
                
        DefaultListModel modelo = new DefaultListModel();
        listProducts.forEach((product) -> {
            double profit = paymentDAO.getQtdeItensPayment(product.getId(), searchType) * product.getProductionProfit();
            modelo.addElement(product.getName() + ": " + Util.stringFormatada(profit));
        });

        jList1.setModel(modelo);
        
    }
    
    private String stringFormatada(double value) {
        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(value);
          
        return valorString;
    }
    
    private void setLabelsAccount() {
        jLabel27.setText("Capital da empresa: " + stringFormatada(account.getCapital()) + " R$");
        jLabel28.setText("Entrou: " + stringFormatada(account.getMoneyIn()) + " R$");
        jLabel86.setText("Saiu: " + stringFormatada(account.getMoneyOut()) + " R$");
        
        jLabel29.setText("Entradas em dinheiro: " + stringFormatada(account.getMoneyTypeIn()) + " R$");
        jLabel85.setText("Entradas em crédito: " + stringFormatada(account.getCreditoIn()) + " R$");
        jLabel84.setText("Entradas em débito: " + stringFormatada(account.getDebitoIn()) + " R$");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        frameAccount = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel87 = new javax.swing.JLabel();
        frameVendas = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel60 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel88 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        frameCompras = new javax.swing.JInternalFrame();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel76 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        frameCompradores = new javax.swing.JInternalFrame();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        frameProdutos = new javax.swing.JInternalFrame();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel46 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(192, 27, 54));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/closing.png"))); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, -1, 40));

        jLabel17.setBackground(new java.awt.Color(42, 39, 41));
        jLabel17.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("Gestão Financeira - Tradição Mineira");
        jPanel11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 400, 40));

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 720, 40));

        jPanel4.setBackground(new java.awt.Color(42, 39, 41));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(42, 39, 41));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/007.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel2.setBackground(new java.awt.Color(192, 27, 54));
        jLabel2.setOpaque(true);
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Minha Conta");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 180, 50));

        jPanel5.setBackground(new java.awt.Color(42, 39, 41));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delivery.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Vendas");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel5.setBackground(new java.awt.Color(192, 27, 54));
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 180, 50));

        jPanel6.setBackground(new java.awt.Color(42, 39, 41));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/test2.png"))); // NOI18N
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Compras");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel8.setBackground(new java.awt.Color(192, 27, 54));
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 180, 50));

        jPanel9.setBackground(new java.awt.Color(42, 39, 41));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/test.png"))); // NOI18N
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Clientes");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel11.setBackground(new java.awt.Color(192, 27, 54));
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 180, 50));

        jPanel7.setBackground(new java.awt.Color(192, 27, 54));

        jLabel36.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel36.setText("Copyright © 2020 LionDev");
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addGap(19, 19, 19))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 180, 40));

        jPanel24.setBackground(new java.awt.Color(42, 39, 41));
        jPanel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel24MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel24MouseExited(evt);
            }
        });
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/004.png"))); // NOI18N
        jPanel24.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(204, 204, 204));
        jLabel49.setText("Produtos");
        jPanel24.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel50.setBackground(new java.awt.Color(192, 27, 54));
        jPanel24.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel4.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 180, 50));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        frameAccount.setVisible(true);
        frameAccount.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(226, 226, 226));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel24.setText("Detalhes da Conta");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 390, 50));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 400, -1));

        jLabel27.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel27.setText("Capital da empresa: " + stringFormatada(account.getCapital()) + " R$");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 640, 60));

        jLabel28.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel28.setText("Entrou: " + stringFormatada(account.getMoneyIn()) + " R$");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 640, 60));

        jLabel29.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(192, 27, 54));
        jLabel29.setText("Entradas em dinheiro: " + stringFormatada(account.getMoneyTypeIn()) + " R$");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 400, 50));

        jLabel84.setBackground(new java.awt.Color(192, 27, 54));
        jLabel84.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(192, 27, 54));
        jLabel84.setText("Entradas em débito: " + stringFormatada(account.getDebitoIn()) + " R$");
        jPanel1.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 400, 50));

        jLabel85.setBackground(new java.awt.Color(192, 27, 54));
        jLabel85.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(192, 27, 54));
        jLabel85.setText("Entradas em crédito: " + stringFormatada(account.getCreditoIn()) + " R$");
        jPanel1.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 400, 50));

        jLabel86.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel86.setText("Saiu: " + stringFormatada(account.getMoneyOut()) + " R$");
        jPanel1.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 640, 60));

        jLabel21.setFont(new java.awt.Font("Monospaced", 1, 11)); // NOI18N
        jLabel21.setText("Dados referentess ao:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Semana", "Mês", "Ano" , "Todos"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 80, -1));

        jPanel15.setBackground(new java.awt.Color(192, 27, 54));

        jList1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jScrollPane5.setViewportView(jList1);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 320, 170));

        jLabel87.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel87.setText("Lucro individuais dos produtos:");
        jPanel1.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 310, -1));

        frameAccount.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 730, 520));

        jDesktopPane1.add(frameAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -30, 750, 550));

        frameVendas.setVisible(false);
        frameVendas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane2.setOpaque(false);

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(51, 51, 51));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COMPRADOR", "VALOR", "PAGAMENTO", "QUANTIDADE", "DATA", "PRODUTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setFocusable(false);
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jTable2.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(192, 27, 54));
        jTable2.setShowHorizontalLines(false);
        jTable2.setShowVerticalLines(false);
        jTable2.setSurrendersFocusOnKeystroke(true);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        frameVendas.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 720, 320));

        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(244, 67, 54));
        jPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel26MouseExited(evt);
            }
        });
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("REMOVER");
        jLabel52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel26.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel25.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 180, 40));

        jPanel27.setBackground(new java.awt.Color(99, 187, 126));
        jPanel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel27MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel27MouseExited(evt);
            }
        });
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("ADICIONAR");
        jLabel53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel27.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 110, 40));

        jPanel25.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 180, 40));

        jPanel28.setBackground(new java.awt.Color(224, 171, 70));
        jPanel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel28MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel28MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel28MouseExited(evt);
            }
        });
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel54.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("ALTERAR");
        jLabel54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel28.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel25.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 180, 40));

        jLabel55.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(192, 27, 54));
        jLabel55.setText("Preço:");
        jPanel25.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 60, -1));

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel25.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 140, 20));

        jLabel57.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(192, 27, 54));
        jLabel57.setText("Comprador:");
        jPanel25.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, -1));

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(192, 27, 54));
        jLabel58.setText("Produto:");
        jPanel25.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 70, -1));

        jLabel59.setBackground(new java.awt.Color(255, 255, 255));
        jLabel59.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel25.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 140, 20));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 110, 20));

        jLabel60.setBackground(new java.awt.Color(255, 255, 255));
        jLabel60.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel25.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 140, 20));

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 140, 20));

        jLabel61.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(192, 27, 54));
        jLabel61.setText("Tipo:");
        jPanel25.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 90, -1));

        jLabel62.setBackground(new java.awt.Color(255, 255, 255));
        jLabel62.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel25.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 140, 20));

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 100, 20));

        jLabel63.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(192, 27, 54));
        jLabel63.setText("Quantidade:");
        jPanel25.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 90, -1));

        jLabel64.setBackground(new java.awt.Color(255, 255, 255));
        jLabel64.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel25.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 140, 20));

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 150, 20));

        jLabel65.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(192, 27, 54));
        jLabel65.setText("Data:");
        jPanel25.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 60, -1));

        jLabel66.setBackground(new java.awt.Color(255, 255, 255));
        jLabel66.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel25.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 140, 20));

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 210, 20));

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 140, 20));

        jLabel88.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel88.setText("Painel de Vendas");
        jPanel25.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 470, 50));

        jSeparator25.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 400, 10));

        frameVendas.getContentPane().add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 190));

        jDesktopPane1.add(frameVendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -30, 750, 550));

        frameCompras.setVisible(false);
        frameCompras.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(219, 218, 218));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane3.setOpaque(false);

        jTable3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable3.setForeground(new java.awt.Color(51, 51, 51));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME DO ITEM", "VALOR", "QUANTIDADE", "PAGAMENTO", "DATA", "PROPRIETARIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable3.setFocusable(false);
        jTable3.setGridColor(new java.awt.Color(0, 0, 0));
        jTable3.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTable3.setRowHeight(25);
        jTable3.setSelectionBackground(new java.awt.Color(192, 27, 54));
        jTable3.setShowHorizontalLines(false);
        jTable3.setShowVerticalLines(false);
        jTable3.setSurrendersFocusOnKeystroke(true);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel16.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 720, 320));

        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel67.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel67.setText("Painel de Compras");
        jPanel29.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 470, 50));

        jPanel30.setBackground(new java.awt.Color(244, 67, 54));
        jPanel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel30MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel30MouseExited(evt);
            }
        });
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("REMOVER");
        jLabel68.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel30.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel29.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 180, 40));

        jPanel31.setBackground(new java.awt.Color(99, 187, 126));
        jPanel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel31MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel31MouseExited(evt);
            }
        });
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel69.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("ADICIONAR");
        jLabel69.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel31.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 110, 40));

        jPanel29.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 180, 40));

        jPanel32.setBackground(new java.awt.Color(224, 171, 70));
        jPanel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel32MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel32MouseExited(evt);
            }
        });
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel70.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("ALTERAR");
        jLabel70.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel32.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel29.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 180, 40));

        jLabel71.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(192, 27, 54));
        jLabel71.setText("Preço:");
        jPanel29.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 60, -1));

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel29.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 140, 20));

        jLabel73.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(192, 27, 54));
        jLabel73.setText("Onde foi:");
        jPanel29.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, -1));

        jLabel74.setBackground(new java.awt.Color(255, 255, 255));
        jLabel74.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(192, 27, 54));
        jLabel74.setText("Nome:");
        jPanel29.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 70, -1));

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel29.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 140, 20));

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 400, 10));

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 110, 20));

        jLabel76.setBackground(new java.awt.Color(255, 255, 255));
        jLabel76.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel29.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 140, 20));

        jSeparator19.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 140, 20));

        jLabel77.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(192, 27, 54));
        jLabel77.setText("Tipo:");
        jPanel29.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 90, -1));

        jLabel78.setBackground(new java.awt.Color(255, 255, 255));
        jLabel78.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel29.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 140, 20));

        jSeparator20.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 100, 20));

        jLabel79.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(192, 27, 54));
        jLabel79.setText("Quantidade:");
        jPanel29.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 90, -1));

        jLabel80.setBackground(new java.awt.Color(255, 255, 255));
        jLabel80.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel29.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 140, 20));

        jSeparator21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 150, 20));

        jLabel81.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(192, 27, 54));
        jLabel81.setText("Data:");
        jPanel29.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 60, -1));

        jLabel82.setBackground(new java.awt.Color(255, 255, 255));
        jLabel82.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel29.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 140, 20));

        jSeparator22.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 210, 20));

        jSeparator23.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 140, 20));

        jPanel16.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 190));

        frameCompras.getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 730, 520));

        jLabel22.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel22.setText("Painel De Vendas");
        frameCompras.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 50));

        jDesktopPane1.add(frameCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -30, 750, 550));

        frameCompradores.setVisible(false);
        frameCompradores.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(219, 218, 218));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel25.setText("Painel de Clientes");
        jPanel14.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 470, 50));

        jPanel2.setBackground(new java.awt.Color(244, 67, 54));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("REMOVER");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel14.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 180, 40));

        jPanel18.setBackground(new java.awt.Color(99, 187, 126));
        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel18MouseExited(evt);
            }
        });
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("ADICIONAR");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel18.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 110, 40));

        jPanel14.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 180, 40));

        jPanel10.setBackground(new java.awt.Color(224, 171, 70));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("ALTERAR");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel14.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 180, 40));

        jLabel20.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(192, 27, 54));
        jLabel20.setText("Cnpj:");
        jPanel14.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 60, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel14.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 140, -1));

        jLabel32.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(192, 27, 54));
        jLabel32.setText("Nome:");
        jPanel14.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 60, -1));

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(192, 27, 54));
        jLabel33.setText("ID:");
        jPanel14.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 60, -1));

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel14.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 140, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 400, 10));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 140, 20));

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel14.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 140, -1));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 140, 20));

        jPanel12.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 190));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane1.setOpaque(false);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(51, 51, 51));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "CNPJ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(192, 27, 54));
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel12.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 720, 330));

        frameCompradores.getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 730, 520));

        jLabel19.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel19.setText("Painel De Vendas");
        frameCompradores.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 50));

        jDesktopPane1.add(frameCompradores, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -30, 750, 550));

        frameProdutos.setVisible(false);
        frameProdutos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(219, 218, 218));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel37.setText("Painel de Produtos");
        jPanel20.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 470, 50));

        jPanel21.setBackground(new java.awt.Color(244, 67, 54));
        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel21MouseExited(evt);
            }
        });
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("REMOVER");
        jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel21.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 180, 40));

        jPanel22.setBackground(new java.awt.Color(99, 187, 126));
        jPanel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel22MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel22MouseExited(evt);
            }
        });
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("ADICIONAR");
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel22.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 110, 40));

        jPanel20.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 180, 40));

        jPanel23.setBackground(new java.awt.Color(224, 171, 70));
        jPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel23MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel23MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel23MouseExited(evt);
            }
        });
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("ALTERAR");
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel23.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 40));

        jPanel20.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 180, 40));

        jLabel41.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(192, 27, 54));
        jLabel41.setText("Preço:");
        jPanel20.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 60, -1));

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel20.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 140, -1));

        jLabel43.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(192, 27, 54));
        jLabel43.setText("Nome:");
        jPanel20.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 60, -1));

        jLabel44.setBackground(new java.awt.Color(255, 255, 255));
        jLabel44.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(192, 27, 54));
        jLabel44.setText("ID:");
        jPanel20.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 60, -1));

        jLabel45.setBackground(new java.awt.Color(255, 255, 255));
        jLabel45.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel20.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 140, -1));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel20.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 400, 10));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel20.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 140, 20));

        jLabel46.setBackground(new java.awt.Color(255, 255, 255));
        jLabel46.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel20.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 140, -1));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel20.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 140, 20));

        jLabel83.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(192, 27, 54));
        jLabel83.setText("Lucro:");
        jPanel20.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 60, -1));

        jSeparator24.setBackground(new java.awt.Color(0, 0, 0));
        jPanel20.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 140, 20));

        jLabel18.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jPanel20.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 140, 20));

        jPanel19.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 190));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane4.setOpaque(false);

        jTable4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable4.setForeground(new java.awt.Color(51, 51, 51));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "PREÇO", "LUCRO PRODUÇÃO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable4.setFocusable(false);
        jTable4.setGridColor(new java.awt.Color(0, 0, 0));
        jTable4.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTable4.setRowHeight(25);
        jTable4.setSelectionBackground(new java.awt.Color(192, 27, 54));
        jTable4.setShowHorizontalLines(false);
        jTable4.setShowVerticalLines(false);
        jTable4.setSurrendersFocusOnKeystroke(true);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel19.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 720, 330));

        frameProdutos.getContentPane().add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 730, 520));

        jLabel47.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel47.setText("Painel De Vendas");
        frameProdutos.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 50));

        jDesktopPane1.add(frameProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -30, 750, 550));

        getContentPane().add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 720, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        frameAccount.setVisible(true);
        frameCompradores.setVisible(false);
        frameCompras.setVisible(false);
        frameVendas.setVisible(false);
        frameProdutos.setVisible(false);
        
        jLabel2.setOpaque(true);
        jLabel5.setOpaque(false);
        jLabel8.setOpaque(false);
        jLabel11.setOpaque(false);
        jLabel50.setOpaque(false);
        
        Util.setValues(account, paymentDAO, shoppingDAO, jComboBox1.getSelectedItem().toString());
        setLabelsAccount();
        setListAccount(jComboBox1.getSelectedItem().toString());

        jPanel4.repaint();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        jPanel3.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        jPanel3.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        frameAccount.setVisible(false);
        frameCompradores.setVisible(false);
        frameCompras.setVisible(false);
        frameVendas.setVisible(true);
        frameProdutos.setVisible(false);
        
        jLabel2.setOpaque(false);
        jLabel5.setOpaque(true);
        jLabel8.setOpaque(false);
        jLabel11.setOpaque(false);
        jLabel50.setOpaque(false);
        jPanel4.repaint();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        jPanel5.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        jPanel5.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        jPanel6.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jPanel6MouseExited

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        frameAccount.setVisible(false);
        frameCompradores.setVisible(false);
        frameCompras.setVisible(true);
        frameVendas.setVisible(false);
        frameProdutos.setVisible(false);
        
        jLabel2.setOpaque(false);
        jLabel5.setOpaque(false);
        jLabel8.setOpaque(true);
        jLabel11.setOpaque(false);
        jLabel50.setOpaque(false);
        
        jPanel4.repaint();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        frameAccount.setVisible(false);
        frameCompradores.setVisible(true);
        frameCompras.setVisible(false);
        frameVendas.setVisible(false);
        frameProdutos.setVisible(false);
                
        jLabel2.setOpaque(false);
        jLabel5.setOpaque(false);
        jLabel8.setOpaque(false);
        jLabel11.setOpaque(true);
        jLabel50.setOpaque(false);
        jPanel4.repaint();
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        jPanel9.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        jPanel9.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseEntered
        jPanel18.setBackground(Color.decode("#63ff7e"));
    }//GEN-LAST:event_jPanel18MouseEntered

    private void jPanel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseExited
        jPanel18.setBackground(Color.decode("#63bb7e"));
    }//GEN-LAST:event_jPanel18MouseExited

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
       jPanel10.setBackground(Color.decode("#e0ff46"));
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        jPanel10.setBackground(Color.decode("#e0ab46"));
    }//GEN-LAST:event_jPanel10MouseExited

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        jPanel2.setBackground(Color.decode("#c01b36"));
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
         jPanel2.setBackground(Color.decode("#f44336"));
    }//GEN-LAST:event_jPanel2MouseExited

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
       framePopupRemoveBuyer = new JFrame();
        
       PopupRemoveBuyer popup = new PopupRemoveBuyer();
       
       if (jTable1.getSelectedRowCount() == 0) {
           JOptionPane.showMessageDialog(null, "Nenhum dado da tabela foi selecionado.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else if (jTable1.getSelectedRowCount() > 1) {
           JOptionPane.showMessageDialog(null, "Selecione apenas um produto para remover.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else {
           popup.initComponentsPopup(framePopupRemoveBuyer, jTable1, listBuyers, modeloTableBuyers, modeloTablePayments, listPayments, paymentDAO);
           framePopupRemoveBuyer.setLocationRelativeTo(null);
           framePopupRemoveBuyer.setVisible(true);
       }
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          Object id = jTable1.getValueAt(jTable1.getSelectedRow(),0);  
          Object name = jTable1.getValueAt(jTable1.getSelectedRow(),1);
          Object cnpj = jTable1.getValueAt(jTable1.getSelectedRow(),2);
          jLabel34.setText(id.toString());
          jLabel35.setText(name.toString());
          jLabel31.setText(cnpj.toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        framePopupAddBuyer = new JFrame();
        
        PopupAddBuyer popup = new PopupAddBuyer();
        
        popup.initComponentsPopup(framePopupAddBuyer, modeloTableBuyers, listBuyers);
        framePopupAddBuyer.setLocationRelativeTo(null);
        framePopupAddBuyer.setVisible(true);
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
       framePopupAlterBuyer = new JFrame();
        
       PopupAlterBuyer popup = new PopupAlterBuyer();
         
       if (jTable1.getSelectedRowCount() == 0 || jTable1.getSelectedRowCount() > 1) {
           JOptionPane.showMessageDialog(null, "Somente um dado na tabela pode ser alterado por vez!.", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
       } else {
           
           Object id = jTable1.getValueAt(jTable1.getSelectedRow(),0);
           Object name = jTable1.getValueAt(jTable1.getSelectedRow(),1);
           Object cnpj = jTable1.getValueAt(jTable1.getSelectedRow(),2);
           
           String string = id.toString();
           int buyerId = Integer.parseInt(string);
           
           popup.initComponentsPopup(framePopupAlterBuyer, jTable1, listBuyers, modeloTableBuyers, name.toString(), cnpj.toString(), buyerId);
           framePopupAlterBuyer.setLocationRelativeTo(null);
           framePopupAlterBuyer.setVisible(true);
       }
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
       framePopupRemoveProduct = new JFrame();
        
       PopupRemoveProduct popup = new PopupRemoveProduct();
       
       if (jTable4.getSelectedRowCount() == 0) {
           JOptionPane.showMessageDialog(null, "Nenhum dado da tabela foi selecionado.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else if (jTable4.getSelectedRowCount() > 1) {
           JOptionPane.showMessageDialog(null, "Selecione apenas um produto para remover.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else {
           popup.initComponentsPopup(framePopupRemoveProduct, jTable4, listProducts, modeloTableProducts, modeloTablePayments, listPayments, paymentDAO);
           framePopupRemoveProduct.setLocationRelativeTo(null);
           framePopupRemoveProduct.setVisible(true);
       }
    }//GEN-LAST:event_jPanel21MouseClicked

    private void jPanel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseEntered
        jPanel21.setBackground(Color.decode("#c01b36"));
    }//GEN-LAST:event_jPanel21MouseEntered

    private void jPanel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseExited
        jPanel21.setBackground(Color.decode("#f44336"));
    }//GEN-LAST:event_jPanel21MouseExited

    private void jPanel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseClicked
        framePopupAddProduct = new JFrame();
        
        PopupAddProduct popup = new PopupAddProduct();
        
        popup.initComponentsPopup(framePopupAddProduct, modeloTableProducts, listProducts);
        framePopupAddProduct.setLocationRelativeTo(null);
        framePopupAddProduct.setVisible(true);
    }//GEN-LAST:event_jPanel22MouseClicked

    private void jPanel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseEntered
        jPanel22.setBackground(Color.decode("#63ff7e"));
    }//GEN-LAST:event_jPanel22MouseEntered

    private void jPanel22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseExited
        jPanel22.setBackground(Color.decode("#63bb7e"));
    }//GEN-LAST:event_jPanel22MouseExited

    private void jPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseClicked
       framePopupAlterProduct = new JFrame();
        
       PopupAlterProduct popup = new PopupAlterProduct();
         
       if (jTable4.getSelectedRowCount() == 0 || jTable4.getSelectedRowCount() > 1) {
           JOptionPane.showMessageDialog(null, "Somente um dado na tabela pode ser alterado por vez!.", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
       } else {
           Object id = jTable4.getValueAt(jTable4.getSelectedRow(),0);
           Object name = jTable4.getValueAt(jTable4.getSelectedRow(),1);
           Object price = jTable4.getValueAt(jTable4.getSelectedRow(),2);
           Object lucro = jTable4.getValueAt(jTable4.getSelectedRow(),3);
           
           int productId = Integer.parseInt(id.toString());
           
           popup.initComponentsPopup(framePopupAlterProduct, jTable4, listProducts, modeloTableProducts, name.toString(), price.toString(), lucro.toString(), productId);
           framePopupAlterProduct.setLocationRelativeTo(null);
           framePopupAlterProduct.setVisible(true);
       }
    }//GEN-LAST:event_jPanel23MouseClicked

    private void jPanel23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseEntered
       jPanel23.setBackground(Color.decode("#e0ff46"));
    }//GEN-LAST:event_jPanel23MouseEntered

    private void jPanel23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseExited
        jPanel23.setBackground(Color.decode("#e0ab46"));
    }//GEN-LAST:event_jPanel23MouseExited

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
          Object id = jTable4.getValueAt(jTable4.getSelectedRow(),0);  
          Object name = jTable4.getValueAt(jTable4.getSelectedRow(),1);
          Object price = jTable4.getValueAt(jTable4.getSelectedRow(),2);
          Object profit = jTable4.getValueAt(jTable4.getSelectedRow(), 3);
          
          jLabel45.setText(id.toString());
          jLabel46.setText(name.toString());
          jLabel42.setText(price.toString() + " R$");
          jLabel18.setText(profit.toString() + " R$");
    }//GEN-LAST:event_jTable4MouseClicked

    private void jPanel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseClicked
        frameAccount.setVisible(false);
        frameCompradores.setVisible(false);
        frameCompras.setVisible(false);
        frameVendas.setVisible(false);
        frameProdutos.setVisible(true);
        
        jLabel2.setOpaque(false);
        jLabel5.setOpaque(false);
        jLabel8.setOpaque(false);
        jLabel11.setOpaque(false);
        jLabel50.setOpaque(true);
        jPanel4.repaint();
    }//GEN-LAST:event_jPanel24MouseClicked

    private void jPanel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseEntered
        jPanel24.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jPanel24MouseEntered

    private void jPanel24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseExited
        jPanel24.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jPanel24MouseExited

    private void jPanel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel26MouseClicked
       framePopupRemovePayment = new JFrame();
        
       PopupRemovePayment popup = new PopupRemovePayment();
       
       if (jTable2.getSelectedRowCount() == 0) {
           JOptionPane.showMessageDialog(null, "Nenhum dado da tabela foi selecionado.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else if (jTable2.getSelectedRowCount() > 1) {
           JOptionPane.showMessageDialog(null, "Selecione apenas uma venda para remover.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else {
           popup.initComponentsPopup(framePopupRemovePayment, jTable2, listPayments, modeloTablePayments);
           framePopupRemovePayment.setLocationRelativeTo(null);
           framePopupRemovePayment.setVisible(true);
       }
    }//GEN-LAST:event_jPanel26MouseClicked

    private void jPanel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel26MouseEntered
        jPanel26.setBackground(Color.decode("#c01b36"));
    }//GEN-LAST:event_jPanel26MouseEntered

    private void jPanel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel26MouseExited
        jPanel26.setBackground(Color.decode("#f44336"));
    }//GEN-LAST:event_jPanel26MouseExited

    private void jPanel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MouseClicked
        framePopupAddPayment = new JFrame();
        
        PopupAddPayment popup = new PopupAddPayment();
        
        popup.initComponentsPopup(framePopupAddPayment, modeloTablePayments, listPayments, listProducts);
        framePopupAddPayment.setLocationRelativeTo(null);
        framePopupAddPayment.setVisible(true);
    }//GEN-LAST:event_jPanel27MouseClicked

    private void jPanel27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MouseEntered
        jPanel27.setBackground(Color.decode("#63ff7e"));
    }//GEN-LAST:event_jPanel27MouseEntered

    private void jPanel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MouseExited
        jPanel27.setBackground(Color.decode("#63bb7e"));
    }//GEN-LAST:event_jPanel27MouseExited

    private void jPanel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MouseClicked
        JOptionPane.showMessageDialog(null, "Alteração indísponível. Contate o desenvolvedor!", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jPanel28MouseClicked

    private void jPanel28MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MouseEntered
       jPanel28.setBackground(Color.decode("#e0ff46"));
    }//GEN-LAST:event_jPanel28MouseEntered

    private void jPanel28MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MouseExited
       jPanel28.setBackground(Color.decode("#e0ab46"));
    }//GEN-LAST:event_jPanel28MouseExited

    private void jPanel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseClicked
       framePopupRemoveShopping = new JFrame();
        
       PopupRemoveShopping popup = new PopupRemoveShopping();
       
       if (jTable3.getSelectedRowCount() == 0) {
           JOptionPane.showMessageDialog(null, "Nenhum dado da tabela foi selecionado.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else if (jTable3.getSelectedRowCount() > 1) {
           JOptionPane.showMessageDialog(null, "Selecione apenas uma venda para remover.", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
       } else {
           popup.initComponentsPopup(framePopupRemoveShopping, jTable3, listShopping, modeloTableShopping);
           framePopupRemoveShopping.setLocationRelativeTo(null);
           framePopupRemoveShopping.setVisible(true);
       }
    }//GEN-LAST:event_jPanel30MouseClicked

    private void jPanel30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseEntered
        jPanel30.setBackground(Color.decode("#c01b36"));
    }//GEN-LAST:event_jPanel30MouseEntered

    private void jPanel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseExited
        jPanel30.setBackground(Color.decode("#f44336"));
    }//GEN-LAST:event_jPanel30MouseExited

    private void jPanel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseClicked
        framePopupAddShopping = new JFrame();
        
        PopupAddShopping popup = new PopupAddShopping();
        
        popup.initComponentsPopup(framePopupAddShopping, modeloTableShopping, listShopping, account, paymentDAO);
        framePopupAddShopping.setLocationRelativeTo(null);
        framePopupAddShopping.setVisible(true);
    }//GEN-LAST:event_jPanel31MouseClicked

    private void jPanel31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseEntered
        jPanel31.setBackground(Color.decode("#63ff7e"));
    }//GEN-LAST:event_jPanel31MouseEntered

    private void jPanel31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseExited
        jPanel31.setBackground(Color.decode("#63bb7e"));
    }//GEN-LAST:event_jPanel31MouseExited

    private void jPanel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseClicked
        JOptionPane.showMessageDialog(null, "Alteração indísponível. Contate o desenvolvedor!", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jPanel32MouseClicked

    private void jPanel32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseEntered
        jPanel32.setBackground(Color.decode("#e0ff46"));
    }//GEN-LAST:event_jPanel32MouseEntered

    private void jPanel32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseExited
        jPanel32.setBackground(Color.decode("#e0ab46"));
    }//GEN-LAST:event_jPanel32MouseExited

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
          Object productName = jTable2.getValueAt(jTable2.getSelectedRow(),6);
          Object buyerName = jTable2.getValueAt(jTable2.getSelectedRow(),1);
          Object value = jTable2.getValueAt(jTable2.getSelectedRow(),2);
          Object paymentType = jTable2.getValueAt(jTable2.getSelectedRow(),3);
          Object qtde = jTable2.getValueAt(jTable2.getSelectedRow(),4);
          Object date = jTable2.getValueAt(jTable2.getSelectedRow(),5);
          
          jLabel59.setText(productName.toString());
          jLabel56.setText(buyerName.toString());
          jLabel60.setText(value.toString());
          jLabel62.setText(paymentType.toString());
          jLabel64.setText(qtde.toString());
          jLabel66.setText(date.toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
          Object productName = jTable3.getValueAt(jTable3.getSelectedRow(),1);
          Object ownerName = jTable3.getValueAt(jTable3.getSelectedRow(),6);
          Object value = jTable3.getValueAt(jTable3.getSelectedRow(),2);
          Object paymentType = jTable3.getValueAt(jTable3.getSelectedRow(),3);
          Object qtde = jTable3.getValueAt(jTable3.getSelectedRow(),4);
          Object date = jTable3.getValueAt(jTable3.getSelectedRow(),5);
          
          jLabel75.setText(productName.toString());
          jLabel72.setText(ownerName.toString());
          jLabel76.setText(value.toString());
          jLabel78.setText(paymentType.toString());
          jLabel80.setText(qtde.toString());
          jLabel82.setText(date.toString());
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        switch (jComboBox1.getSelectedItem().toString()) {
            case "Dia":
                Util.setValues(account, paymentDAO, shoppingDAO, "Dia");
                setLabelsAccount();
                setListAccount("Dia");
                break;
            case "Semana":
                Util.setValues(account, paymentDAO, shoppingDAO, "Semana");
                setLabelsAccount();
                setListAccount("Semana");
                break;
            case "Mês":
                Util.setValues(account, paymentDAO, shoppingDAO, "Mês");
                setLabelsAccount();
                setListAccount("Mês");
                break;
            case "Ano":
                Util.setValues(account, paymentDAO, shoppingDAO, "Ano");
                setLabelsAccount();
                setListAccount("Ano");
                break;
            default:
                Util.setValues(account, paymentDAO, shoppingDAO, "Todos");
                setLabelsAccount();
                setListAccount("Todos");
                break;
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame frameAccount;
    private javax.swing.JInternalFrame frameCompradores;
    private javax.swing.JInternalFrame frameCompras;
    private javax.swing.JInternalFrame frameProdutos;
    private javax.swing.JInternalFrame frameVendas;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private static javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
