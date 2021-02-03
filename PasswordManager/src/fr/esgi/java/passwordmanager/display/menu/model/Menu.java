package fr.esgi.java.passwordmanager.display.menu.model;

import fr.esgi.java.passwordmanager.display.actions.IAction;

import java.util.LinkedHashMap;


/**
 * Class Menu : Pattern of menu.
 */
public class Menu {

    public static final String instruction = "Veuillez saisir le numero de l'option que vous souhaitez effectuer";
    public String name;
    public String type;
    public LinkedHashMap<String, IAction> actionsMap;


    /**
     * Insert an action related to this (menu object).
     *
     * @param name   String displayed in Menu corresponding to option menu (named action in code).
     * @param action Class action bind to the option menu.
     */
    public void putAction(String name, IAction action) {
        actionsMap.put(name, action);
    }

    public String getInstruction() {
        return instruction;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LinkedHashMap<String, IAction> getActionsMap() {
        return actionsMap;
    }
}
