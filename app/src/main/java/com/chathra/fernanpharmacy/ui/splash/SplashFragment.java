package com.chathra.fernanpharmacy.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.chathra.fernanpharmacy.R;


public class SplashFragment extends Fragment {

//    UserStore userStore;
//    AppStore appStore;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_splash, container, false);

//        if(appStore == null){
//            appStore  = new AppStore(getActivity());
//        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                appStore.open();
//                int count = appStore.getCount();
//                System.out.println("count " + count);
//                appStore.close();

//                if(count == 0){
//                    appStore.open();
//                  appStore.insertNew();
//                    appStore.close();
//
//                    Intent intent = new Intent(requireActivity(), OnboardingActivity.class);
//                    startActivity(intent);
//                    getActivity().finish();
//                }else {
//
                    NavDirections action = SplashFragmentDirections.actionSplashFragmentToSignInFragment2();
                    Navigation.findNavController(view).navigate(action);


//                }
            }
        }, 1000);

        return view;
    }




}