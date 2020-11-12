package com.example.myapplication;

public class User{

    private String userName;
    private String password;
    private Wallet[] wallets;

    public User(String userName, String password, Wallet[] wallets) {
        this.userName = userName;
        this.password = password;
        this.wallets = wallets;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Wallet[] getWallets() {
        return wallets;
    }

    public void setWallets(Wallet[] wallets) {
        this.wallets = wallets;
    }


}
