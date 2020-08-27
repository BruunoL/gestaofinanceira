/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.bruno.gestaofinanceira.util;

/**
 *
 * @author bruno
 */
public enum PaymentType {
    
    //Se um dia der algum erro fudido, olhar aqui primeiro lugar
    
    DINHEIRO(1, 1),
    CRÉDITO(2, 0.0499),
    DÉBITO(3, 0.0199),
    UNKNOWN(4, 1);
    
    private final int id;
    private final double tax;

    PaymentType(int id, double tax) {
        this.id = id;
        this.tax = tax;
    }
    
    public int getId() {
        return id;
    }
    
    public double getTax() {
        return tax;
    }
}