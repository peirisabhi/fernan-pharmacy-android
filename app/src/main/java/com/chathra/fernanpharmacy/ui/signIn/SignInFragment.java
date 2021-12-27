package com.chathra.fernanpharmacy.ui.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chathra.fernanpharmacy.DoctorActivity;
import com.chathra.fernanpharmacy.PatientActivity;
import com.chathra.fernanpharmacy.databinding.FragmentSignInBinding;
import com.chathra.fernanpharmacy.db.UserStore;
import com.chathra.fernanpharmacy.model.Doctor;
import com.chathra.fernanpharmacy.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import static com.chathra.fernanpharmacy.common.ComActions.SIGNIN;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;
    UserStore userStore;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (userStore == null) {
            userStore = new UserStore(getActivity());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections navDirections = SignInFragmentDirections.actionSignInFragmentToSignUpTypeFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });


        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }


    private synchronized void signIn() {
        String email = binding.editEmail.getText().toString();
        String password = binding.editPassword.getText().toString();

        if (email == null || email.equals("")) {
            Toast.makeText(requireActivity(), "email Empty", Toast.LENGTH_SHORT).show();
        } else if (password == null || password.equals("")) {
            Toast.makeText(requireActivity(), "password Empty", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject jsonRequest = new JSONObject();
            String url = URL + SIGNIN;
            try {

                jsonRequest.put("email", email);
                jsonRequest.put("password", password);

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


                        int status = response.getInt("status");
                        System.out.println(status);

                        if (status == 200) {

                            int id = response.getInt("id");
                            System.out.println(id);

                            User user = new User(response.getInt("id"), response.getString("type"));

                            userStore.open();
                            userStore.drop();
                            userStore.insertUser(user);
                            userStore.close();

                            Toast.makeText(requireActivity(), "Login Success!", Toast.LENGTH_SHORT).show();
//
                            if(response.getString("type").equals("PATIENT")) {

                                Intent intent = new Intent(requireActivity(), PatientActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }else{
                                Intent intent = new Intent(requireActivity(), DoctorActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }

                        } else {
                            Toast.makeText(requireActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Response", error.toString());
                    Toast.makeText(requireActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


}