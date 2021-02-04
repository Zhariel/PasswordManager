package fr.esgi.java.passwordmanager.display.menu;

import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.display.actions.IAction;
import fr.esgi.java.passwordmanager.display.menu.model.Form;
import fr.esgi.java.passwordmanager.display.menu.model.Menu;
import fr.esgi.java.passwordmanager.managers.SiteManager;
import fr.esgi.java.passwordmanager.models.Site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Class DisplayManager
 * Manage the output of this application
 */
public class DisplayManager {

    private Menu currentMenu;


    public DisplayManager(boolean isFirstlaunch) {
        if (isFirstlaunch) {
            launchMenu(MenuType.MAIN.toString());
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    /**
     * launchMenu
     *
     * @param type : Main or User. Corresplonding to Enum in the file MenuType
     *             Call the menu factory
     */
    public void launchMenu(String type) {
        MenuFactory factory = new MenuFactory();
        currentMenu = factory.getMenu(type);
        displayMenu(currentMenu);
    }

    /**
     * displayMenu
     *
     * @param currentMenu : the current menu diplayed
     *                    Display menu options
     */
    public void displayMenu(Menu currentMenu) {
        System.out.println(generateTextOfMenu(currentMenu));
    }

    /**
     * generateTextOfMenu
     *
     * @param currentMenu : the current menu diplayed
     *                    Generate menu's strings.
     * @return String : Contain all the menu text.
     */
    private String generateTextOfMenu(Menu currentMenu) {
        StringBuilder sb = new StringBuilder();
        sb.append(currentMenu.name).append(" :\n\n");
        sb.append(currentMenu.getInstruction()).append(":\n\n");
        List<String> actionNames = new ArrayList<String>(currentMenu.actionsMap.keySet());
        for (int i = 0; i < actionNames.size(); i++) {
            sb.append(String.format(" %d: %s%n", i + 1, actionNames.get(i)));
        }
        return sb.toString();
    }

    /**
     * executeAction
     *
     * @param actionNumber : index of the option menu
     *                     launche the action binding at this option menu selected.
     * @return boolean : feedback of the process.
     */
    public boolean executeAction(int actionNumber) throws Exception {

        int effectiveActionNumber = actionNumber - 1;
        if (effectiveActionNumber < 0 || effectiveActionNumber >= currentMenu.actionsMap.size()) {
            System.out.println(actionNumber + " n'est pas un choix valide, s'il vous plait choisissez un nombre entre 1 et " + currentMenu.actionsMap.size());
            return false;
        } else {
            List<IAction> actions = new ArrayList<IAction>(currentMenu.actionsMap.values());
            boolean actionFeedBack = actions.get(effectiveActionNumber).run();

            if (!actionFeedBack) {
                System.out.println();
                displayMenu(currentMenu);
            }
            return actionFeedBack;
        }
    }


    /**
     * updateMenu
     *
     * @param actionNumber   : index of the option menu
     * @param feedBackAction : boolean : feedback of the process.
     *                       Switch the type of curent menu where login ou logout are succed.
     */

    public void updateMenu(boolean feedBackAction, int actionNumber) {

        if (!feedBackAction) {
            return;
        }

        MenuFactory factory = new MenuFactory();

        if (currentMenu.getType().equals(String.valueOf(MenuType.MAIN)) && actionNumber == 1) {
            currentMenu = factory.getMenu(String.valueOf(MenuType.USER));
            sayHelloToUser();
        }

        if (currentMenu.getType().equals(String.valueOf(MenuType.USER)) && actionNumber == 6) {
            currentMenu = factory.getMenu(String.valueOf(MenuType.MAIN));
        }

        System.out.println();
        displayMenu(currentMenu);
    }


    public void displayListSite() {

        int nbSites = Session.getInstance().getCurrentUser().getListSites().size();
        System.out.println(nbSites + " sites sont enregistes.");

        for (int i = 0; i < nbSites; i++) {
            System.out.println(Session.getInstance().getCurrentUser().getListSites().get(i).getName());
        }

    }

    public boolean displayOneSite(Form displayOneSiteForm) {

        if (displayOneSiteForm.getInputsForm().get(0).equals("")) {
            System.out.println("Saisie vide.");
            return false;
        }

        SiteManager siteManager = new SiteManager();
        Site targetSite = siteManager.findSiteInListSites(displayOneSiteForm.getInputsForm().get(0));

        if (targetSite == null) {
            System.out.println("Ce site n'est pas present dans votre base de donnees.");
            return false;
        }

        System.out.println(targetSite.toString());
        return true;
    }

    public void sayHelloToUser() {
        try {
            System.out.println("Bonjour " + Session.getInstance().getCurrentUser().getName());
        } catch (Exception e) {
            System.out.println("Bonjour");
        }

    }
}

