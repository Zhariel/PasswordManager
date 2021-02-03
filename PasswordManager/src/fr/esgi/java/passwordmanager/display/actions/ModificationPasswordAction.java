package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.display.menu.model.Form;
import java.util.Scanner;

public class ModificationPasswordAction implements IAction {

    private static final int numberInput = 4;
    private Form modificationPasswordForm;

    public ModificationPasswordAction() {
        modificationPasswordForm = new Form("Modification mot de passe");
        modificationPasswordForm.addInstructionsForm("Saisissez votre identifiant");
        modificationPasswordForm.addInstructionsForm("Saisissez votre ancien mdp");
        modificationPasswordForm.addInstructionsForm("Saisissez votre nouveau mdp");
        modificationPasswordForm.addInstructionsForm("Confirmation nouveau mdp");
    }

    public boolean run() {
        launchForm();

        boolean feedBackAction = Session.getInstance().getInstanceUserManager().modificationPassword(modificationPasswordForm.getInputsForm());

        modificationPasswordForm.emptyList();
        return feedBackAction;
    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + modificationPasswordForm.getTitle() + "\n");
        for (int i = 0; i < numberInput; i++) {
            System.out.print(modificationPasswordForm.getInstructionsForm().get(i) + " : ");
            modificationPasswordForm.addInputsForm(scanner.nextLine());
        }
        System.out.println();
    }
}
