package fr.esgi.java.passwordmanager.models;

import java.util.ArrayList;
import java.util.List;


/** Class User
 * Contains informations about user : name,password, email anf listSites.
 **/

public class User {

    private String name;
    public Password password;
    private String email;
    public List<Site> listSites;

    /**
     * Constructor
     * It's used when a new user is created or add to a file or a list.
     **/
    public User(String name, Password password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.listSites = new ArrayList<>();
    }

    /**
     * Constructor
     * It's used when a copy of an user is necessary
     **/
    public User(String name, Password password, String email, List<Site> listSites) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.listSites = listSites;
    }

    public void removeSiteOnListSites(int index) {
        this.listSites.remove(index);
    }

    public void addSiteOnListSites(Site newSite) {
        this.listSites.add(newSite);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {

        return new Password(this.password.getPassword(), this.password.getMaster());
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
