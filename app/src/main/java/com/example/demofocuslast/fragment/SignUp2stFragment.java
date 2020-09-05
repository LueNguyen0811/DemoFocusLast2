package com.example.demofocuslast.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demofocuslast.Interface.SendPhoneNumberListener;
import com.example.demofocuslast.Interface.SendTextListenner;
import com.example.demofocuslast.R;
import com.hbb20.CountryCodePicker;

public class SignUp2stFragment extends Fragment {
    String TAG = "interface";
    ImageButton btnBack;
    Button btnNext;
    TextView txtLogin;
    CountryCodePicker countryCodePicker;
    EditText edtCreatePhone;
    String _phoneNo = "";
    String _getUserEntteredPhoneNumBer = "";

    public SignUp2stFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_st2_sign_up, container, false);
        final LoginFragment loginFragment = new LoginFragment();
        final SignInFragment signInFragment = new SignInFragment();
        final DecodeFragment decodeFragment = new DecodeFragment();

        //mapping
        btnBack = view.findViewById(R.id.btnBackPhone);
        txtLogin = view.findViewById(R.id.txtLoginCreate);
        btnNext = view.findViewById(R.id.btnNext);
        edtCreatePhone = view.findViewById(R.id.edtCreatPhone);
        countryCodePicker = view.findViewById(R.id.country_code_picker);

        //Events
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right)
                        .addToBackStack(null)
                        .replace(R.id.layoutLogin,loginFragment).commit();
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right)
                        .addToBackStack(null)
                        .replace(R.id.layoutLogin,signInFragment).commit();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validatePhoneNumber()){
                    return;
                }
                _getUserEntteredPhoneNumBer = edtCreatePhone.getText().toString().trim();
                _phoneNo = "+" +countryCodePicker.getSelectedCountryCode() + _getUserEntteredPhoneNumBer;
                decodeFragment.phoneNumber = _phoneNo;
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right)
                        .addToBackStack(null)
                        .replace(R.id.layoutLogin,decodeFragment).commit();

            }
        });
        return view;
    }
    private boolean validatePhoneNumber() {
        String val = edtCreatePhone.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        String checkNumber = "\\d+";
        if (val.isEmpty()) {
            edtCreatePhone.setError("Enter valid phone number");
            return false;
        } else if (val.matches(checkspaces)) {
            edtCreatePhone.setError("No White spaces are allowed!");
            return false;
        }else if(!val.matches(checkNumber)){
            edtCreatePhone.setError("Not a phone number format");
            return false;
        }
        else {
            edtCreatePhone.setError(null);
            return true;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}