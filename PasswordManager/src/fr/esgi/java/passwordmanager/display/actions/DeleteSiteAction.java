package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;
import fr.esgi.java.passwordmanager.managers.SiteManager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DeleteSiteAction implements IAction {

    private static final int numberInput = 2;
    private Form deleteSiteForm;

    public DeleteSiteAction() {
        deleteSiteForm = new Form("Suppression d'un site");
        deleteSiteForm.getInstructionsForm().add("Saisissez le site a supprimer");
        deleteSiteForm.getInstructionsForm().add("Est-vous sur ? y/n");

        List<Integer> tmpCursorList = Arrays.asList(0, 1);
        deleteSiteForm.getCursor().addAll(tmpCursorList);
    }

    public boolean run() {
        launchForm();

        SiteManager siteManager = new SiteManager();
        boolean feedBackAction = siteManager.initDeleteSite(deleteSiteForm.getInputsForm());

        deleteSiteForm.emptyList();
        return feedBackAction;
    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(deleteSiteForm.getTitle() + "\n");

        for (int i = 0; i < numberInput; i++) {
            System.out.print(deleteSiteForm.getInstructionsForm().get(i) + " : ");

            if (deleteSiteForm.getCursor().get(i) != 0) {
                checkYesOrNoQuestion(scanner, i);
            } else {
                deleteSiteForm.getInputsForm().add(scanner.nextLine());
            }
        }

        System.out.println("\n");
    }

    public void checkYesOrNoQuestion(Scanner scanner, int index) {
        String tmpInput;

        do {

            tmpInput = scanner.nextLine();

            if (tmpInput.length() > 0) {
                tmpInput = tmpInput.substring(0, 1).toLowerCase();
            } else {
                tmpInput = "error";
                tmpInput = tmpInput.substring(0, 1).toLowerCase();
            }

            if (tmpInput.equals("y")) {
                deleteSiteForm.getInputsForm().add(tmpInput);
            } else if (tmpInput.equals("n")) {
                deleteSiteForm.getInputsForm().add(tmpInput);
                deleteSiteForm.fillInputFormArrayListWhitNAValues(deleteSiteForm.getCursor().get(index));
                index += deleteSiteForm.getCursor().get(index);
            } else {
                System.out.print(deleteSiteForm.getInstructionsForm().get(index) + " : ");
            }
        } while (!tmpInput.equals("y") && !tmpInput.equals("n"));

    }

}
