package fr.esgi.java.passwordmanager.display.menu.model;

import fr.esgi.java.passwordmanager.display.actions.*;
import fr.esgi.java.passwordmanager.display.menu.MenuType;


import java.util.LinkedHashMap;

public class MenuUser extends Menu {

    /** Create a MenuUser Object
     * assign a name
     * creat a actionMap containing the displayed sentense of option and its associated action
     */

    public MenuUser(){
        this.name = "Menu Utilisateur";
        this.actionsMap = new LinkedHashMap<String, IAction>();
        this.type = String.valueOf(MenuType.USER);
        putAction("Ajouter un site",new AddSiteAction());
        putAction("Afficher la liste des sites",new DisplayListSiteAction());
        putAction("Afficher les informations d'un site",new DisplayOneSiteAction());
        putAction("Modifier un site",new ModificationSiteAction());
        putAction("Supprimer un site",new DeleteSiteAction());
        putAction("Deconnexion",new LogoutAction());
        putAction("Quitter",new ExitAction());
    }

}
