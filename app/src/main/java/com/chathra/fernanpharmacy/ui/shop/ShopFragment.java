package com.chathra.fernanpharmacy.ui.shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.adapter.ProductAdapter;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.databinding.FragmentShopBinding;
import com.chathra.fernanpharmacy.model.Product;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.chathra.fernanpharmacy.common.ComLoaders.LOADPRODUCTS;
import static com.chathra.fernanpharmacy.common.Config.URL;


public class ShopFragment extends Fragment {

   FragmentShopBinding binding;

    RecyclerView productRecyclerView;
    private RecyclerView.LayoutManager productLayoutManager;

    KProgressHUD hud;

    List<Product> productList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hud = KProgressHUD.create(requireActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        productRecyclerView = binding.productRecycler;
        productRecyclerView.setHasFixedSize(true);

        productLayoutManager = new GridLayoutManager(requireActivity(), 2);
        productRecyclerView.setLayoutManager(productLayoutManager);

        loadProducts();
    }


    void loadProducts(){
        hud.show();
        productList.clear();

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

                            productList.add(product);
                        }

                        System.out.println("categoryList -- " + productList.size());

                        ProductAdapter productAdapter = new ProductAdapter(productList, requireActivity());
                        productRecyclerView.setAdapter(productAdapter);

                        productAdapter.setListener(new ProductAdapter.Listener() {
                            @Override
                            public void onClick(int position) {

                                ShopFragmentDirections.ActionNavigationShopToProductFragment toProductFragment = ShopFragmentDirections.actionNavigationShopToProductFragment(productList.get(position));
                                Navigation.findNavController(binding.getRoot()).navigate(toProductFragment);

                            }
                        });


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