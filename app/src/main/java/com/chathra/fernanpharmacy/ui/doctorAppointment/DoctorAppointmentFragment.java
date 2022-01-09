package com.chathra.fernanpharmacy.ui.doctorAppointment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chathra.fernanpharmacy.DoctorActivity;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.adapter.DoctorAppointmentAdapter;
import com.chathra.fernanpharmacy.adapter.PatientAppointmentAdapter;
import com.chathra.fernanpharmacy.databinding.FragmentDoctorAppointmentBinding;
import com.chathra.fernanpharmacy.model.Appointment;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.chathra.fernanpharmacy.common.ComLoaders.LOADDOCTORAPPOINTMENTS;
import static com.chathra.fernanpharmacy.common.ComLoaders.LOADPATIENTAPPOINTMENTS;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class DoctorAppointmentFragment extends Fragment {

    FragmentDoctorAppointmentBinding binding;


    KProgressHUD hud;

    List<Appointment> appointmentList = new ArrayList<>();
    DoctorActivity doctorActivity;
    RecyclerView appointmentRecycler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorAppointmentBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doctorActivity = (DoctorActivity) requireActivity();

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
        String url = URL + LOADDOCTORAPPOINTMENTS + "/" + doctorActivity.getCurrentUser().getId();
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
                        appointment.setPatient(object.getString("patient"));
                        appointment.setStatus(object.getString("status"));
                        appointment.setPayment(object.getString("payment"));

                        appointmentList.add(appointment);

                    }

                    System.out.println("doctorList -- " + appointmentList.size());


                    DoctorAppointmentAdapter adapter = new DoctorAppointmentAdapter(requireActivity(), appointmentList);
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