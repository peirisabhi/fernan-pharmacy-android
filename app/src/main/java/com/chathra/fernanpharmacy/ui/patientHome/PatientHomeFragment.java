package com.chathra.fernanpharmacy.ui.patientHome;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;


import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.adapter.ImageSliderAdapter;
import com.chathra.fernanpharmacy.databinding.FragmentHomeBinding;
import com.chathra.fernanpharmacy.model.ImageSliderItem;

import java.util.ArrayList;
import java.util.List;

public class PatientHomeFragment extends Fragment {

    private PatientHomeViewModel patientHomeViewModel;
    private FragmentHomeBinding binding;
    private Handler sliderHandler = new Handler();
    private static final int SLIDER_TIME_OUT = 3000;

//    AnimDialog networkLostDialog;

    boolean networkAvailable = false;

    ViewPager2 viewPager2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        patientHomeViewModel =
                new ViewModelProvider(this).get(PatientHomeViewModel.class);
//
       binding = FragmentHomeBinding.inflate(inflater, container, false);
//
        return binding.getRoot();

//        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager2 = binding.viewPagerImageSlider;

        loadImageSlider();
    }


    private void loadImageSlider(){
        List<ImageSliderItem> imageSliderItems = new ArrayList<>();
        imageSliderItems.add(new ImageSliderItem(R.drawable.slider1));
        imageSliderItems.add(new ImageSliderItem(R.drawable.slider2));
        imageSliderItems.add(new ImageSliderItem(R.drawable.slider1));
//        imageSliderItems.add(new ImageSliderItem(R.drawable.slider2));
//        imageSliderItems.add(new ImageSliderItem(R.drawable.slider1));

        viewPager2.setAdapter(new ImageSliderAdapter(imageSliderItems, viewPager2, requireActivity()));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(25));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, SLIDER_TIME_OUT);
            }
        });
    }



    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, SLIDER_TIME_OUT);
    }



}