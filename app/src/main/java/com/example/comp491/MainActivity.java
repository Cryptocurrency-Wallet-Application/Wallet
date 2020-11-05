package com.example.comp491;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            MainPageFragment mainPageFragment = new MainPageFragment();

            fragmentTransaction.replace(R.id.fragment_container, mainPageFragment);

            fragmentTransaction.addToBackStack("mainPage");

            fragmentTransaction.commit();
        }
    }

    public void alert(final String title, final String message, final boolean isFragmentPop) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title).setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (isFragmentPop) {
                                    FragmentManager fm = getSupportFragmentManager();
                                    for (int i = 0; i < fm.getBackStackEntryCount() - 1; ++i) {
                                        fm.popBackStack();
                                    }
                                }
                            }
                        }).create().show();
            }
        });
    }

}
