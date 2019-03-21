package com.bil495calendear.bitirmeprojesi;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Apartment {

    FirebaseUser firebaseUser;

    private List<String> userIDList ;
    private String apartmentID;
    private String adminID;
    private String City;
    private String Adress;
    private String apartmentName;


    public Apartment(String apartmentName,String cityName,String adress,String uid, String apartmentID) {// create apartment

        this.userIDList = new ArrayList<String>();
        this.apartmentID = apartmentID;
        this.userIDList.add(uid);
        this.apartmentName = apartmentName;
        this.Adress=adress;
        this.adminID = uid;

    }

    public Apartment(String apartmentName,String cityName,String adress,List<String> userIDList,String adminID,String userid, String apartmentID){// For Updating Apartment


        this.userIDList = new ArrayList<String>();
        this.userIDList = userIDList;
        this.apartmentID = apartmentID;
        this.userIDList.add(userid);
        this.apartmentName = apartmentName;
        this.Adress=adress;
        this.adminID = adminID;


    }

    public Apartment(){}

    public List<String> getUserIDList() {
        return userIDList;
    }

    public void setUserIDList(List<String> userIDList) {
        this.userIDList = userIDList;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }



}
