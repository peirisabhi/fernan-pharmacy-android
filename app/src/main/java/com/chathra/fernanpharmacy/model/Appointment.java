package com.chathra.fernanpharmacy.model;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    Long id;

    String date;

    String time;

    String doctor;

    String specialities;

    String status;

    String payment;

    String patient;

    public Appointment() {
    }

    public Appointment(Long id, String date, String time, String doctor, String specialities, String status, String payment) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.specialities = specialities;
        this.status = status;
        this.payment = payment;
    }

    public Appointment(Long id, String date, String time, String doctor, String specialities, String status, String payment, String patient) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.specialities = specialities;
        this.status = status;
        this.payment = payment;
        this.patient = patient;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
