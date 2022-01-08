package com.chathra.fernanpharmacy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.model.Product;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.chathra.fernanpharmacy.common.Config.URL_IMAGE_LOADER;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private Listener listener;
    private Context context;

    public interface Listener {
        void onClick(int position);
    }

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView img = cardView.findViewById(R.id.img);
        TextView title = cardView.findViewById(R.id.product_name);
        TextView location = cardView.findViewById(R.id.location);
        TextView rating = cardView.findViewById(R.id.rating);
        TextView price = cardView.findViewById(R.id.price);
        TextView discount_price = cardView.findViewById(R.id.discount_price);

        title.setText(productList.get(position).getName());
        price.setText(ComLib.getDoubleWithCents(productList.get(position).getSellingPrice()));
        location.setText(productList.get(position).getCategory());

        System.out.println("productList.get(position).getImg() ---  " + productList.get(position).getImg());

        if(productList.get(position).getImg().equals(" ")){
            System.out.println("null");
        }else{
            System.out.println("not null");
        }

        if (productList.get(position).getImg().equals(" ")){
            Glide.with(context).load(R.drawable.image).into(img);
        }else{
//            String base64image = productList.get(position).getImg();
//
//            byte[] encodeByte = Base64.decode(base64image, Base64.DEFAULT);
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length, options);
//
////            int nh = (int) (pl.getImage_height() * (50.0 / pl.getImage_width()));
//
//            image = Bitmap.createScaledBitmap(image, 360, 360, false);
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            image.compress(Bitmap.CompressFormat.PNG, 100, baos);
//
//            byte[] b = baos.toByteArray();
//            System.gc();
//            base64image = Base64.encodeToString(b, Base64.NO_WRAP);
//
//            byte[] decodedString = Base64.decode(base64image, Base64.DEFAULT);
//            Bitmap bm = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//            Glide.with(context).load(bm).into(img);

            System.out.println(URL_IMAGE_LOADER  + productList.get(position).getImg().replace('\\', '/'));

            Glide.with(context).load(URL_IMAGE_LOADER + productList.get(position).getImg().replace('\\', '/')).into(img);


        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener !=null){
                    listener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
