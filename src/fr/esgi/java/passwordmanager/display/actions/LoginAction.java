package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class LoginAction implements IAction {

    private static final int numberInput = 2;
    private Form loginForm;

    public LoginAction(){
        loginForm = new Form("Cration d'un utilisateur");
        loginForm.getInstructionsForm().add("Saisissez votre login");
        loginForm.getInstructionsForm().add("Saisissez votre mdp");
    }

    @Override
    public boolean run() {
        launchForm();
        // Envoie la liste d'inputs ("loginForm.getInputsForm()") à login exemple :
        // SiteManager siteManager = new SiteManager();
        // boolean feedBackAction = siteManager->login(loginForm.getInputsForm());

        //FORMAT de la liste d'inputs : ["id utilisateur", "mdp"]
        //Tu peux aussi voir les champs affichés juste au-dessus dans la fonction loginAction() (constructeur de cette classe.)

        return true; // return feedBackAction

    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<numberInput;i++){
            System.out.print(loginForm.getInstructionsForm().get(i) +" : ");
            loginForm.getInputsForm().add(scanner.nextLine());
            System.out.println();
        }
    }


}