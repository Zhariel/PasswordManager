package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.DisplayManager;
import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class DisplayOneSiteAction implements IAction {

    private static final int numberInput = 1;
    private Form displayOneSiteForm;

    public DisplayOneSiteAction() {

        displayOneSiteForm = new Form("Affichage d'un site");
        displayOneSiteForm.getInstructionsForm().add("Saisissez le nom du site a afficher ");

    }

    public boolean run() {

        launchForm();
        DisplayManager entry = new DisplayManager(false);
        return entry.displayOneSite(displayOneSiteForm);
    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numberInput; i++) {
            System.out.print(displayOneSiteForm.getInstructionsForm().get(i) + " : ");
            displayOneSiteForm.getInputsForm().add(scanner.nextLine());
        }
        System.out.println("\n");
    }
}
