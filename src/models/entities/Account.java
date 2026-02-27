/*
* Fazer um programa para ler os dados de uma conta bancária e depois realizar um
saque nesta conta bancária, mostrando o novo saldo. Um saque não pode ocorrer
ou se não houver saldo na conta, ou se o valor do saque for superior ao limite de
saque da conta.
* */

package models.entities;

import models.exceptions.DomainException;

public class Account {
    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;

    public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getBalance() {
        return String.format("%.2f", balance);
    }

    public Double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void deposit(Double amount) {
        balance += amount;
    }

    public void withdraw(Double amount) {
        validateWithdraw(amount);
        balance -= amount;
    }

    private void validateWithdraw(Double amount) {
        if (amount > balance) {
            throw new DomainException("Withdraw error: Not enough balance!");
        }
        if (amount > withdrawLimit) {
            throw new DomainException("Withdraw error: The amount exceeds withdraw limit!");
        }
        if (amount <= 0) {
            throw new DomainException("Withdraw error: The amount is invalid(<=0)");
        }
    }
}

