package fr.esgi.java.passwordmanager.models;

import java.util.ArrayList;
import java.util.List;
public class User {
    private String name;
    public Password password;
    private String email;
    public List<Site> listSites;

    public User(String name, Password password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.listSites = new ArrayList<>();
    }

    public User(String name, Password password, String email,List<Site> listSites) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.listSites = listSites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {

        Password tmpPassword = new Password(this.password.getPassword(),this.password.getMaster());

        return tmpPassword;
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

    public List<Site> getListSites() {

        ArrayList<Site> tmpListSites = new ArrayList<>();
        tmpListSites.addAll(this.listSites);

        return listSites;
    }

    public void setListSites(List<Site> listSites) {
        this.listSites = listSites;
    }
}
