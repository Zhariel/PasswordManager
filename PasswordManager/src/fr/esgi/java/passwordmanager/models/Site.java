package fr.esgi.java.passwordmanager.models;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Site {
    private String name;
    private Password password;
    private String idUser;
    public Constraint constraint;
    public Metadata metaData;


    public Site(String name, Password password, String idUserInSite, Constraint constraint, Metadata metaData){
        this.name = name;
        this.password = password;
        this.idUser = idUserInSite;
        this.constraint = constraint;
        this.metaData = metaData;
    }


    public Site(ArrayList<String> listInputsFormCreatSite){

        this.name = listInputsFormCreatSite.get(0);
        this.idUser = listInputsFormCreatSite.get(7);

        if(listInputsFormCreatSite.get(1).equals("y")){
            this.constraint = new Constraint(listInputsFormCreatSite.subList(2,7));
        }else{
            this.constraint = new Constraint(); //default constructor set values total : 8, Upper : 1, lower : 1, speChar : 1, digit : 1, letters : 4
        }

        if(listInputsFormCreatSite.get(8).equals("y")){
            this.password = new Password(listInputsFormCreatSite.get(9),this.constraint,false);
        }else{
            this.password = new Password(null,this.constraint,false);
        }

        LocalDate date = LocalDate.now();
        this.metaData = new Metadata(date,listInputsFormCreatSite.get(13),listInputsFormCreatSite.get(11));

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
