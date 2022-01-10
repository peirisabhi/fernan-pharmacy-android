package com.chathra.fernanpharmacy.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.adapter.DoctorAppointmentAdapter;
import com.chathra.fernanpharmacy.databinding.FragmentSplashBinding;
import com.chathra.fernanpharmacy.model.Appointment;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.chathra.fernanpharmacy.common.ComLoaders.GETDATA;
import static com.chathra.fernanpharmacy.common.ComLoaders.GETDATA2;
import static com.chathra.fernanpharmacy.common.ComLoaders.LOADDOCTORAPPOINTMENTS;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class SplashFragment extends Fragment {

    //    UserStore userStore;
//    AppStore appStore;
    KProgressHUD hud;
    boolean requestStatus = false;
    int activeStatus = 0;
    FragmentSplashBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(inflater, container, false);

//        if(appStore == null){
//            appStore  = new AppStore(getActivity());
//        }


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                appStore.open();
//                int count = appStore.getCount();
//                System.out.println("count " + count);
//                appStore.close();

//                if(count == 0){
//                    appStore.open();
//                  appStore.insertNew();
//                    appStore.close();
//
//                    Intent intent = new Intent(requireActivity(), OnboardingActivity.class);
//                    startActivity(intent);
//                    getActivity().finish();
//                }else {
//

                    navigate();



//                }
            }
        }, 1000);

    }


    synchronized void navigate() {
        hud.show();


        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        String url = GETDATA;
//        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.i("Response", "Response is: " + response);
                hud.dismiss();
                try {

                    activeStatus = response.getInt("auth");
                    requestStatus = true;

                    if(activeStatus == 1){
                        NavDirections action = SplashFragmentDirections.actionSplashFragmentToSignInFragment2();
                        Navigation.findNavController(binding.getRoot()).navigate(action);
                    }else{
                        NavDirections navDirections = SplashFragmentDirections.actionSplashFragmentToHomeFragment();
                        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    requestStatus = false;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", error.toString());
                requestStatus = false;
                navigate2();
            }
        });
        queue.add(jsonObjectRequest);



    }


    synchronized void navigate2() {
        hud.show();


        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        String url = GETDATA2;
//        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.i("Response", "Response is: " + response);
                hud.dismiss();
                try {

                    activeStatus = response.getInt("auth");
                    requestStatus = true;

                    if(activeStatus == 1){
                        NavDirections action = SplashFragmentDirections.actionSplashFragmentToSignInFragment2();
                        Navigation.findNavController(binding.getRoot()).navigate(action);
                    }else{
                        NavDirections navDirections = SplashFragmentDirections.actionSplashFragmentToHomeFragment();
                        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    requestStatus = false;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", error.toString());
                requestStatus = false;
            }
        });
        queue.add(jsonObjectRequest);



    }

}