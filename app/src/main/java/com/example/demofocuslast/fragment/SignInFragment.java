package com.example.demofocuslast.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.demofocuslast.R;

import org.w3c.dom.Text;

public class SignInFragment extends Fragment {
    ImageButton btnBack;
    TextView txtCreateAccount,txtForgetPassword;
    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        btnBack = view.findViewById(R.id.btnBack);
        txtCreateAccount = view.findViewById(R.id.txtCreateAccount);
        final LoginFragment loginFragment = new LoginFragment();
        final SignUpFragment signUpFragment = new SignUpFragment();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right)
                        .addToBackStack(null)
                        .replace(R.id.layoutLogin,loginFragment).commit();
            }
        });
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right)
                        .addToBackStack(null)
                        .replace(R.id.layoutLogin,signUpFragment).commit();
            }
        });
        return view;
    }
}