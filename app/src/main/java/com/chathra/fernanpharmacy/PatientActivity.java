package com.chathra.fernanpharmacy;

import android.os.Bundle;

import com.chathra.fernanpharmacy.databinding.ActivityPatientBinding;
import com.chathra.fernanpharmacy.db.UserStore;
import com.chathra.fernanpharmacy.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;


public class PatientActivity extends AppCompatActivity {

    private ActivityPatientBinding binding;



    User currentUser;
    UserStore userStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPatientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (userStore == null) {
            userStore = new UserStore(getApplicationContext());
        }

        userStore.open();
        ArrayList<User> users = userStore.getUsers();
        System.out.println("users -- " + users.size());
        if(users.size() != 0){
            currentUser = users.get(0);
            System.out.println(currentUser.getType() + " --- " + currentUser.getId());
        }
        userStore.close();
    }
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}