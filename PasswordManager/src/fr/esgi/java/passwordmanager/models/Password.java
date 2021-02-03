package fr.esgi.java.passwordmanager.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

public class Password {

    private String password;
    private boolean master;
    private static final int KEY =13;
    private ArrayList<Character> charSpecial;

    public Password(String password,Constraint constraints, boolean master) {

        initCharSpecialArray();
        this.master = master;

        if(!master) {
            if (password != null) {
                this.password = password;
            } else {
                if(!constraints.constraintsValidity()){
                    constraints.resetConstraints();
                }
                this.password = creatPassword(constraints);
            }
        }else{
            this.password=password;
        }

    }

    public Password(String password,boolean master){
        initCharSpecialArray();
        this.master = master;
        this.password = password;
    }

    public boolean getMaster(){
        return this.master;
    }

    public void initCharSpecialArray(){

        this.charSpecial = new ArrayList<Character>();
        List<Character> tmpcharSpecial = Arrays.asList('&', '~', '\"', '#', '\'', '{', '}', '(', ')', '[', ']', '-', '|', '`', '_', '^', '@', '=', '+', '!', '§', '%', '*', '£', '$', '¤', '?', '/', '\\', ',', '.', ';', ':', '<', '>', 'é', 'è', 'à', 'ù', 'ç', '²', '°', 'µ');
        this.charSpecial.addAll(tmpcharSpecial);

    }


    private String creatPassword(Constraint constraints) {

        StringBuilder newPassword= new StringBuilder();

        for(int i=0;i<constraints.getUpperCase();i++){
            newPassword.append(generateRandomUpperCase());
        }

        for(int i=0;i<constraints.getLowerCase()+constraints.getLetter();i++){
            newPassword.append(generateRandomLowerCase());
        }

        for(int i = 0; i<constraints.getSpecialChar(); i++){
            newPassword.append(generateRandomCharSpecial());
        }

        for(int i = 0; i<constraints.getDigit(); i++){
            newPassword.append(generateRandomInteger(0,9));
        }

        if(newPassword.length()!=constraints.getTotalLength()){
            for(int i=0;i<constraints.getTotalLength()-newPassword.length();i++){
                newPassword.append(generateRandomLowerCase());
            }
        }

        return mixString(newPassword.toString());

    }

    private String mixString(String newPassword) {

        String mix="";
        ArrayList<Integer> listIndex= new ArrayList<>();
        int index;

        for(int i=0;i<newPassword.length();i++){
            listIndex.add(i);
        }

        for(int i=0;i<newPassword.length();i++){

            index = generateRandomInteger(0,newPassword.length()-1);

            while(!listIndex.contains(index)){
                index = generateRandomInteger(0,newPassword.length()-1);
            }

            mix+= newPassword.charAt(index);
            listIndex.remove(listIndex.indexOf(index));
        }

        return mix;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String encryption(String password){

        StringBuilder encrypt = new StringBuilder();
        int ascii;

        for(int i=0;i<password.length();i++){

            if(charSpecial.contains(password.charAt(i))){
                encrypt.append(charSpecial.get((charSpecial.indexOf(password.charAt(i)) + KEY)% charSpecial.size()));
            }else if(isLowerCase(password.charAt(i)) || isUpperCase(password.charAt(i)) ){
                ascii = (int)password.charAt(i);
                ascii = (ascii + KEY)%26;
                encrypt.append((char)ascii);
            }else if(isInterger(password.charAt(i))){
                ascii = (Integer.parseInt(String.valueOf((password.charAt(i)))+ KEY)%10);
                encrypt.append((char)ascii);
            }else{
                encrypt.append(password.charAt(i));
            }

        }

        return encrypt.toString();
    }

    public String decryption(String password){

        StringBuilder decrypt = new StringBuilder();
        int ascii;

        for(int i=0;i<password.length();i++){

            if(charSpecial.contains(password.charAt(i))){
                decrypt.append(charSpecial.get((charSpecial.indexOf(password.charAt(i))- KEY)% charSpecial.size()));
            }else if(isLowerCase(password.charAt(i)) || isUpperCase(password.charAt(i)) ){
                ascii = (int)password.charAt(i);
                ascii = (ascii- KEY)%26;
                decrypt.append((char)ascii);
            }else if(isInterger(password.charAt(i))){
                ascii = Integer.parseInt(String.valueOf((password.charAt(i)- KEY)%10));
                decrypt.append((char)ascii);
            }else{
                decrypt.append(password.charAt(i));
            }

        }

        return decrypt.toString();
    }

    public static boolean isInterger(char charNum) {

        try {
            int integer = Integer.parseInt(String.valueOf(charNum));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public char generateRandomLowerCase(){
        Random rand = new Random();

        return (char)(rand.nextInt(26) + 97);
    }

    public char generateRandomUpperCase(){
        Random rand = new Random();

        return (char)(rand.nextInt(26) + 65);
    }

    public int generateRandomInteger(int min, int max){
        Random rand = new Random();

        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public char generateRandomCharSpecial(){
        Random rand = new Random();
        int index = (int)(Math.random()*charSpecial.size());

        return charSpecial.get(index);
    }

}
