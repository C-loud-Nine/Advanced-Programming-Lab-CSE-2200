package com.example.project101;

public class UserHelperClass {
    String name,balance,pin,cardno,branch;

    public UserHelperClass() {
    }
    public UserHelperClass(String name, String balance, String branch, String cardno, String pin) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.cardno = cardno;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
