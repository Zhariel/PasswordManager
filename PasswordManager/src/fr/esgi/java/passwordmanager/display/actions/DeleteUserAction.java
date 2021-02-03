package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class DeleteUserAction implements IAction {

    private static final int numberInput = 2;
    private Form deleteUserForm;

    public DeleteUserAction(){
        deleteUserForm = new Form("Suppression d'un utilisateur");
        deleteUserForm.getInstructionsForm().add("Nom de l'utilisateur a supprimer");
        deleteUserForm.getInstructionsForm().add("Son mdp");

    }

    @Override
    public boolean run() {
        launchForm();
        boolean feedBackAction = Session.getInstance().getInstanceUserManager().initDeleteUser(deleteUserForm.getInputsForm());

        deleteUserForm.emptyList();
        return feedBackAction;

    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(deleteUserForm.getTitle() + "\n");
        for (int i = 0; i < numberInput; i++) {
            System.out.print(deleteUserForm.getInstructionsForm().get(i) + " : ");
            deleteUserForm.getInputsForm().add(scanner.nextLine());
        }
        System.out.println("\n");

    }
}
