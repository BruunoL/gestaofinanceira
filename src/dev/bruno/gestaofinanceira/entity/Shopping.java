package dev.bruno.gestaofinanceira.entity;

import dev.bruno.gestaofinanceira.util.PaymentType;

public class Shopping {
    
    private int id;
    private String name;
    private double value;
    private int qtde;
    private PaymentType paymentType;
    private String date;
    private String receiverName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public int getQtdeItens() {
        return qtde;
    }

    public void setQtdeIens(int qtde) {
        this.qtde = qtde;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}