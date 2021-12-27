/*
 * @author abhi
 */

package com.chathra.fernanpharmacy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.chathra.fernanpharmacy.common.Config.URL_IMAGE_LOADER;


public class ProductImageSliderAdapter extends PagerAdapter {

    private Context context;
    private List<String> images;

    public ProductImageSliderAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        System.out.println("images.size() -- " + images.size());
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(URL_IMAGE_LOADER + images.get(position).replace('\\', '/')).into(imageView);
        container.addView(imageView, 0);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
