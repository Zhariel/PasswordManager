package fr.esgi.java.passwordmanager.display.menu.model;

import fr.esgi.java.passwordmanager.display.actions.IAction;

import java.util.LinkedHashMap;

public class Menu {

    //TODO mettre en prive

    public static final String instruction ="Veuillez saisir le numero de l'option que vous souhaitez effectuer";
    public String name;
    public String type;
    public LinkedHashMap<String, IAction> actionsMap;


    /** Insert an action related to this (menu object).
     * @param name name of the action
     * @param action Method to run for the selected option
     */
    public void putAction(String name, IAction action) {
        actionsMap.put(name, action);
    }

    /**Get the menu instruction
     * @return instruction
     */

    public String getInstruction() {
        return instruction;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
