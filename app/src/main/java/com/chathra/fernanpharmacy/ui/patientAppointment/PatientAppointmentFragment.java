package com.chathra.fernanpharmacy.ui.patientAppointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.chathra.fernanpharmacy.databinding.FragmentPatientAppointmentBinding;


public class PatientAppointmentFragment extends Fragment {

    private PatientAppointmentViewModel patientAppointmentViewModel;
    FragmentPatientAppointmentBinding binding;
//
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//    KProgressHUD hud;
//
//    SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd yyyy");
//    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//    Date currentDate = new Date();
//
//    RecyclerView appointmentRecycler;
//
//    private FirestoreRecyclerAdapter<Appointment, AppointmentHolder> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        patientAppointmentViewModel =
                new ViewModelProvider(this).get(PatientAppointmentViewModel.class);
        binding = FragmentPatientAppointmentBinding.inflate(inflater, container, false);
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
//        loadAppointments();
    }


//    void loadAppointments() {
//        hud.show();
//
//        appointmentRecycler = binding.appointmentsRecycler;
//        appointmentRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
//
//        Query loadJobs = db.collection("doctor_appointments")
//                .whereEqualTo("customerDocumentId", ((MainActivity)getActivity()).customerDocumentId);
//        FirestoreRecyclerOptions recyclerOptions = new FirestoreRecyclerOptions.Builder<Appointment>().setQuery(loadJobs, Appointment.class).build();
//
//        adapter = new FirestoreRecyclerAdapter<Appointment, AppointmentHolder>(recyclerOptions) {
//            @Override
//            protected void onBindViewHolder(@NonNull AppointmentHolder appointmentHolder, int i, @NonNull Appointment appointment) {
//
//                appointmentHolder.doctorName.setText(appointment.getDoctorName());
//                appointmentHolder.specialities.setText(appointment.getSpecialist());
//
//                if (!appointment.getVisitType().equals("Video Call")) {
//                    appointmentHolder.visit.setTextColor(ContextCompat.getColor(requireActivity(), R.color.non_video));
//                    appointmentHolder.visit.setText(appointment.getVisitType());
//                    appointmentHolder.videoCam.setImageResource(R.drawable.ic_location_gray);
//                }
//
//                appointmentHolder.time.setText(appointment.getTimeDuration());
//
//                appointmentHolder.date.setText(sdf.format(appointment.getAppointmentDate()) + " " + appointment.getStartTime());
//                long dayCount = ComLib.getDayCount(sdf1.format(currentDate), sdf1.format(appointment.getAppointmentDate()));
//                if (dayCount < 0) {
//                    appointmentHolder.toDays.setText("In " + dayCount + " Days");
//                }else{
//                    appointmentHolder.toDays.setVisibility(View.INVISIBLE);
//                }
//
//                appointmentHolder.status.setText(appointment.getStatus());
//
//                if(appointment.getStatus().equals("Pending")){
//                    appointmentHolder.status.setTextColor(ContextCompat.getColor(requireActivity(), R.color.unverified));
//                }else if(appointment.getStatus().equals("Confirmed")){
//                    appointmentHolder.status.setTextColor(ContextCompat.getColor(requireActivity(), R.color.verified));
//                }else if(appointment.getStatus().equals("Canceled")){
//                    appointmentHolder.status.setTextColor(ContextCompat.getColor(requireActivity(), R.color.canceled));
//                }
//
//                if(appointment.isPaid()){
//                    appointmentHolder.payment.setTextColor(ContextCompat.getColor(requireActivity(), R.color.verified));
//                    appointmentHolder.payment.setText("PAID");
//                }else{
//                    appointmentHolder.payment.setTextColor(ContextCompat.getColor(requireActivity(), R.color.unverified));
//                    appointmentHolder.payment.setText("Rs." + ComLib.getDoubleWithCents(appointment.getPaymentAmount()));
//                }
//
//
//                if (appointment.getDoctorImage() != null) {
//                    System.out.println(appointment.getDoctorImage());
//                    storageRef.child("doctorImages/" + appointment.getDoctorImage()).getDownloadUrl()
//                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    Glide.with(requireActivity()).load(uri).listener(new RequestListener<Drawable>() {
//                                        @Override
//                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                            return false;
//                                        }
//
//                                        @Override
//                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                            hud.dismiss();
//                                            return false;
//                                        }
//                                    }).into(appointmentHolder.doctorImage);
////                                    hud.dismiss();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    e.printStackTrace();
//                                    hud.dismiss();
//                                }
//                            });
//                } else {
//                    hud.dismiss();
//                }
//
//            }
//
//            @NonNull
//            @Override
//            public AppointmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
//
//                return new AppointmentHolder(view);
//            }
//        };
//
//        appointmentRecycler.setAdapter(adapter);
//
//    }
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }

}