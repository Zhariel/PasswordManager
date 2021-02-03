package fr.esgi.java.passwordmanager.models;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class Site
 * This class contains name,password,idUser,constraints and metaData.
 **/

public class Site {

    private String name;
    private Password password;
    private String idUser;
    public Constraint constraint;
    public Metadata metaData;


    /**
     * Constructor
     * It's used when a site is created from fileData
     **/
    public Site(String name, Password password, String idUserInSite, Constraint constraint, Metadata metaData) {
        this.name = name;
        this.password = password;
        this.idUser = idUserInSite;
        this.constraint = constraint;
        this.metaData = metaData;
    }

    /**
     * Constructor
     * It's used when a site is created from inputs user.
     **/
    public Site(ArrayList<String> listInputsFormCreatSite) {

        this.name = listInputsFormCreatSite.get(0);
        this.idUser = listInputsFormCreatSite.get(7);

        if (listInputsFormCreatSite.get(1).equals("y")) {
            this.constraint = new Constraint(listInputsFormCreatSite.subList(2, 7));
        } else {
            this.constraint = new Constraint(); //default constructor set values totalLength : 8, Upper : 1, lower : 1, speChar : 1, digit : 1, letters : 4
        }

        //User chooses to entry a password (entry yes)
        if (listInputsFormCreatSite.get(8).equals("y")) {
            this.password = new Password(listInputsFormCreatSite.get(9), this.constraint, false);
        } else {
            //Application generates the password.
            this.password = new Password(this.constraint, false);
        }

        LocalDate date = LocalDate.now();
        this.metaData = new Metadata(date, listInputsFormCreatSite.get(13), listInputsFormCreatSite.get(11));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password.getPassword();
    }

    public void setPassword(String newPassword) {
        this.password.setPassword(newPassword);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Site{\n" +
                " site='" + name + '\'' +
                ",\n password='" + password.getPassword() + '\'' +
                ",\n idUserInSite='" + idUser + '\'' +
                ",\n" + constraint.toString() +
                ",\n" + metaData.toString() +
                "\n}";
    }
}
