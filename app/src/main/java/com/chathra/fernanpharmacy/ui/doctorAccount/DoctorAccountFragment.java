package com.chathra.fernanpharmacy.ui.doctorAccount;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chathra.fernanpharmacy.AuthenticationActivity;
import com.chathra.fernanpharmacy.DoctorActivity;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.databinding.FragmentDoctorAccountBinding;
import com.chathra.fernanpharmacy.db.UserStore;
import com.chathra.fernanpharmacy.ui.patientAccount.PatientAccountFragmentDirections;

import org.jetbrains.annotations.NotNull;


public class DoctorAccountFragment extends Fragment {

    FragmentDoctorAccountBinding binding;
    DoctorActivity doctorActivity;
    UserStore userStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorActivity = (DoctorActivity) requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDoctorAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.name.setText(doctorActivity.getCurrentUser().getName());


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

                NavDirections navDirections = DoctorAccountFragmentDirections.actionNavigationDoctorAccountToDoctorEditProfileFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);

            }
        });

        binding.cardViewSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = DoctorAccountFragmentDirections.actionNavigationAccountToSupportFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        });

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