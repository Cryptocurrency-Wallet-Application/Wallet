package com.example.myapplication;

public class Wallet {

    private String walletAddress;
    private double Ethereum;
    private double Tether;
    private double Link;
    private double TL;
    private double Euro;
    private double Dollar;

    public Wallet(String walletAddress, double ethereum, double tether, double link, double TL, double euro, double dollar) {
        this.walletAddress = walletAddress;
        this.Ethereum = ethereum;
        this.Tether = tether;
        this.Link = link;
        this.TL = TL;
        this.Euro = euro;
        this.Dollar = dollar;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public double getEthereum() {
        return Ethereum;
    }

    public void setEthereum(double ethereum) {
        Ethereum = ethereum;
    }

    public double getTether() {
        return Tether;
    }

    public void setTether(double tether) {
        Tether = tether;
    }

    public double getLink() {
        return Link;
    }

    public void setLink(double link) {
        Link = link;
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
