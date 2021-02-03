package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;
import fr.esgi.java.passwordmanager.managers.InputType;
import fr.esgi.java.passwordmanager.managers.SiteManager;

import java.util.ArrayList;
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
        creatSiteForm.getInstructionsForm().add("Voulez-vous generer votre mdp vous meme y/n");
        creatSiteForm.getInstructionsForm().add("Saisissez le mdp");
        creatSiteForm.getInstructionsForm().add("Voulez-vous ajouter des metadonnees y/n");
        creatSiteForm.getInstructionsForm().add("Ajout commentaires ");
        creatSiteForm.getInstructionsForm().add("Voulez-vous un rappel y/n");
        creatSiteForm.getInstructionsForm().add("Ajout duree avant rappel (XXj/XXm/XXy) ");

        List<Integer> tmpCursorList = Arrays.asList(0, 5, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0);
        creatSiteForm.getCursor().addAll(tmpCursorList);

        List<InputType> tmpInputsList = Arrays.asList(InputType.NAME,InputType.YESNO,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NAME,InputType.YESNO,InputType.PASSWORD,InputType.YESNO,InputType.COM,InputType.YESNO,InputType.DURATION);
        creatSiteForm.getTypeInputs().addAll(tmpInputsList);

    }

    public boolean run() {

        launchForm();

        SiteManager siteManager = new SiteManager();
        boolean feedBackAction = siteManager.addSite(creatSiteForm.getInputsForm());

        creatSiteForm.emptyList();
        return feedBackAction;
    }

    @Override
    public void launchForm() {

        Scanner scanner = new Scanner(System.in);
        String tmpInput;
        System.out.println("\n" + creatSiteForm.getTitle() + "\n");

        for (int i = 0; i < numberInput; i++) {

            System.out.print(creatSiteForm.getInstructionsForm().get(i) + " : ");

            if (creatSiteForm.getCursor().get(i) != 0) {
                if(!creatSiteForm.checkYesOrNoQuestion(scanner, i)) {
                    i += creatSiteForm.getCursor().get(i);
                }
            } else {

                tmpInput = creatSiteForm.checkIfInputIsEmpty(i,scanner,scanner.nextLine());

                if(creatSiteForm.getTypeInputs().get(i).equals(InputType.NUM)){

                    tmpInput = creatSiteForm.checkIfInputIsNumber(i,scanner,tmpInput);

                }else if(creatSiteForm.getTypeInputs().get(i).equals(InputType.DURATION)){

                    tmpInput = creatSiteForm.checkIfInputIsDuration(i,scanner,tmpInput);

                }

                creatSiteForm.getInputsForm().add(tmpInput);
            }
        }
        System.out.println("\n");
    }

}

