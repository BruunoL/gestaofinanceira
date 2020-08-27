package dev.bruno.gestaofinanceira.entity;

public class Account {
    
    private double capital;
    private double moneyIn;
    private double moneyOut;
    private double debitoIn;
    private double creditoIn;
    private double moneyTypeIn;

    public double getMoneyTypeIn() {
        return moneyTypeIn;
    }

    public void setMoneyTypeIn(double moneyTypeIn) {
        this.moneyTypeIn = moneyTypeIn;
    }

    public double getDebitoIn() {
        return debitoIn;
    }

    public void setDebitoIn(double debitoIn) {
        this.debitoIn = debitoIn;
    }

    public double getCreditoIn() {
        return creditoIn;
    }

    public void setCreditoIn(double creditoIn) {
        this.creditoIn = creditoIn;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getMoneyIn() {
        return moneyIn;
    }

    public void setMoneyIn(double moneyIn) {
        this.moneyIn = moneyIn;
    }

    public double getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(double moneyOut) {
        this.moneyOut = moneyOut;
    }
}