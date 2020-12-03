package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public class AccountScreen extends AppCompatActivity {
    private String userName;
    private String password;
    private File walletDir;
    private Web3j web3j;
    private Button createWallet;
    private Button toWalletAddressesScreen;
    private ArrayList<String> userWalletAddresses = new ArrayList<String>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button logout;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);
        anyChartView = findViewById(R.id.any_chart_view);
        setupPieChart();
        String username = this.getIntent().getStringExtra("Username");
        this.password = this.getIntent().getStringExtra("password");
        this.setUserName(username);
        getUserWalletAddresses();
        //////////////////////////////////////////////
        setupBouncyCastle();
        testnetBlockchainConnectionTest();
        //////////////////////////////////////////////
        createWallet = (Button) findViewById(R.id.button3);
        createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWallet();
            }
        });
        toWalletAddressesScreen = (Button) findViewById(R.id.button11);
        toWalletAddressesScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWalletAddressesScreen();
            }
        });
        logout = findViewById(R.id.buttonLogout);
        firebaseAuth = FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                startActivity(new Intent(AccountScreen.this, MainActivity.class));
            }
        });
    }
    public void openWalletAddressesScreen(){
        Intent intent = new Intent(this, WalletAddressesScreen.class);
        intent.putExtra("Username", userName);
        intent.putExtra("password", password);
        intent.putStringArrayListExtra("walletAddresses", userWalletAddresses);
        startActivity(intent);
    }
    /////////////////////////////////////////////////
    public void getUserWalletAddresses(){
        db.collection("Users").document(userName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        ArrayList<String> addresses = (ArrayList<String>) document.get("walletAddresses");
                        for (String s : addresses){
                            userWalletAddresses.add(s);
                        }
                    }
                }
            }
        });
    }
    public void testnetBlockchainConnectionTest(){
        web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/37132f5960df4aca855ab542d74d9f91"));
        try {
            Web3ClientVersion clientVersion = web3j.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                toastAsync("Client connected to the Blockchain.");
            }
            else {
                toastAsync("Could not connect to the Blockchain.");
            }
        }
        catch (Exception e) {

        }
    }

    public void createWallet(){
        try{
            String walletDirectory = getFilesDir().getAbsolutePath();
            String walletName = WalletUtils.generateLightNewWalletFile(password, new File(walletDirectory));
            toastAsync("Wallet generated");
            ////// wallet generated
            Credentials credentials = WalletUtils.loadCredentials(password, walletDirectory + "/" + walletName);
            String walletAddress = credentials.getAddress();
            Wallet createdWallet = new Wallet(walletAddress,0,0,0,0,0,0);
            db.collection("Wallets").document(walletAddress).set(createdWallet);
            ///// wallet with address written to the database
            User tempUser = new User(userName, password);
            for(String a : userWalletAddresses){
                tempUser.addWalletAdress(a);
            }
            userWalletAddresses.add(walletAddress);
            tempUser.addWalletAdress(walletAddress);
            db.collection("Users").document(userName).set(tempUser);
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
    private void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            return;
        }
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }
    //////////////////////////////////////////////////
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
    AnyChartView anyChartView;
    String[] wallets = {"Wallet1", "Wallet2", "Wallet3", "Wallet4", "Wallet5"};
    int[] amount = {5, 5, 5, 5, 5};

    public void setupPieChart() {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i =0; i<wallets.length; i++) {
            dataEntries.add(new ValueDataEntry(wallets[i], amount[i]));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}