package fr.esgi.java.passwordmanager.models;

import java.util.List;
public class User {
    private String name;
    public Password password;
    private String email;
    public List<Site> ListSites;

    public User(String name, Password password, String email, List<Site> ListSites) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.ListSites = ListSites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Site> getListSites() {
        return ListSites;
    }

    public void setListSites(List<Site> listSites) {
        ListSites = listSites;
    }
}
