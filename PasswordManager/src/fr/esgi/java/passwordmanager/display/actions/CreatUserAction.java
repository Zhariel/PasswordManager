package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;
import fr.esgi.java.passwordmanager.managers.UserManager;

import java.util.Scanner;

public class CreatUserAction implements IAction {

    private static final int numberInput = 4;
    private Form creatUserForm;

    public CreatUserAction() {

        creatUserForm = new Form("Creation d'un utilisateur");
        creatUserForm.addInstructionsForm("Saisissez un login");
        creatUserForm.addInstructionsForm("Saisissez un mdp");
        creatUserForm.addInstructionsForm("Saisissez de nouveau votre mdp");
        creatUserForm.addInstructionsForm("Saisissez votre mail");
    }

    @Override
    public boolean run() {
        launchForm();

         UserManager userManager = new UserManager();
         boolean feedBackAction = userManager.addUser(creatUserForm.getInputsForm());

        creatUserForm.emptyList();
        return true;
    }


    @Override
    public void launchForm() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + creatUserForm.getTitle() + "\n");

        for(int i=0;i<numberInput;i++){
            System.out.print(creatUserForm.getInstructionsForm().get(i) +" : ");
            creatUserForm.addInputsForm(scanner.nextLine());
        }

        System.out.println();
    }
}
