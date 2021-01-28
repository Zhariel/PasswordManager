package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.model.Form;

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

        List<Integer> tmpCursorList = Arrays.asList(0,5,0,0,0,0,0,0,1,0,1,0,1,0);
        modificationSiteForm.getCursor().addAll(tmpCursorList);

    }

    public boolean run() {
        launchForm();
        // Envoie la liste d'inputs ("modificationSiteForm.getInputsForm()") à SiteManager exemple :
        // SiteManager siteManager = new SiteManager();
        // boolean feedBackAction = siteManager->modificationSite(modificationSiteForm.getInputsForm());

        //FORMAT de la liste d'inputs : ["nom site","personnalisation contraintes y/n","Longueur mdp","majuscule","minuscule","char spé","chiffre","id Site", "personnalisation mdp y/n","mdp","personnalisation metaDonnee","commentaire","personnalisation rappel","duree (XXj/XXm/XXy)"]
        // Tu peux aussi voir les champs affichés juste au-dessus dans la fonction ModificationSiteAction() (constructeur de cette classe.)
        //tu recois tj un petit "y" ou un petit "n" pour les confirmations ou personalisation
        modificationSiteForm.emptyList();
        return true; //return feedBackAction
    }

    @Override
    public void launchForm() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n"+modificationSiteForm.getTitle()+"\n");

        for(int i=0;i<numberInput;i++){
            System.out.print(modificationSiteForm.getInstructionsForm().get(i) +" : ");

            if(modificationSiteForm.getCursor().get(i)!=0){

                String tmpInput;

                do {

                    tmpInput = scanner.nextLine();

                    if(tmpInput.length()>0){
                        tmpInput = tmpInput.substring(0, 1).toLowerCase();
                    }else{
                        tmpInput = "error";
                        tmpInput = tmpInput.substring(0, 1).toLowerCase();
                    }

                    if (tmpInput.equals("y")) {
                        modificationSiteForm.getInputsForm().add(tmpInput);
                    } else if (tmpInput.equals("n")) {
                        modificationSiteForm.getInputsForm().add(tmpInput);
                        modificationSiteForm.fillInputFormArrayListWhitNAValues(modificationSiteForm.getCursor().get(i));
                        i += modificationSiteForm.getCursor().get(i);
                    } else {
                        System.out.print(modificationSiteForm.getInstructionsForm().get(i) +" : ");
                    }
                }while (!tmpInput.equals("y") && !tmpInput.equals("n"));

            }else{
                modificationSiteForm.getInputsForm().add(scanner.nextLine());
            }
        }
        System.out.println("\n");
    }
}
