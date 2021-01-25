package fr.esgi.java.passwordmanager.display.menu;

import fr.esgi.java.passwordmanager.display.actions.IAction;
import fr.esgi.java.passwordmanager.display.menu.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class DisplayManager {

    private Menu currentMenu;


    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public DisplayManager(){
        launchMenu(MenuType.MAIN.toString());
    }

    public void launchMenu(String type) {
        MenuFactory factory =  new MenuFactory();
        currentMenu = factory.getMenu(type);
        displayMenu(currentMenu);
    }

    /** Display menu options
     *
     */
    public void displayMenu(Menu currentMenu) {
        System.out.println(generateTextOfMenu(currentMenu));
    }

    /** Generate menu's strings.
     *
     */
    private String generateTextOfMenu(Menu currentMenu) {
        StringBuilder sb = new StringBuilder();
        sb.append(currentMenu.name).append(":\n");
        sb.append(currentMenu.getInstruction()).append(":\n");
        List<String> actionNames = new ArrayList<String>(currentMenu.actionsMap.keySet());
        for (int i = 0; i < actionNames.size(); i++) {
            sb.append(String.format(" %d: %s%n", i + 1, actionNames.get(i)));
        }
        return sb.toString();
    }

    public boolean executeAction(int actionNumber){

        int effectiveActionNumber = actionNumber - 1;
        if (effectiveActionNumber < 0 || effectiveActionNumber >= currentMenu.actionsMap.size()) {
            System.out.println( actionNumber + " n'est pas un choix valide, s'il vous plait choisissez un nombre entre 1 et "+currentMenu.actionsMap.size());
            return false;
        } else {
            List<IAction> actions = new ArrayList<IAction>(currentMenu.actionsMap.values());
            Boolean actionFeedBack = actions.get(effectiveActionNumber).run();

            if(!actionFeedBack){
                System.out.println("Erreur de saisie");
            }
            return actionFeedBack;
        }
    }

    public void updateMenu(boolean feedBackAction,int actionNumber) {

        if(!feedBackAction){
            return;
        }

        MenuFactory factory =  new MenuFactory();

        if(currentMenu.getType().equals(String.valueOf(MenuType.MAIN)) && actionNumber==1){
            currentMenu = factory.getMenu(String.valueOf(MenuType.USER));
        }

        if(currentMenu.getType().equals(String.valueOf(MenuType.USER)) && actionNumber==6){
            currentMenu = factory.getMenu(String.valueOf(MenuType.MAIN));
        }

        displayMenu(currentMenu);
    }

}

