package com.chathra.fernanpharmacy.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.adapter.ProductImageSliderAdapter;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.databinding.FragmentProductBinding;
import com.chathra.fernanpharmacy.model.Product;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment {

    FragmentProductBinding binding;
    KProgressHUD hud;

    ProductFragmentArgs args;
    Product product;
    List<String> imageList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = ProductFragmentArgs.fromBundle(getArguments());

        product = args.getProduct();

        loadDetails();

    }

    private void loadDetails(){
        binding.textViewTitle.setText(product.getName());
//        if (data.getLong("entered_date") != 0) {
//            binding.txtTime.setReferenceTime(data.getLong("entered_date"));
//        } else {
            binding.date.setVisibility(View.GONE);
//        }

        binding.location.setText(product.getCategory());
        binding.price.setText(ComLib.getDoubleWithCents(product.getSellingPrice()));
        binding.saleBy.setText(product.getBrand());
        binding.description.setText(product.getDescription());

        imageList.add(product.getImg());

        System.out.println("imageList -- " + imageList.size());

        ViewPager viewPager = binding.viewPager;
        ProductImageSliderAdapter productImageSliderAdapter = new ProductImageSliderAdapter(requireContext(), imageList);
        viewPager.setAdapter(productImageSliderAdapter);
    }

}