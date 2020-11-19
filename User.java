package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class User{

    private String userName;
    private String password;
    private List<Wallet> wallets = new ArrayList<Wallet>();
    private List<Integer> walletAddresses = new ArrayList<Integer>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void addWallet(Wallet w1){
        this.wallets.add(w1);
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

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

}
