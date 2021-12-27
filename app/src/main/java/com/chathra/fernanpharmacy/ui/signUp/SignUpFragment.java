package com.chathra.fernanpharmacy.ui.signUp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chathra.fernanpharmacy.DoctorActivity;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.databinding.FragmentSignUpBinding;
import com.chathra.fernanpharmacy.db.UserStore;
import com.chathra.fernanpharmacy.model.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static com.chathra.fernanpharmacy.common.ComActions.DOCTORSIGNUP;
import static com.chathra.fernanpharmacy.common.ComActions.PATIENTSIGNUP;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;
    SignUpFragmentArgs args;
    UserStore userStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(userStore == null){
            userStore  = new UserStore(getActivity());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_up, container, false);

        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = SignUpFragmentArgs.fromBundle(getArguments());

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections navDirections = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });
    }


    private synchronized void signUp() {

        String name = binding.editName.getText().toString();
        String email = binding.editEmail.getText().toString();
        String password = binding.editPassword.getText().toString();
        String confirmPassword = binding.editConfirmPassword.getText().toString();

        System.out.println("password " + password);
        System.out.println("confirmPassword " + confirmPassword);

        if (name == null || name.equals("")) {
            Toast.makeText(requireActivity(), "Name Empty", Toast.LENGTH_SHORT).show();
        } else if (email == null || email.equals("")) {
            Toast.makeText(requireActivity(), "email Empty", Toast.LENGTH_SHORT).show();
        } else if (password == null || password.equals("")) {
            Toast.makeText(requireActivity(), "password Empty", Toast.LENGTH_SHORT).show();
        } else if (confirmPassword == null || confirmPassword.equals("")) {
            Toast.makeText(requireActivity(), "Confirm Password Empty", Toast.LENGTH_SHORT).show();
        } else if (!confirmPassword.equals(password)) {
            Toast.makeText(requireActivity(), "Confirm Password Not Same", Toast.LENGTH_SHORT).show();
        } else {

            JSONObject jsonRequest = new JSONObject();
            String url = null;
            try {

                if (args.getType().equals("DOCTOR")) {
                    jsonRequest.put("fname", name);
                    jsonRequest.put("email", email);
                    jsonRequest.put("password", password);
                    jsonRequest.put("specialitiesId", 1);
                    jsonRequest.put("status", 1);

                    url = URL + DOCTORSIGNUP;
                }else if (args.getType().equals("PATIENT")) {
                    jsonRequest.put("fullName", name);
                    jsonRequest.put("email", email);
                    jsonRequest.put("password", password);
                    jsonRequest.put("status", 1);

                    url = URL + PATIENTSIGNUP;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonRequest.toString());


            RequestQueue queue = Volley.newRequestQueue(requireActivity());


            System.out.println(url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
//                    hud.dismiss();
                    Log.i("Response", "Response is: " + response);

                    try {
                        int id = response.getInt("id");
                        System.out.println(id);

//                        if (id != 0) {

                            User user = new User(response.getInt("id"), args.getType());
//
                            userStore.open();
                            userStore.drop();
                            userStore.insertUser(user);
                            userStore.close();
//
                            Toast.makeText(requireActivity(), "Registration Success!", Toast.LENGTH_SHORT).show();

                        if (args.getType().equals("PATIENT")) {

                            Intent intent = new Intent(requireActivity(), PatientActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else {
                            Intent intent = new Intent(requireActivity(), DoctorActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
//                        } else {
//                            Toast.makeText(requireActivity(), "Some thing Went Wrong", Toast.LENGTH_SHORT).show();
//                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Response", error.toString());


                    String errorMessage = error.toString();

                    String responseBody = null;
                    try {
                        responseBody = new String(error.networkResponse.data, "utf-8");
                        JSONObject data = new JSONObject(responseBody);

                        errorMessage = data.optString("message") + " : " + data.optString("variable");


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_LONG).show();
                }
            });
            queue.add(jsonObjectRequest);

        }

    }

}