/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.bruno.gestaofinanceira.util;

import dev.bruno.gestaofinanceira.entity.Account;
import dev.bruno.gestaofinanceira.entity.Buyer;
import dev.bruno.gestaofinanceira.entity.Payment;
import dev.bruno.gestaofinanceira.entity.Product;
import dev.bruno.gestaofinanceira.entity.Shopping;
import dev.bruno.gestaofinanceira.entity.dao.BuyerDAO;
import dev.bruno.gestaofinanceira.entity.dao.PaymentDAO;
import dev.bruno.gestaofinanceira.entity.dao.ProductDAO;
import dev.bruno.gestaofinanceira.entity.dao.ShoppingDAO;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class Util {
    
    public static void readTableProducts(DefaultTableModel modeloTableProducts, List<Product> listProducts, boolean attList) {
        if (attList) {
           listProducts = new ProductDAO().findAllProducts();
        }
        
        modeloTableProducts.setNumRows(0);
        for (int i = 0; i < listProducts.size(); i++) {
           modeloTableProducts.addRow(new Object[] {
                    listProducts.get(i).getId(),
                    listProducts.get(i).getName(),
                    stringFormatada(listProducts.get(i).getPrice()),
                    stringFormatada(listProducts.get(i).getProductionProfit())
            });
        }
    }
    
    public static void setValues(Account account, PaymentDAO paymentDAO, ShoppingDAO shoppingDAO, String searchType) {
        if (searchType.equals("Dia")) {
             account.setMoneyIn(paymentDAO.getSaldo("Dia"));
             account.setMoneyOut(shoppingDAO.getSaldo("Dia"));
             account.setCapital(account.getMoneyIn() - account.getMoneyOut());
        
             account.setDebitoIn(paymentDAO.getValuePaymentByType(PaymentType.DÉBITO.getId(), searchType));
             account.setCreditoIn(paymentDAO.getValuePaymentByType(PaymentType.CRÉDITO.getId(), searchType));
             account.setMoneyTypeIn(paymentDAO.getValuePaymentByType(PaymentType.DINHEIRO.getId(), searchType));
        } else if (searchType.equals("Semana")) {
             account.setMoneyIn(paymentDAO.getSaldo("Semana"));
             account.setMoneyOut(shoppingDAO.getSaldo("Semana"));
             account.setCapital(account.getMoneyIn() - account.getMoneyOut());
        
             account.setDebitoIn(paymentDAO.getValuePaymentByType(PaymentType.DÉBITO.getId(), searchType));
             account.setCreditoIn(paymentDAO.getValuePaymentByType(PaymentType.CRÉDITO.getId(), searchType));
             account.setMoneyTypeIn(paymentDAO.getValuePaymentByType(PaymentType.DINHEIRO.getId(), searchType));
        } else if (searchType.equals("Ano")) {
             account.setMoneyIn(paymentDAO.getSaldo("Ano"));
             account.setMoneyOut(shoppingDAO.getSaldo("Ano"));
             account.setCapital(account.getMoneyIn() - account.getMoneyOut());
        
             account.setDebitoIn(paymentDAO.getValuePaymentByType(PaymentType.DÉBITO.getId(), searchType));
             account.setCreditoIn(paymentDAO.getValuePaymentByType(PaymentType.CRÉDITO.getId(), searchType));
             account.setMoneyTypeIn(paymentDAO.getValuePaymentByType(PaymentType.DINHEIRO.getId(), searchType));
        } else if (searchType.equals("Mês")) {
             account.setMoneyIn(paymentDAO.getSaldo("Mês"));
             account.setMoneyOut(shoppingDAO.getSaldo("Mês"));
             account.setCapital(account.getMoneyIn() - account.getMoneyOut());
        
             account.setDebitoIn(paymentDAO.getValuePaymentByType(PaymentType.DÉBITO.getId(), searchType));
             account.setCreditoIn(paymentDAO.getValuePaymentByType(PaymentType.CRÉDITO.getId(), searchType));
             account.setMoneyTypeIn(paymentDAO.getValuePaymentByType(PaymentType.DINHEIRO.getId(), searchType));
        } else if (searchType.equals("Todos")) {
             account.setMoneyIn(paymentDAO.getSaldo("Todos"));
             account.setMoneyOut(shoppingDAO.getSaldo("Todos"));
             account.setCapital(account.getMoneyIn() - account.getMoneyOut());
        
             account.setDebitoIn(paymentDAO.getValuePaymentByType(PaymentType.DÉBITO.getId(), searchType));
             account.setCreditoIn(paymentDAO.getValuePaymentByType(PaymentType.CRÉDITO.getId(), searchType));
             account.setMoneyTypeIn(paymentDAO.getValuePaymentByType(PaymentType.DINHEIRO.getId(), searchType));
        }
    }
    
    public static void readTableBuyers(DefaultTableModel modeloTableBuyers, List<Buyer> listBuyers, boolean attList) {
        if (attList) {
            listBuyers = new BuyerDAO().findAllBuyers();
        }
        
        modeloTableBuyers.setNumRows(0);
        for (int i = 0; i < listBuyers.size(); i++) {
            modeloTableBuyers.addRow(new Object[] {
                        listBuyers.get(i).getId(),
                        listBuyers.get(i).getName(),
                        formatarCnpj(listBuyers.get(i).getCnpj())
            });
        }
    }
    
    public static void readTablePayments(PaymentDAO paymentDAO, DefaultTableModel modeloTablePayments, List<Payment> listPayments, boolean attList) {
        if (attList) {
            listPayments = paymentDAO.findAllPayments();
        } 
        
         modeloTablePayments.setNumRows(0);
         for (int i = 0; i < listPayments.size(); i++) {
            modeloTablePayments.addRow(new Object[] {
                    listPayments.get(i).getId(),
                    paymentDAO.getBuyerNameById(listPayments.get(i).getBuyerId()),
                    stringFormatada(listPayments.get(i).getPaymentValue()),
                    listPayments.get(i).getPaymentType(),
                    listPayments.get(i).getQtdeItens() + " unid",
                    listPayments.get(i).getDate(),
                    paymentDAO.getProductNameByID(listPayments.get(i).getProductId())
            });
        }
    }
    
    public static void readTableShoppings(DefaultTableModel modeloTableShopping, List<Shopping> listShopping, boolean attList) {
        if (attList) {
            listShopping = new ShoppingDAO().findAllShoppings();
        }
        
        modeloTableShopping.setNumRows(0);
        for (int i = 0; i < listShopping.size(); i++) {
            modeloTableShopping.addRow(new Object[] {
                    listShopping.get(i).getId(),
                    listShopping.get(i).getName(),
                    stringFormatada(listShopping.get(i).getValue()),
                    listShopping.get(i).getQtdeItens() + " unid",
                    listShopping.get(i).getPaymentType(),
                    listShopping.get(i).getDate(),
                    listShopping.get(i).getReceiverName()
            });
        }
    }
    
    public static String stringFormatada(double value) {
        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(value);
          
        return valorString;
    }
     
    public static String formatarCnpj(String string) {
        String valorString;
        switch (string.length()) {
            case 15:
                valorString = String.format(string, "xxx.xxx.xxx/xxxx-xx");
                break;
            case 11:
                valorString = String.format("xx.xxx.xxx-xx", string);
                break;
            default:
                valorString = string;
                break;
        }
        return valorString;
    }
}