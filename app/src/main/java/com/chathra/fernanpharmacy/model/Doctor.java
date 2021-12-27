package com.chathra.fernanpharmacy.model;

import java.io.Serializable;
import java.util.Date;

public class Doctor implements Serializable {
    String name;
    String email;
    String password;
    String mobile;
    String nic;
    Date dob;
    String gender;
    String country;
    String district;
    String city;
    String address;
    String zipCode;
    String approvalStatus;
    String about;
    double price;
    String priceType;
    String image;
    boolean isVideoOk;
    String experience;
    String specialist;
    double lat;
    double lan;
    boolean isActive;
    boolean isOnline;
    boolean isBlock;
    double rating;
    int totalComments;
    Date registerdAt;
    String doctorDocumentId;

    public Doctor() {
    }

    public Doctor(String name, String email, String password, String mobile, String nic, Date dob, String gender, String country, String district, String city, String address, String zipCode, String approvalStatus, String about, double price, String priceType, String image, boolean isVideoOk, String experience, String specialist, double lat, double lan, boolean isActive, boolean isOnline, boolean isBlock, double rating, int totalComments, Date registerdAt, String doctorDocumentId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.nic = nic;
        this.dob = dob;
        this.gender = gender;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.approvalStatus = approvalStatus;
        this.about = about;
        this.price = price;
        this.priceType = priceType;
        this.image = image;
        this.isVideoOk = isVideoOk;
        this.experience = experience;
        this.specialist = specialist;
        this.lat = lat;
        this.lan = lan;
        this.isActive = isActive;
        this.isOnline = isOnline;
        this.isBlock = isBlock;
        this.rating = rating;
        this.totalComments = totalComments;
        this.registerdAt = registerdAt;
        this.doctorDocumentId = doctorDocumentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVideoOk() {
        return isVideoOk;
    }

    public void setVideoOk(boolean videoOk) {
        isVideoOk = videoOk;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public Date getRegisterdAt() {
        return registerdAt;
    }

    public void setRegisterdAt(Date registerdAt) {
        this.registerdAt = registerdAt;
    }

    public String getDoctorDocumentId() {
        return doctorDocumentId;
    }

    public void setDoctorDocumentId(String doctorDocumentId) {
        this.doctorDocumentId = doctorDocumentId;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nic='" + nic + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", about='" + about + '\'' +
                ", price=" + price +
                ", priceType='" + priceType + '\'' +
                ", image='" + image + '\'' +
                ", isVideoOk=" + isVideoOk +
                ", experience='" + experience + '\'' +
                ", specialist='" + specialist + '\'' +
                ", lat=" + lat +
                ", lan=" + lan +
                ", isActive=" + isActive +
                ", isOnline=" + isOnline +
                ", isBlock=" + isBlock +
                ", rating=" + rating +
                ", totalComments=" + totalComments +
                ", registerdAt=" + registerdAt +
                ", doctorDocumentId='" + doctorDocumentId + '\'' +
                '}';
    }
}
