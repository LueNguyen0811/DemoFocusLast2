package com.example.demofocuslast.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.demofocuslast.R;

public class SignUpFragment extends Fragment {
    ImageButton btnBack;
    EditText edtName,edtMail,edtUser,edtPass;
    TextView txtLoginCreate;
    Button btnNext;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        final LoginFragment loginFragment = new LoginFragment();
        final SignUp2stFragment st2SignUpFragment = new SignUp2stFragment();
        final SignInFragment signInFragment = new SignInFragment();
        //mapping
        btnBack = view.findViewById(R.id.btnBack);
        btnNext = view.findViewById(R.id.btnNext);
        edtName = view.findViewById(R.id.edtName);
        edtMail = view.findViewById(R.id.edtCreatMail);
        edtPass = view.findViewById(R.id.edtCreatPass);
        edtUser = view.findViewById(R.id.edtCreatUserName);
        txtLoginCreate = view.findViewById(R.id.txtLoginCreate);

        //set event
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right)
                        .addToBackStack(null)
                        .replace(R.id.frame_layout,loginFragment).commit();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
                    return;
                }
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.layoutLogin,st2SignUpFragment).commit();

            }
        });
        txtLoginCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right)
                        .addToBackStack(null)
                        .replace(R.id.frame_layout,signInFragment).commit();
            }
        });

        return view;
    }

    private boolean validatePassword() {
        String val = edtPass.getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                //"(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            edtPass.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            edtPass.setError("Password should contain 4 characters!");
            return false;
        } else {
            edtPass.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = edtMail.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            edtMail.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            edtMail.setError("Invalid Email!");
            return false;
        } else {
            edtMail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
            String val = edtUser.getText().toString().trim();
            String checkspaces = "(?=S+$)";

            if (val.isEmpty()) {
                edtUser.setError("Field can not be empty");
                return false;
            } else if (val.length() > 20) {
                edtUser.setError("Username is too large!");
                return false;
            } else if (val.matches(checkspaces)) {
                edtUser.setError("No White spaces are allowed!");
                return false;
            } else {
                edtUser.setError(null);
                return true;
            }
        }

    private boolean validateFullName() {
        String val = edtName.getText().toString().trim();
        if (val.isEmpty()) {
            edtName.setError("Field can not be empty");
            return false;
        } else {
            edtName.setError(null);
            return true;
        }
    }
}