package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;
import fr.esgi.java.passwordmanager.managers.SiteManager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddSiteAction implements IAction {

    private static final int numberInput = 14;
    private Form creatSiteForm;

    public AddSiteAction() {

        creatSiteForm = new Form("Ajout d'un site");
        creatSiteForm.getInstructionsForm().add("Saisissez le nom du site");
        creatSiteForm.getInstructionsForm().add("Voulez-vous personnaliser les contraintes y/n");
        creatSiteForm.getInstructionsForm().add("Longueur mdp");
        creatSiteForm.getInstructionsForm().add("Nombre de majuscules");
        creatSiteForm.getInstructionsForm().add("Nombre de minuscules");
        creatSiteForm.getInstructionsForm().add("Nombre de characteres speciaux");
        creatSiteForm.getInstructionsForm().add("Nombre de chiffres");
        creatSiteForm.getInstructionsForm().add("Saisissez l'identifiant du site");
        creatSiteForm.getInstructionsForm().add("Voulez-vous generer votre mdp automatiquement y/n");
        creatSiteForm.getInstructionsForm().add("Saisissez le mdp");
        creatSiteForm.getInstructionsForm().add("Voulez-vous ajouter des metadonnees y/n");
        creatSiteForm.getInstructionsForm().add("Ajout commentaires ");
        creatSiteForm.getInstructionsForm().add("Voulez-vous un rappel y/n");
        creatSiteForm.getInstructionsForm().add("Ajout duree avant rappel (XXj/XXm/XXy) ");

        List<Integer> tmpCursorList = Arrays.asList(0, 5, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0);
        creatSiteForm.getCursor().addAll(tmpCursorList);

    }

    public boolean run() {

        launchForm();

        // Envoie la liste d'inputs ("creatSiteForm.getInputsForm()") à SiteManager exemple :
        SiteManager siteManager = new SiteManager();
        boolean feedBackAction = siteManager.insertSite(creatSiteForm.getInputsForm());

        //FORMAT de la liste d'inputs : ["nom site","personnalisation contraintes y/n","Longueur mdp","majuscule","minuscule","char spé","chiffre","id Site", "personnalisation mdp y/n","mdp","personnalisation metaDonnee","commentaire","personnalisation rappel","duree (XXj/XXm/XXy)"]
        // Tu peux aussi voir les champs affichés juste au-dessus dans la fonction AddSiteAction() (constructeur de cette classe.)
        //tu recois tj un petit "y" ou un petit "n" pour les confirmations ou personalisation ex : [nomSite, n, null, null, null, null, null, idSite, y, mdpPerso, n, null, n, null]


        creatSiteForm.emptyList();
        return true; //return feedBackAction
    }

    @Override
    public void launchForm() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + creatSiteForm.getTitle() + "\n");

        for (int i = 0; i < numberInput; i++) {
            System.out.print(creatSiteForm.getInstructionsForm().get(i) + " : ");

            if (creatSiteForm.getCursor().get(i) != 0) {

                checkYesOrNoQuestion(scanner, i);
            } else {
                creatSiteForm.getInputsForm().add(scanner.nextLine());
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
                creatSiteForm.getInputsForm().add(tmpInput);
            } else if (tmpInput.equals("n")) {
                creatSiteForm.getInputsForm().add(tmpInput);
                creatSiteForm.fillInputFormArrayListWhitNAValues(creatSiteForm.getCursor().get(index));
                index += creatSiteForm.getCursor().get(index);
            } else {
                System.out.print(creatSiteForm.getInstructionsForm().get(index) + " : ");
            }
        } while (!tmpInput.equals("y") && !tmpInput.equals("n"));
    }


}

