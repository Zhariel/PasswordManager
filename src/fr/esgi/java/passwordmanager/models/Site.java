package fr.esgi.java.passwordmanager.models;

public class Site {

    String name;
    String password;
    String idUser;
    Constraint constraints;
    Metadata metaData;

    public Site(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Constraint getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraint constraints) {
        this.constraints = constraints;
    }

    public Metadata getMetaData() {
        return metaData;
    }

    public void setMetaData(Metadata metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return "Site{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", idUser='" + idUser + '\'' +
                ", constraints=" + constraints.toString() +
                ", metaData=" + metaData +
                '}';
    }
}
