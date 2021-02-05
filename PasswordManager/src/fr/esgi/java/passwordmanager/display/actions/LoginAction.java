package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class LoginAction implements IAction {

    private static final int numberInput = 2;
    private Form loginForm;

    public LoginAction() {
        loginForm = new Form("\nIdentification");
        loginForm.addInstructionsForm("Saisissez votre login");
        loginForm.addInstructionsForm("Saisissez votre mdp");
    }

    @Override
    public boolean run() {
        launchForm();
        boolean feedBackAction;
        try {
            feedBackAction = Session.getInstance().getInstanceUserManager().login(loginForm.getInputsForm());
        }catch(Exception e){
            feedBackAction = false;
        }
        loginForm.emptyList();
        return feedBackAction;
    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(loginForm.getTitle() + "\n");
        for (int i = 0; i < numberInput; i++) {
            System.out.print(loginForm.getInstructionsForm().get(i) + " : ");
            loginForm.addInputsForm(scanner.nextLine());
        }
        System.out.println("\n");
    }

}
