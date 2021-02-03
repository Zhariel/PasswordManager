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
        deleteSiteForm.addInstructionsForm("Saisissez le site a supprimer");
        deleteSiteForm.addInstructionsForm("Est-vous sur ? y/n");

        List<Integer> tmpCursorList = Arrays.asList(0, 1);
        deleteSiteForm.addCursor(tmpCursorList);
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
                deleteSiteForm.checkYesOrNoQuestion(scanner,i);
            } else {
                deleteSiteForm.addInputsForm(scanner.nextLine());
            }
        }

        System.out.println("\n");
    }

}
