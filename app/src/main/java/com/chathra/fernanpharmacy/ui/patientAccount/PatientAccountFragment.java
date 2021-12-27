package com.chathra.fernanpharmacy.ui.patientAccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.chathra.fernanpharmacy.databinding.FragmentPatientAccountBinding;
import com.kaopiz.kprogresshud.KProgressHUD;


public class PatientAccountFragment extends Fragment {

    FragmentPatientAccountBinding binding;
//
    KProgressHUD hud;
//    public Customer customer;
//    public String customerDocumentId;
//    UserStore userStore;
//
//    StorageReference storageRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        storageRef = FirebaseStorage.getInstance().getReference();
//
//        customer = ((MainActivity) getActivity()).customer;
//        customerDocumentId = ((MainActivity) getActivity()).customerDocumentId;

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

//        hud = KProgressHUD.create(requireActivity())
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setCancellable(false)
//                .setAnimationSpeed(2)
//                .setDimAmount(0.5f);
//
//        init();
//
//        binding.cardViewLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                logout();
//                Intent intent = new Intent(requireActivity(), AuthenticateActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });
//
        binding.cardViewPersonalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavDirections navDirections = PatientAccountFragmentDirections.actionNavigationAccountToDoctorEditProfileFragment2();

            }
        });
//
//
//        binding.location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavDirections navDirections = AccountFragmentDirections.actionNavigationAccountToCustomerLocationFragment();
//                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
//            }
//        });
//
//
        binding.cardViewSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = PatientAccountFragmentDirections.actionNavigationAccountToSupportFragment2();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        });
    }


//    void init(){
//        hud.show();
//        binding.name.setText(customer.getName());
//        binding.mobile.setText(customer.getMobile());
//
//        if (customer.getImage() != null) {
//            storageRef.child("customerImages/" + customer.getImage()).getDownloadUrl()
//                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            Glide.with(requireActivity()).load(uri).listener(new RequestListener<Drawable>() {
//                                @Override
//                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                    hud.dismiss();
//                                    return false;
//                                }
//                            }).into(binding.profileImage);
//
//
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            e.printStackTrace();
//                        }
//                    });
//        }else {
//            hud.dismiss();
//        }
//    }
//
//
//
//    public void logout() {
//        if (userStore == null) {
//            userStore = new UserStore(requireActivity());
//        }
//
//        userStore.open();
//        userStore.drop();
//        userStore.close();
//
//    }



}