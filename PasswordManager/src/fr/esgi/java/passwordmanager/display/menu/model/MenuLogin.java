package fr.esgi.java.passwordmanager.display.menu.model;

import fr.esgi.java.passwordmanager.display.actions.*;
import fr.esgi.java.passwordmanager.display.menu.MenuType;

import java.util.LinkedHashMap;

/**
 * Class Menulogin
 * Contains the skeleton of the MenuLogin.
 **/

public class MenuLogin extends Menu {

    /**
     * Allows to a MenuLogin Object
     * assign a name and a type
     * creat an actionMap containing the displayed sentense of option and its associated action
     */

    public MenuLogin() {
        this.name = "Menu d'accueil";
        this.type = String.valueOf(MenuType.MAIN);
        this.actionsMap = new LinkedHashMap<String, IAction>();
        putAction("Identification", new LoginAction());
        putAction("Creation d'un utilisateur", new CreatUserAction());
        putAction("Changement de mdp", new ModificationPasswordAction());
        putAction("Supression d'un utilisateur", new DeleteUserAction());
        putAction("Mot de passe oublie", new PasswordForgotAction());
        putAction("Quitter", new ExitAction());

    }


}
