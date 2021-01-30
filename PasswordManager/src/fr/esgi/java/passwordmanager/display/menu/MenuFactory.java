package fr.esgi.java.passwordmanager.display.menu;

import fr.esgi.java.passwordmanager.display.menu.model.Menu;
import fr.esgi.java.passwordmanager.display.menu.model.MenuLogin;
import fr.esgi.java.passwordmanager.display.menu.model.MenuUser;
import fr.esgi.java.passwordmanager.display.menu.model.*;


public class MenuFactory {


    private Menu creatMenu(String type){

        if(type.equalsIgnoreCase(String.valueOf(MenuType.MAIN))){
            return new MenuLogin();
        }else if(type.equalsIgnoreCase(String.valueOf(MenuType.USER))){
            return new MenuUser();
        }
        return null;
    }

    public Menu getMenu(String type){
        return creatMenu(type);
    }

}
