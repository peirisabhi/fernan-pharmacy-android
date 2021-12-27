package com.chathra.fernanpharmacy.ui.patientAppointment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PatientAppointmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PatientAppointmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }



}