package com.chathra.fernanpharmacy.ui.doctorProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.chathra.fernanpharmacy.databinding.FragmentDoctorProfileBinding;


public class DoctorProfileFragment extends Fragment {

    FragmentDoctorProfileBinding binding;
//    DoctorProfileFragmentArgs args;
//    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//    KProgressHUD hud;
//
//    Doctor doctor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDoctorProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
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
//        args = DoctorProfileFragmentArgs.fromBundle(getArguments());
//
//        init();
//
//        binding.bookAppointment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println(args.getDoctorDocumentId());
//
//                Intent intent = new Intent(requireActivity(), DoctorAppointmentBookingActivity.class);
//                intent.putExtra("doctor", doctor);
//                intent.putExtra("doctorDocumentId", args.getDoctorDocumentId());
//                intent.putExtra("customer", ((MainActivity)getActivity()).customer);
//                intent.putExtra("customerDocumentId", ((MainActivity)getActivity()).customerDocumentId);
//                startActivity(intent);
//            }
//        });
//
//
//        binding.btnMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DoctorProfileFragmentDirections.ActionDoctorProfileFragmentToChatMessageFragment actionDoctorProfileFragmentToChatMessageFragment = DoctorProfileFragmentDirections.actionDoctorProfileFragmentToChatMessageFragment(doctor, args.getDoctorDocumentId(), null, null);
//                Navigation.findNavController(binding.getRoot()).navigate(actionDoctorProfileFragmentToChatMessageFragment);
//            }
//        });

    }

//    void init(){
//        hud.show();
//
//        doctor = args.getDoctor();
//
//        if(!doctor.isVideoOk()){
//            binding.video.setTextColor(ContextCompat.getColor(requireActivity(), R.color.non_video));
//            binding.videoCam.setImageResource(R.drawable.ic_video_off);
//        }
//
//        binding.textViewDoctorName.setText(doctor.getName());
//        binding.textViewSpecialities.setText(doctor.getSpecialist());
//        binding.location.setText(doctor.getCity());
//        binding.experiance.setText(doctor.getExperience());
//
//        if (doctor.getLan() != 0 && doctor.getLat() != 0) {
//            binding.direction.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    DoctorProfileFragmentDirections.ActionDoctorProfileFragmentToDoctorLocationDirectionFragment toDoctorLocationDirectionFragment = DoctorProfileFragmentDirections.actionDoctorProfileFragmentToDoctorLocationDirectionFragment(doctor);
//                    Navigation.findNavController(binding.getRoot()).navigate(toDoctorLocationDirectionFragment);
//                }
//            });
//        }else{
//            binding.direction.setVisibility(View.GONE);
//        }
//
//        if (doctor.getImage() != null) {
//            storageRef.child("doctorImages/" + doctor.getImage()).getDownloadUrl()
//                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            Glide.with(requireActivity()).load(uri).into(binding.profileImage);
//                            hud.dismiss();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            e.printStackTrace();
//                            hud.dismiss();
//                        }
//                    });
//        }else {
//            hud.dismiss();
//        }
//
//
//    }
}