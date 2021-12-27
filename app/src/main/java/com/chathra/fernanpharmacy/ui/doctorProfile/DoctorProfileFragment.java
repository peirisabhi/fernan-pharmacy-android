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

import com.bumptech.glide.Glide;
import com.chathra.fernanpharmacy.databinding.FragmentDoctorProfileBinding;
import com.chathra.fernanpharmacy.model.Doctor;
import com.kaopiz.kprogresshud.KProgressHUD;

import static com.chathra.fernanpharmacy.common.Config.URL_IMAGE_LOADER;


public class DoctorProfileFragment extends Fragment {

    FragmentDoctorProfileBinding binding;
    DoctorProfileFragmentArgs args;
    KProgressHUD hud;
    Doctor doctor;

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

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        args = DoctorProfileFragmentArgs.fromBundle(getArguments());

        init();

        binding.bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(args.getDoctor().toString());

                DoctorProfileFragmentDirections.ActionDoctorProfileFragmentToBookAppointmentFragment bookAppointmentFragment = DoctorProfileFragmentDirections.actionDoctorProfileFragmentToBookAppointmentFragment(args.getDoctor());
                Navigation.findNavController(binding.getRoot()).navigate(bookAppointmentFragment);
            }
        });


    }

    void init(){
//        hud.show();

        doctor = args.getDoctor();

//        if(!doctor.isVideoOk()){
//            binding.video.setTextColor(ContextCompat.getColor(requireActivity(), R.color.non_video));
//            binding.videoCam.setImageResource(R.drawable.ic_video_off);
//        }

        binding.textViewDoctorName.setText(doctor.getName());
        binding.textViewSpecialities.setText(doctor.getSpecialist());
//        binding.location.setText(doctor.getCity());
//        binding.experiance.setText(doctor.getExperience());

//        if (doctor.getLan() != 0 && doctor.getLat() != 0) {
//            binding.direction.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    DoctorProfileFragmentDirections.ActionDoctorProfileFragmentToDoctorLocationDirectionFragment toDoctorLocationDirectionFragment = DoctorProfileFragmentDirections.actionDoctorProfileFragmentToDoctorLocationDirectionFragment(doctor);
//                    Navigation.findNavController(binding.getRoot()).navigate(toDoctorLocationDirectionFragment);
//                }
//            });
//        }else{
            binding.direction.setVisibility(View.GONE);
//        }


        if (!doctor.getImage().equals(" ")){

            System.out.println(URL_IMAGE_LOADER  + doctor.getImage().replace('\\', '/'));

            Glide.with(requireActivity()).load(URL_IMAGE_LOADER + doctor.getImage().replace('\\', '/')).into(binding.profileImage);


        }


    }
}