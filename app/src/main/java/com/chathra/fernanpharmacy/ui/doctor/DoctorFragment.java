package com.chathra.fernanpharmacy.ui.doctor;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chathra.fernanpharmacy.adapter.ProductAdapter;
import com.chathra.fernanpharmacy.databinding.FragmentDoctorBinding;
import com.chathra.fernanpharmacy.model.Doctor;
import com.chathra.fernanpharmacy.model.Product;
import com.chathra.fernanpharmacy.ui.shop.ShopFragmentDirections;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.chathra.fernanpharmacy.common.ComLoaders.LOADPRODUCTS;
import static com.chathra.fernanpharmacy.common.Config.URL;

public class DoctorFragment extends Fragment {

    private DoctorViewModel doctorViewModel;
    private FragmentDoctorBinding binding;

    KProgressHUD hud;

    RecyclerView doctorRecycler;

    List<Doctor> doctorList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        doctorViewModel =
                new ViewModelProvider(this).get(DoctorViewModel.class);

        binding = FragmentDoctorBinding.inflate(inflater, container, false);

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

        doctorRecycler = binding.doctorRecycler;
        doctorRecycler.setHasFixedSize(true);
        doctorRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));

        loadDoctors();
//
//        binding.map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavDirections navDirections = DoctorFragmentDirections.actionNavigationDoctorToDoctorMapFragment();
//                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
//            }
//        });
    }



    void  loadDoctors(){

        hud.show();
        doctorList.clear();


        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        String url = URL + LOADPRODUCTS;
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

                        Product product = new Product();
                        product.setId(object.getLong("id"));
                        product.setName(object.getString("name"));
                        product.setCategory(object.getJSONObject("category").getString("category"));
                        product.setSellingPrice(object.getDouble("sellingPrice"));
                        product.setImg(object.getString("img"));
                        product.setDescription(object.getString("description"));
                        product.setBrand(object.getJSONObject("brand").getString("brand"));

//                        productList.add(product);
                    }

//                    System.out.println("categoryList -- " + productList.size());
//
//                    ProductAdapter productAdapter = new ProductAdapter(productList, requireActivity());
//                    productRecyclerView.setAdapter(productAdapter);
//
//                    productAdapter.setListener(new ProductAdapter.Listener() {
//                        @Override
//                        public void onClick(int position) {
//
//                            ShopFragmentDirections.ActionNavigationShopToProductFragment toProductFragment = ShopFragmentDirections.actionNavigationShopToProductFragment(productList.get(position));
//                            Navigation.findNavController(binding.getRoot()).navigate(toProductFragment);
//
//                        }
//                    });


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