package com.chathra.fernanpharmacy.ui.patientHome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PatientHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PatientHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}