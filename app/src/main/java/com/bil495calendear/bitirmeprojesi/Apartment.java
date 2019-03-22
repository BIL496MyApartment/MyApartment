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
        this.userIDList.add("First");
        this.apartmentID = apartmentID;
        this.userIDList.add(uid);
        this.apartmentName = apartmentName;
        this.Adress=adress;
        this.adminID = uid;
        this.City = cityName;

    }

    public Apartment(String apartmentName,String cityName,String adress,List<String> userIDList,String adminID,String userid, String apartmentID){// For Updating Apartment


        this.userIDList = new ArrayList<String>();
        this.userIDList = userIDList;
        this.apartmentID = apartmentID;
        this.userIDList.add(userid);
        this.apartmentName = apartmentName;
        this.Adress = adress;
        this.adminID = adminID;
        this.City = cityName;

    }
    public Apartment(List<String> userIDList,String apartmentName,String cityName,String adress,String adminID, String apartmentID){//update values for popup

        this.userIDList = new ArrayList<String>();
        this.userIDList = userIDList;
        this.apartmentID = apartmentID;
        this.apartmentName = apartmentName;
        this.Adress = adress;
        this.adminID = adminID;
        this.City = cityName;



    }

    public Apartment(String apartmentName,String cityName,String adress,List<String> userIDList,String adminID, String apartmentID){//For remove an user from apartment

        this.userIDList = new ArrayList<String>();
        this.userIDList = userIDList;
        this.apartmentID = apartmentID;
        this.apartmentName = apartmentName;
        this.Adress = adress;
        this.adminID = adminID;
        this.City = cityName;
    }

    public Apartment(String apartmentName,String cityName,String adress,List<String> userIDList, String apartmentID){//For remove Admin user from apartment

        this.userIDList = new ArrayList<String>();
        this.userIDList = userIDList;
        // eger user kalmışsa adminden sonra kayıt olan ilk kişi admin olur.Eğer yoksa apartmandan bütün userlar çıkmış olur fakat verileri databasede tutulmaya d
        if (userIDList.size()>1)
            this.adminID = userIDList.get(1);
        else
            this.adminID="";
        this.apartmentID = apartmentID;
        this.apartmentName = apartmentName;
        this.Adress = adress;
        this.City = cityName;
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
