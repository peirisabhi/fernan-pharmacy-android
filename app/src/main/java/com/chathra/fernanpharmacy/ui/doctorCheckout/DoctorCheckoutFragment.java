package com.chathra.fernanpharmacy.ui.doctorCheckout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.databinding.FragmentCheckoutBinding;
import com.chathra.fernanpharmacy.databinding.FragmentDoctorCheckoutBinding;
import com.chathra.fernanpharmacy.ui.checkout.CheckoutFragmentArgs;
import com.chathra.fernanpharmacy.ui.checkout.CheckoutFragmentDirections;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.chathra.fernanpharmacy.common.ComActions.ADDORDER;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class DoctorCheckoutFragment extends Fragment {

    FragmentDoctorCheckoutBinding binding;
    DoctorCheckoutFragmentArgs args;
    KProgressHUD hud;

    DoctorActivity doctorActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDoctorCheckoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doctorActivity = (DoctorActivity) requireActivity();

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        args = DoctorCheckoutFragmentArgs.fromBundle(getArguments());

        binding.subTotal.setText(ComLib.getDoubleWithCents(args.getProduct().getSellingPrice()));
        binding.netTotal.setText(ComLib.getDoubleWithCents(args.getProduct().getSellingPrice() + 250));


        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrder();
            }
        });

    }


    private void saveOrder() {
        String name = binding.fullName.getText().toString();
        String nic = binding.nic.getText().toString();
        String email = binding.email.getText().toString();
        String address = binding.address.getText().toString();
        String mobile = binding.mobile.getText().toString();

        if (name.equals("") || name == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Name!", Toast.LENGTH_SHORT).show();
        } else if (mobile.equals("") || mobile == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Mobile No!", Toast.LENGTH_SHORT).show();
        } else if (address.equals("") || address == null) {
            Toast.makeText(requireActivity(), "Please Enter Your Address!", Toast.LENGTH_SHORT).show();
        } else {
//            hud.show();

            JSONObject jsonRequest = new JSONObject();
            JSONArray orderItems = new JSONArray();
            try {

                JSONObject orderItem = new JSONObject();
                orderItem.put("productId", args.getProduct().getId());
                orderItem.put("qty", 1);

                orderItems.put(orderItem);

                jsonRequest.put("doctorId", doctorActivity.getCurrentUser().getId());
                jsonRequest.put("fname", name);
                jsonRequest.put("nic", nic);
                jsonRequest.put("phone", mobile);
                jsonRequest.put("email", email);
                jsonRequest.put("address", address);
                jsonRequest.put("subTotal", args.getProduct().getSellingPrice());
                jsonRequest.put("shipping", 250);
                jsonRequest.put("total", args.getProduct().getSellingPrice() + 250);
                jsonRequest.put("orderItems", orderItems);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonRequest.toString());


            RequestQueue queue = Volley.newRequestQueue(requireActivity());
            String url = URL + ADDORDER;

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

                        DoctorCheckoutFragmentDirections.ActionDoctorCheckoutFragmentToDoctorOrderSuccessFragment successFragment = DoctorCheckoutFragmentDirections.actionDoctorCheckoutFragmentToDoctorOrderSuccessFragment("<p>Order Reference No <b>#" + id + "</b></p>");
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