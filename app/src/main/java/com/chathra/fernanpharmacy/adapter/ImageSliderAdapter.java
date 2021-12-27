package com.chathra.fernanpharmacy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.model.ImageSliderItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private List<ImageSliderItem> imageSliderItems;
    private ViewPager2 viewPager2;
    private Context context;

    public ImageSliderAdapter(List<ImageSliderItem> imageSliderItems, ViewPager2 viewPager2, Context context) {
        this.imageSliderItems = imageSliderItems;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageSliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        holder.setImage(imageSliderItems.get(position));
        if(position == imageSliderItems.size() - 2){
            viewPager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return imageSliderItems.size();
    }

    public class ImageSliderViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView imageView;

        ImageSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(ImageSliderItem imageSliderItem) {
//            imageView.setImageResource(imageSliderItem.getImage());
            Glide.with(context).load(imageSliderItem.getImage()).into(imageView);
        }
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imageSliderItems.addAll(imageSliderItems);
            notifyDataSetChanged();
        }
    };
}
