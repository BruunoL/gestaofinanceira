package dev.bruno.gestaofinanceira.entity;

import dev.bruno.gestaofinanceira.util.PaymentType;

public class Payment {

    private int id;
    private int buyerId;
    private double paymentValue;
    private PaymentType paymentType;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
    
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentType(int id) {
        switch (id) {
            case 1:
                this.paymentType = PaymentType.DINHEIRO;
                break;
            case 2:
                this.paymentType = PaymentType.CRÉDITO;
                break;
            case 3:
                this.paymentType = PaymentType.DÉBITO;
                break;
            default:
                this.paymentType = PaymentType.UNKNOWN;
                break;
        }
    }
    
    private int qtdeItens;

    public int getQtdeItens() {
        return qtdeItens;
    }

    public void setQtdeItens(int qtdeItens) {
        this.qtdeItens = qtdeItens;
    }
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int id) {
        this.buyerId = id;
    }

    public double getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(double payment_value) {
        this.paymentValue = payment_value;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int product_id) {
        this.productId = product_id;
    }
    
    public double calculePaymentWithTax(double productPrice, PaymentType payment) {
        double totalprice = productPrice * getQtdeItens();
        double finalPrice;
        if (payment == PaymentType.DINHEIRO) {
           finalPrice = totalprice * payment.getTax();
        } else {
           finalPrice = totalprice - (totalprice * payment.getTax());
        }

        
        return finalPrice;
    }
}