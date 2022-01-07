package com.chathra.fernanpharmacy.ui.bookAppointment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chathra.fernanpharmacy.DoctorActivity;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.databinding.FragmentBookAppointmentBinding;
import com.chathra.fernanpharmacy.model.Doctor;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.chathra.fernanpharmacy.common.ComActions.ADDAPPOINTMENT;
import static com.chathra.fernanpharmacy.common.ComActions.UPDATEDOCTORACCOUNT;
import static com.chathra.fernanpharmacy.common.Config.URL;

public class BookAppointmentFragment extends Fragment {

    FragmentBookAppointmentBinding binding;
    BookAppointmentFragmentArgs args;

    Doctor doctor;
    PatientActivity patientActivity;

    KProgressHUD hud;

    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;

    private EditText dateEditText, timeEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookAppointmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        patientActivity = (PatientActivity) requireActivity();

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        dateEditText = binding.editDate;
        timeEditText = binding.editTime;

        dateEditText.setInputType(InputType.TYPE_NULL);
        timeEditText.setInputType(InputType.TYPE_NULL);

        dateEditText.setText(ComLib.getDate(new Date()));
//        timeEditText.setText(ComLib.get);

        args = BookAppointmentFragmentArgs.fromBundle(getArguments());

        doctor = args.getDoctor();

        init();
        setDatePickers();


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAppointment();
            }
        });

        binding.editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        binding.editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show();
            }
        });

    }


    public void setDatePickers(){

        int currentYear = ComLib.GetCurrentYear();
        int currentMonth = ComLib.GetCurrentMonth() - 1;
        int currentDay = ComLib.GetCurrentDay();
        System.out.println("currentMonth===" + currentMonth);

        if(datePicker == null){
            datePicker = new DatePickerDialog(requireActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dateString = dateFormat.format(calendar.getTime());

                            binding.editDate.setText(dateString);
                        }
                    }, currentYear, currentMonth, currentDay);
        }

        if(timePicker == null){
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            timeEditText.setText( hour + ":" + minute);

            timePicker = new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    timeEditText.setText( selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, true);
        }
    }


    private void init(){
        if(doctor != null){
            binding.textViewDoctorName.setText(doctor.getName());
            binding.textViewSpecialities.setText(doctor.getSpecialist());

        }
    }


    private void saveAppointment(){

        String name = binding.editName.getText().toString();
        String mobile = binding.editMobile.getText().toString();
        String nic = binding.editNic.getText().toString();
        String about = binding.editAbout.getText().toString();
        String date = binding.editDate.getText().toString();
        String time = binding.editTime.getText().toString();

        if (name.equals("") || name == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Name!", Toast.LENGTH_SHORT).show();
        } else if (mobile.equals("") || mobile == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Mobile No!", Toast.LENGTH_SHORT).show();
        } else if (date.equals("") || date == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Date !", Toast.LENGTH_SHORT).show();
        }else if (time.equals("") || time == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Time!", Toast.LENGTH_SHORT).show();
        }else {
            hud.show();

            JSONObject jsonRequest = new JSONObject();
            try {
                jsonRequest.put("date", date);
                jsonRequest.put("time", time);
                jsonRequest.put("doctorId", doctor.getId());
                jsonRequest.put("patientId", patientActivity.getCurrentUser().getId());
                jsonRequest.put("name", name);
                jsonRequest.put("mobile", mobile);
                jsonRequest.put("nic", nic);
                jsonRequest.put("about", about);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonRequest.toString());


            RequestQueue queue = Volley.newRequestQueue(requireActivity());
            String url = URL + ADDAPPOINTMENT;

            System.out.println(url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    hud.dismiss();
                    Log.i("Response", "Response is: " + response);

                    System.out.println(response);
                    Toast.makeText(requireActivity(), "Successfully Saved!", Toast.LENGTH_SHORT).show();

                    try {
                        String id = response.getString("id");

                        String text = "<p>Appointment booked with <b>"+name+"</b> <br/>on <b>"+date+" "+time+"</b> <br/>Reference No <b>#"+id+"</b> </p>";


                        BookAppointmentFragmentDirections.ActionBookAppointmentFragmentToBookingSuccessFragment successFragment = BookAppointmentFragmentDirections.actionBookAppointmentFragmentToBookingSuccessFragment(text);
                        Navigation.findNavController(binding.getRoot()).navigate(successFragment);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Response", error.toString());
                    Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);

        }


    }


}