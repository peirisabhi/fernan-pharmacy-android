package com.chathra.fernanpharmacy.ui.orderSuccess;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chathra.fernanpharmacy.R;
import com.chathra.fernanpharmacy.databinding.FragmentOrderSuccessBinding;
import com.chathra.fernanpharmacy.ui.bookingSuccess.BookingSuccessFragmentDirections;

import org.jetbrains.annotations.NotNull;


public class OrderSuccessFragment extends Fragment {

    FragmentOrderSuccessBinding binding;
    OrderSuccessFragmentArgs args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = OrderSuccessFragmentArgs.fromBundle(getArguments());

        binding.textView14.setText(Html.fromHtml(args.getText()));

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections navDirections = OrderSuccessFragmentDirections.actionOrderSuccessFragmentToNavigationShop();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        });
    }
}