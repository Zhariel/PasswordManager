package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class ModificationPasswordAction implements IAction {

    private static final int numberInput = 3;
    private Form modificationPasswordForm;

    public ModificationPasswordAction() {
        modificationPasswordForm = new Form("Modification mot de passe");
        modificationPasswordForm.getInstructionsForm().add("Saisissez votre ancien mdp");
        modificationPasswordForm.getInstructionsForm().add("Saisissez votre nouveau mdp");
        modificationPasswordForm.getInstructionsForm().add("Confirmation nouveau mdp");
    }

    public boolean run() {
        launchForm();

        // Envoie la liste d'inputs ("modificationPasswordForm.getInputsForm()") à PasswordManager exemple :  [est ce que l'on créer un PasswordManager? Comme ca la classe Password reste une classe container]
        // PasswordManager passwordManager = new PasswordManager();
        // boolean feedBackAction = PasswordManager->modificationPassword(modificationPasswordForm.getInputsForm());

        //FORMAT de la liste d'inputs : ["ancien mdp", "nouveau mdp","nouveau mdp"]
        // Tu peux aussi voir les champs affichés juste au-dessus dans la fonction ModificationPasswordAction() (constructeur de cette classe.)

        modificationPasswordForm.emptyList();
        return true; //return feedBackAction
    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<numberInput;i++){
            System.out.print(modificationPasswordForm.getInstructionsForm().get(i) +" : ");
            modificationPasswordForm.getInputsForm().add(scanner.nextLine());
            System.out.println();
        }
    }
}
