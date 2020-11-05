package com.example.comp491;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainPageFragment extends Fragment {

    @BindView(R.id.userName)
    EditText userName;

    @BindView(R.id.password)
    EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.main_page, container, false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @OnClick(R.id.loginButton)
    void login() {

        if (userName.getText().equals("") || userName == null || password.getText().equals("") || password == null) { //add check username from system
            ((MainActivity)this.getActivity()).alert("Login", "Username or Password wrong.", false);
            return;
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuFragment menuFragment = new MenuFragment();

        fragmentTransaction.replace(R.id.fragment_container, menuFragment);

        fragmentTransaction.addToBackStack("MenuFragment");

        fragmentTransaction.commit();


    }

}