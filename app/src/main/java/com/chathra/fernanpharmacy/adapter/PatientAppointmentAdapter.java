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

import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.common.ComLib;
import com.chathra.fernanpharmacy.model.Appointment;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PatientAppointmentAdapter extends RecyclerView.Adapter<PatientAppointmentAdapter.ViewHolder> {

    private Context context;
    private List<Appointment> appointmentList;

    SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

    public PatientAppointmentAdapter(Context context, List<Appointment> appointmentList) {
        this.context = context;
        this.appointmentList = appointmentList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_appointment, viewGroup, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CardView cardView = viewHolder.cardView;

        ImageView doctorImage = cardView.findViewById(R.id.profile_image);
        ImageView videoCam = cardView.findViewById(R.id.video_cam);
        TextView doctorName = cardView.findViewById(R.id.textViewDoctorName);
        TextView specialities = cardView.findViewById(R.id.textViewSpecialities);
        TextView visit = cardView.findViewById(R.id.visit);
        TextView time = cardView.findViewById(R.id.time);
        TextView toDays = cardView.findViewById(R.id.toDays);
        TextView date = cardView.findViewById(R.id.dateTime);
        TextView status = cardView.findViewById(R.id.status);
        TextView payment = cardView.findViewById(R.id.payment);

        long dayCount = ComLib.getDayCount(sdf1.format(new Date()), appointmentList.get(i).getDate());

        doctorName.setText(appointmentList.get(i).getDoctor());
        specialities.setText(appointmentList.get(i).getSpecialities());
        date.setText(appointmentList.get(i).getDate() + " " + appointmentList.get(i).getTime());
        status.setText(appointmentList.get(i).getStatus());
//        time.setText(appointmentList.get(i).getTime());
        toDays.setText("In " + dayCount + " Days");

    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);

            this.cardView = cardView;
        }
    }
}
