package com.chathra.fernanpharmacy.ui.patientAppointment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.adapter.DoctorAdapter;
import com.chathra.fernanpharmacy.adapter.PatientAppointmentAdapter;
import com.chathra.fernanpharmacy.databinding.FragmentPatientAppointmentBinding;
import com.chathra.fernanpharmacy.model.Appointment;
import com.chathra.fernanpharmacy.model.Doctor;
import com.chathra.fernanpharmacy.ui.doctor.DoctorFragmentDirections;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.chathra.fernanpharmacy.common.ComLoaders.LOADDOCTORDETAILS;
import static com.chathra.fernanpharmacy.common.ComLoaders.LOADPATIENTAPPOINTMENTS;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class PatientAppointmentFragment extends Fragment {

    private PatientAppointmentViewModel patientAppointmentViewModel;
    FragmentPatientAppointmentBinding binding;

    KProgressHUD hud;

    List<Appointment> appointmentList = new ArrayList<>();
    PatientActivity patientActivity;
    RecyclerView appointmentRecycler;


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

        patientActivity = (PatientActivity) requireActivity();

        appointmentRecycler = binding.appointmentsRecycler;
        appointmentRecycler.setHasFixedSize(true);
        appointmentRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        loadAppointments();
    }


    void loadAppointments() {
        appointmentList.clear();
        hud.show();

        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        String url = URL + LOADPATIENTAPPOINTMENTS + "/" + patientActivity.getCurrentUser().getId();
        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", "Response is: " + response);
                hud.dismiss();
                try {

                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        Appointment appointment = new Appointment();
                        appointment.setId(object.getLong("id"));
                        appointment.setDate(object.getString("date"));
                        appointment.setTime(object.getString("time"));
                        appointment.setDoctor(object.getString("doctor"));
                        appointment.setStatus(object.getString("status"));
                        appointment.setPayment(object.getString("payment"));

                        appointmentList.add(appointment);

                    }

                    System.out.println("doctorList -- " + appointmentList.size());


                    PatientAppointmentAdapter adapter = new PatientAppointmentAdapter(requireActivity(), appointmentList);
                    appointmentRecycler.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", error.toString());
            }
        });
        queue.add(jsonObjectRequest);


    }


}