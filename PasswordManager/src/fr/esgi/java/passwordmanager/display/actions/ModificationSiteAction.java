package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;
import fr.esgi.java.passwordmanager.managers.InputType;
import fr.esgi.java.passwordmanager.managers.SiteManager;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ModificationSiteAction implements IAction {

    private static final int numberInput = 14;
    private Form modificationSiteForm;

    public ModificationSiteAction() {

        modificationSiteForm = new Form("Modification d'un site");
        modificationSiteForm.getInstructionsForm().add("Saisissez le nom du site");
        modificationSiteForm.getInstructionsForm().add("Voulez-vous personnaliser les contraintes y/n");
        modificationSiteForm.getInstructionsForm().add("Longueur mdp");
        modificationSiteForm.getInstructionsForm().add("Nombre de majuscules");
        modificationSiteForm.getInstructionsForm().add("Nombre de minuscules");
        modificationSiteForm.getInstructionsForm().add("Nombre de characteres speciaux");
        modificationSiteForm.getInstructionsForm().add("Nombre de chiffres");
        modificationSiteForm.getInstructionsForm().add("Saisissez l'identifiant du site");
        modificationSiteForm.getInstructionsForm().add("Voulez-vous generer votre mdp automatiquement y/n");
        modificationSiteForm.getInstructionsForm().add("Saisissez le mdp");
        modificationSiteForm.getInstructionsForm().add("Voulez-vous ajouter des metadonnees y/n");
        modificationSiteForm.getInstructionsForm().add("Ajout commentaires ");
        modificationSiteForm.getInstructionsForm().add("Voulez-vous un rappel y/n");
        modificationSiteForm.getInstructionsForm().add("Ajout duree avant rappel (XXj/XXm/XXy) ");

        List<Integer> tmpCursorList = Arrays.asList(0, 5, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0);
        modificationSiteForm.getCursor().addAll(tmpCursorList);

        List<InputType> tmpInputsList = Arrays.asList(InputType.NAME,InputType.YESNO,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NAME,InputType.YESNO,InputType.PASSWORD,InputType.YESNO,InputType.COM,InputType.YESNO,InputType.DURATION);
        modificationSiteForm.getTypeInputs().addAll(tmpInputsList);

    }

    public boolean run() {
        launchForm();
        SiteManager siteManager = new SiteManager();
        boolean feedBackAction = false;
        try {
            feedBackAction = siteManager.modificationSite(modificationSiteForm.getInputsForm());
        } catch (FileNotFoundException e) {
            System.out.println("Le site n'a pas pu être modifié.");
        }

        modificationSiteForm.emptyList();
        return feedBackAction;
    }

    @Override
    public void launchForm() {

        Scanner scanner = new Scanner(System.in);
        String tmpInput;
        System.out.println("\n" + modificationSiteForm.getTitle() + "\n");

        for (int i = 0; i < numberInput; i++) {

            System.out.print(modificationSiteForm.getInstructionsForm().get(i) + " : ");

            if (modificationSiteForm.getCursor().get(i) != 0) {
                if(!modificationSiteForm.checkYesOrNoQuestion(scanner, i)) {
                    i += modificationSiteForm.getCursor().get(i);
                }
            } else {

                tmpInput = modificationSiteForm.checkIfInputIsEmpty(i,scanner,scanner.nextLine());

                if(modificationSiteForm.getTypeInputs().get(i).equals(InputType.NUM)){

                    tmpInput = modificationSiteForm.checkIfInputIsNumber(i,scanner,tmpInput);

                }else if(modificationSiteForm.getTypeInputs().get(i).equals(InputType.DURATION)){

                    tmpInput = modificationSiteForm.checkIfInputIsDuration(i,scanner,tmpInput);

                }

                modificationSiteForm.getInputsForm().add(tmpInput);
            }
        }
        System.out.println("\n");
    }

}