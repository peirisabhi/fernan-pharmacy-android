package com.chathra.fernanpharmacy.ui.signUpType;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.databinding.FragmentSignUpTypeBinding;
import com.chathra.fernanpharmacy.ui.signUp.SignUpFragmentDirections;

import org.jetbrains.annotations.NotNull;


public class SignUpTypeFragment extends Fragment {

    FragmentSignUpTypeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_up_type, container, false);

        binding = FragmentSignUpTypeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpTypeFragmentDirections.ActionSignUpTypeFragmentToSignUpFragment toSignUpFragment = SignUpTypeFragmentDirections.actionSignUpTypeFragmentToSignUpFragment("PATIENT");
                Navigation.findNavController(view).navigate(toSignUpFragment);
            }
        });


        binding.materialButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpTypeFragmentDirections.ActionSignUpTypeFragmentToSignUpFragment toSignUpFragment = SignUpTypeFragmentDirections.actionSignUpTypeFragmentToSignUpFragment("DOCTOR");
                Navigation.findNavController(view).navigate(toSignUpFragment);
            }
        });


//        binding.materialButton3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SignUpTypeFragmentDirections.ActionSignUpTypeFragmentToSignUpFragment toSignUpFragment = SignUpTypeFragmentDirections.actionSignUpTypeFragmentToSignUpFragment("PHARMACY");
//                Navigation.findNavController(view).navigate(toSignUpFragment);
//            }
//        });


        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections navDirections = SignUpTypeFragmentDirections.actionSignUpTypeFragmentToSignInFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });
    }
}