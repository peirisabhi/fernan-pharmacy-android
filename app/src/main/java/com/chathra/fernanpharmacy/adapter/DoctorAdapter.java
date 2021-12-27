package com.chathra.fernanpharmacy.adapter;

import android.content.Context;
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
import com.chathra.fernanpharmacy.model.Doctor;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.chathra.fernanpharmacy.common.Config.URL_IMAGE_LOADER;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private List<Doctor> doctorList;
    private Listener listener;
    private Context context;

    public DoctorAdapter(List<Doctor> doctorList, Context context) {
        this.doctorList = doctorList;
        this.context = context;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onClick(int position);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doctor, viewGroup, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        CardView cardView = holder.cardView;
        ImageView img = cardView.findViewById(R.id.profile_image);
        TextView name = cardView.findViewById(R.id.textViewDoctorName);
        TextView specialities = cardView.findViewById(R.id.textViewSpecialities);

        name.setText(doctorList.get(i).getName());
        specialities.setText(doctorList.get(i).getSpecialist());

        if (!doctorList.get(i).getImage().equals(" ")){

            System.out.println(URL_IMAGE_LOADER  + doctorList.get(i).getImage().replace('\\', '/'));

            Glide.with(context).load(URL_IMAGE_LOADER + doctorList.get(i).getImage().replace('\\', '/')).into(img);


        }

    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);

            this.cardView = cardView;
        }
    }
}
