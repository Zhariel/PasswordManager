package fr.esgi.java.passwordmanager.models;

import java.util.List;

/**
 * Class Constraint
 * Class containing the number of :
 * totalLength;
 * upperCase;
 * lowerCase;
 * letter;
 * specialChar;
 * digit, of a password, imposed by a Site.
 * Constaints belong to a Site.
 **/

public class Constraint {

    private int totalLength;
    private int upperCase;
    private int lowerCase;
    private int letter; // Calcul : totalLength - (upperCase + lowerCase + specialChar + digit);
    private int specialChar;
    private int digit;

    /**
     * Constructor
     * It's use only in the fonction jsonTojava.
     **/
    public Constraint(int totalLength, int upperCase, int lowerCase, int letter, int specialChar, int digit) {

        this.totalLength = totalLength;
        this.upperCase = upperCase;
        this.lowerCase = lowerCase;
        this.specialChar = specialChar;
        this.digit = digit;
        this.letter = letter;
    }

    /**
     * Constructor
     * It's use when a Site is creat and constaints have not entry by the User.
     * It's a default constructor.
     **/
    public Constraint() {

        this.totalLength = 8;
        this.upperCase = 1;
        this.lowerCase = 1;
        this.specialChar = 1;
        this.digit = 1;
        this.letter = 4;
    }

    /**
     * Constructor
     * It's use when a Site is creat and constaints have been entry by the User.
     **/
    public Constraint(List<String> listConstraints) {

        this.totalLength = Integer.parseInt(listConstraints.get(0));
        this.upperCase = Integer.parseInt(listConstraints.get(1));
        this.lowerCase = Integer.parseInt(listConstraints.get(2));
        this.specialChar = Integer.parseInt(listConstraints.get(3));
        this.digit = Integer.parseInt(listConstraints.get(4));
        this.letter = this.totalLength - (this.upperCase + this.lowerCase + this.specialChar + this.digit);
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

    public int getLetter() {
        return letter;
    }

    public void setLetter(int letter) {
        this.letter = letter;
    }

    public int getSpecialChar() {
        return specialChar;
    }

    public void setSpecialChar(int specialChar) {
        this.specialChar = specialChar;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    @Override
    public String toString() {
        return "\nConstraint{" +
                "\n logueur total=" + totalLength +
                ",\n majuscule =" + upperCase +
                ",\n minuscule =" + lowerCase +
                ",\n lettres = " + letter +
                ",\n charactere special =" + specialChar +
                ",\n chiffre =" + digit +
                "\n}";
    }

    public boolean constraintsValidity() {
        return this.upperCase + this.lowerCase + this.specialChar + this.digit + this.letter == this.totalLength;
    }

    /**
     * Function resetConstraints
     * In case of constraintsValidity return false.
     **/

    public void resetConstraints() {

        this.totalLength = 8;
        this.upperCase = 1;
        this.lowerCase = 1;
        this.specialChar = 1;
        this.digit = 1;
        this.letter = this.totalLength - (this.upperCase + this.lowerCase + this.specialChar + this.digit);

    }
}