package com.example.myapplication;

public class Wallet {

    private String walletName;
    private int walletAddress;
    private double Bitcoin;
    private double Ethereum;
    private double TL;
    private double Euro;
    private double Dollar;


    public Wallet(String walletName, double bitcoin, double ethereum, double TL, double euro, double dollar) {
        this.walletName = walletName;
        this.Bitcoin = bitcoin;
        this.Ethereum = ethereum;
        this.TL = TL;
        this.Euro = euro;
        this.Dollar = dollar;
        this.setWalletAddress();
    }

    public void transferBTC(Wallet w1, Wallet w2, double amount){
        if(w1.getBitcoin()-amount>=0){
            w1.setBitcoin(w1.getBitcoin()-amount);
            w2.setBitcoin(w2.getBitcoin()+amount);
        }
    }
    public void transferTL(Wallet w1, Wallet w2, double amount){
        if(w1.getTL()-amount>=0){
            w1.setTL(w1.getTL()-amount);
            w2.setTL(w2.getTL()+amount);
        }
    }
    public void transferEuro(Wallet w1, Wallet w2, double amount){
        if(w1.getEuro()-amount>=0){
            w1.setEuro(w1.getEuro()-amount);
            w2.setEuro(w2.getEuro()+amount);
        }
    }
    public void transferEthereum(Wallet w1, Wallet w2, double amount){
        if(w1.getEthereum()-amount>=0){
            w1.setEthereum(w1.getEthereum()-amount);
            w2.setEthereum(w2.getEthereum()+amount);
        }
    }
    public void transferDollar(Wallet w1, Wallet w2, double amount){
        if(w1.getDollar()-amount>=0){
            w1.setDollar(w1.getDollar()-amount);
            w2.setDollar(w2.getDollar()+amount);
        }
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public int getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(){
        this.walletAddress = this.walletName.hashCode() + this.getWalletName().hashCode();
    }


    public double getBitcoin() {
        return Bitcoin;
    }

    public void setBitcoin(double bitcoin) {
        Bitcoin = bitcoin;
    }

    public double getEthereum() {
        return Ethereum;
    }

    public void setEthereum(double ethereum) {
        Ethereum = ethereum;
    }

    public double getTL() {
        return TL;
    }

    public void setTL(double TL) {
        this.TL = TL;
    }

    public double getEuro() {
        return Euro;
    }

    public void setEuro(double euro) {
        Euro = euro;
    }

    public double getDollar() {
        return Dollar;
    }

    public void setDollar(double dollar) {
        Dollar = dollar;
    }

}
