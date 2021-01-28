package fr.esgi.java.passwordmanager.models;

import java.util.ArrayList;

public class User {

    String id;
    Password password;
    String email;
    private ArrayList<Site> listSites;


    public User(){
        id="Paul";
        listSites = new ArrayList<Site>();
        listSites.add(new Site("Amazon"));
        listSites.add(new Site("Babylon"));
        listSites.add(new Site("Cdiscount"));
        listSites.add(new Site("Decathlon"));
        listSites.add(new Site("Ecommerce"));
        listSites.add(new Site("Facebook"));
        listSites.add(new Site("Google"));
        listSites.add(new Site("Hotmail"));
        listSites.add(new Site("Italie"));
        listSites.add(new Site("Jordanie"));
        listSites.add(new Site("Kakao"));
        listSites.add(new Site("L'obs"));
        listSites.add(new Site("Nautiljion"));
        listSites.add(new Site("Topito"));
        listSites.add(new Site("Samsung"));
        listSites.add(new Site("Youtube"));
        listSites.add(new Site("Zao"));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setListSites(ArrayList<Site> listSites) {
        listSites = listSites;
    }

    public ArrayList<Site> getListSites() {
        return listSites;
    }

}
