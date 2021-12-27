package com.chathra.fernanpharmacy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.chathra.fernanpharmacy.db.UserStore;
import com.chathra.fernanpharmacy.model.Doctor;
import com.chathra.fernanpharmacy.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DoctorActivity extends AppCompatActivity {

    public Doctor doctor;
    public String doctorDocumentId;

    User currentUser;

    UserStore userStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        Intent intent = getIntent();
        doctor = (Doctor) intent.getSerializableExtra("doctor");
        doctorDocumentId = intent.getStringExtra("doctorDocId");

//        System.out.println("doctor " + doctor.toString());
//        System.out.println("doctorDocId -- " + doctorDocumentId);

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