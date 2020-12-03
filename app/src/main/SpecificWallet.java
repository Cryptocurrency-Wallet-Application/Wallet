package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SpecificWallet extends AppCompatActivity {

    private String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_wallet);
        this.address = this.getIntent().getStringExtra("address");

    }

    public void sendTransaction(String receiverAdress){
        try{
            //Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
            //TransactionReceipt receipt = Transfer.sendFunds(web3j,credentials, receiverAdress, new BigDecimal(1), Convert.Unit.ETHER).sendAsync().get();
            //toastAsync("Transaction complete: " +receipt.getTransactionHash());
        }
        catch (Exception e){
            toastAsync(e.getMessage());
        }
    }

    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}