package com.chathra.fernanpharmacy.ui.patientAccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.chathra.fernanpharmacy.AuthenticationActivity;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.databinding.FragmentPatientAccountBinding;
import com.chathra.fernanpharmacy.db.UserStore;
import com.kaopiz.kprogresshud.KProgressHUD;


public class PatientAccountFragment extends Fragment {

    FragmentPatientAccountBinding binding;
//
    KProgressHUD hud;

    UserStore userStore;
    PatientActivity patientActivity;
//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        patientActivity = (PatientActivity) requireActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPatientAccountBinding.inflate(inflater, container, false);
        return  binding.getRoot();

//        return null;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
//
        init();
//
        binding.cardViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Intent intent = new Intent(requireActivity(), AuthenticationActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
//
        binding.cardViewPersonalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavDirections navDirections = PatientAccountFragmentDirections.actionNavigationAccountToPatientEditProfileFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        });

        binding.cardViewSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = PatientAccountFragmentDirections.actionNavigationAccountToSupportFragment2();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        });
    }


    void init(){
//        hud.show();
        binding.name.setText(patientActivity.getCurrentUser().getName());


    }



    public void logout() {
        if (userStore == null) {
            userStore = new UserStore(requireActivity());
        }

        userStore.open();
        userStore.drop();
        userStore.close();

    }



}