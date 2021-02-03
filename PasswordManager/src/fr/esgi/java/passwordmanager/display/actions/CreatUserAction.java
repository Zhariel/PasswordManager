package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;
import fr.esgi.java.passwordmanager.managers.UserManager;

import java.util.Scanner;

public class CreatUserAction implements IAction {

    private static final int numberInput = 4;
    private Form creatUserForm;

    public CreatUserAction() {

        creatUserForm = new Form("Creation d'un utilisateur");
        creatUserForm.getInstructionsForm().add("Saisissez un login");
        creatUserForm.getInstructionsForm().add("Saisissez un mdp");
        creatUserForm.getInstructionsForm().add("Saisissez de nouveau votre mdp");
        creatUserForm.getInstructionsForm().add("Saisissez votre mail");
    }

    @Override
    public boolean run() {
        launchForm();

        UserManager userManager = null;
        try {
            userManager = new UserManager();
        } catch (Exception e) {
            System.out.println("Impossible de créer l'utilisateur.");
        }
        try {
            boolean feedBackAction = userManager.addUser(creatUserForm.getInputsForm());
        } catch (Exception e) {
            System.out.print("Impossible de supprimer l'utilisateur.q");
        }

        creatUserForm.emptyList();
        return true;
    }


    @Override
    public void launchForm() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + creatUserForm.getTitle() + "\n");

        for(int i=0;i<numberInput;i++){
            System.out.print(creatUserForm.getInstructionsForm().get(i) +" : ");
            creatUserForm.getInputsForm().add(scanner.nextLine());
        }

        System.out.println();
    }
}
