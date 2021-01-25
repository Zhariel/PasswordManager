package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class CreatUserAction implements IAction {

    private static final int numberInput = 4;
    private Form creatUserForm;

    public CreatUserAction() {
        creatUserForm = new Form("Cration d'un utilisateur");
        creatUserForm.getInstructionsForm().add("Saisissez un login");
        creatUserForm.getInstructionsForm().add("Saisissez un mdp");
        creatUserForm.getInstructionsForm().add("Saisissez de nouveau votre mdp");
        creatUserForm.getInstructionsForm().add("Saisissez votre mail");
    }

    @Override
    public boolean run() {
        launchForm();
        // Envoie la liste d'inputs ("creatUserForm.getInputsForm()") à UserManager exemple :
        // UserManager userManager = new UserManager();
        // boolean feedBackAction = userManager->addUser(creatUserForm.getInputsForm());

        //FORMAT de la liste d'inputs : ["login","mdp","confirmation mdp","mail"]
        // Tu peux aussi voir les champs affichés juste au-dessus dans la fonction CreatUserAction() (constructeur de cette classe.)
        creatUserForm.emptyList();
        return true; //return feedBackAction
    }


    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<numberInput;i++){
            System.out.print(creatUserForm.getInstructionsForm().get(i) +" : ");
            creatUserForm.getInputsForm().add(scanner.nextLine());
            System.out.println();
        }

    }
}
