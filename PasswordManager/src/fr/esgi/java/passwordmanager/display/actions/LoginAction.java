package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class LoginAction implements IAction {

    private static final int numberInput = 2;
    private Form loginForm;

    public LoginAction() {
        loginForm = new Form("\nIdentification");
        loginForm.getInstructionsForm().add("Saisissez votre login");
        loginForm.getInstructionsForm().add("Saisissez votre mdp");
    }

    @Override
    public boolean run() {
        launchForm();
        boolean feedBackAction = Session.getInstance().getInstanceUserManager().login(loginForm.getInputsForm());

        loginForm.emptyList();
        return feedBackAction;
    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(loginForm.getTitle() + "\n");
        for (int i = 0; i < numberInput; i++) {
            System.out.print(loginForm.getInstructionsForm().get(i) + " : ");
            loginForm.getInputsForm().add(scanner.nextLine());
        }
        System.out.println("\n");
    }


}
