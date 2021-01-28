package fr.esgi.java.passwordmanager.models;

public class Password {
    String password;
    Enum Master;

    public Password(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                '}';
    }
}
