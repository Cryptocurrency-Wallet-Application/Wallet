package com.example.myapplication;

public class Wallet {
    private int walletID;
    private int walletKey;
    private String walletName;
    private double Bitcoin;
    private double Etherium;
    private double TL;
    private double Euro;
    private double Dollar;


    public Wallet(String walletName) {
        this.walletName = walletName;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public int getWalletKey() {
        return walletKey;
    }

    public void setWalletKey(int walletKey) {
        this.walletKey = walletKey;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

}
