package fr.esgi.java.passwordmanager.models;

public class Constraint {

    int totalLength;
    int upperCase;
    int lowerCase;
    int specialChars;
    int digits;

    public Constraint(){

    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getUpperCase() {
        return upperCase;
    }

    public void setUpperCase(int upperCase) {
        this.upperCase = upperCase;
    }

    public int getLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(int lowerCase) {
        this.lowerCase = lowerCase;
    }

    public int getSpecialChars() {
        return specialChars;
    }

    public void setSpecialChars(int specialChars) {
        this.specialChars = specialChars;
    }


    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "totalLength=" + totalLength +
                ", upperCase=" + upperCase +
                ", lowerCase=" + lowerCase +
                ", specialChars=" + specialChars +
                ", digits=" + digits +
                '}';
    }
}
