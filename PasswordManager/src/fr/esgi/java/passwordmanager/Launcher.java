package fr.esgi.java.passwordmanager;

import fr.esgi.java.passwordmanager.display.menu.DisplayManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**PasswordManager Application
 * @author : Clara Florac
 * @author : Romain Blazevic
 * @author : Joel Chep
 * Date : 03/02/2021
 * Manager of passwords for private User.
 * */

public class Launcher {


    public static void main(String[] args) {

        //********************
        //***Initialization***
        //********************

        System.out.println("\nBienvenue !\n");
        DisplayManager entry = new DisplayManager(true);
        Scanner scanner = new Scanner(System.in);
        Session.getInstance();
        Session.getInstance().initUserManager();

        //********************
        //**Loop Application**
        //********************

        while (true) {

            try {
                System.out.print("Saisissez votre choix ( 1 a " + entry.getCurrentMenu().actionsMap.size() + ") : ");
                int actionNumber = scanner.nextInt();
                boolean feedBBack = entry.executeAction(actionNumber);
                entry.updateMenu(feedBBack, actionNumber);

            } catch (InputMismatchException ec) {

                System.out.println("Veuillez entrez un int");
                scanner.nextLine(); // empty the buffer, nextInt() doesn't it when an error occurs.

            } catch (Exception e) {

                System.out.println("Une erreur imprevue est survenue.");
            }
        }
    }

}


