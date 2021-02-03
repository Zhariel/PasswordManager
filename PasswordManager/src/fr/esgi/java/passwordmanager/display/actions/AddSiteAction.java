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
        creatSiteForm.addInstructionsForm("Saisissez le nom du site");
        creatSiteForm.addInstructionsForm("Voulez-vous personnaliser les contraintes y/n");
        creatSiteForm.addInstructionsForm("Longueur mdp");
        creatSiteForm.addInstructionsForm("Nombre de majuscules");
        creatSiteForm.addInstructionsForm("Nombre de minuscules");
        creatSiteForm.addInstructionsForm("Nombre de characteres speciaux");
        creatSiteForm.addInstructionsForm("Nombre de chiffres");
        creatSiteForm.addInstructionsForm("Saisissez l'identifiant du site");
        creatSiteForm.addInstructionsForm("Voulez-vous generer votre mdp vous meme y/n");
        creatSiteForm.addInstructionsForm("Saisissez le mdp");
        creatSiteForm.addInstructionsForm("Voulez-vous ajouter des metadonnees y/n");
        creatSiteForm.addInstructionsForm("Ajout commentaires ");
        creatSiteForm.addInstructionsForm("Voulez-vous un rappel y/n");
        creatSiteForm.addInstructionsForm("Ajout duree avant rappel (XXj/XXm/XXy) ");

        List<Integer> tmpCursorList = Arrays.asList(0, 5, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0);
        creatSiteForm.addCursor(tmpCursorList);

        List<InputType> tmpInputsList = Arrays.asList(InputType.NAME,InputType.YESNO,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NUM,InputType.NAME,InputType.YESNO,InputType.PASSWORD,InputType.YESNO,InputType.COM,InputType.YESNO,InputType.DURATION);
        creatSiteForm.addTypeInputs(tmpInputsList);

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

                creatSiteForm.addInputsForm(tmpInput);
            }
        }
        System.out.println("\n");
    }

}

