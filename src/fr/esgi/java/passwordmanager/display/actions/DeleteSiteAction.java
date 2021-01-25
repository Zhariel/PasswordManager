package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;

import java.util.Scanner;

public class DeleteSiteAction implements IAction {

    private static final int numberInput = 2;
    private Form deleteSiteForm;

    public DeleteSiteAction(){
        deleteSiteForm = new Form("Suppression d'un site");
        deleteSiteForm.getInstructionsForm().add("Saisissez le site a supprimer");
        deleteSiteForm.getInstructionsForm().add("Est-vous sur ?");
    }

    public boolean run() {
        launchForm();
        // Envoie la liste d'inputs ("deleteSiteForm.getInputsForm()") à SiteManager exemple :
        // SiteManager siteManager = new SiteManager();
        // boolean feedBackAction = siteManager->deleteSite(deleteSiteForm.getInputsForm());

        //FORMAT de la liste d'inputs : ["nomSite","confirmation y/n"]  'tu recois tj un petit "y" ou un petit "n" pour les confirmations ou personalisation
        // Tu peux aussi voir les champs affichés juste au-dessus dans la fonction DeleteSiteAction() (constructeur de cette classe.)
        deleteSiteForm.emptyList();
        return true; //return feedBackAction
    }

    @Override
    public void launchForm() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<numberInput;i++){
            System.out.print(deleteSiteForm.getInstructionsForm().get(i) +" : ");
            deleteSiteForm.getInputsForm().add(scanner.nextLine());
            System.out.println();
        }

    }
}
