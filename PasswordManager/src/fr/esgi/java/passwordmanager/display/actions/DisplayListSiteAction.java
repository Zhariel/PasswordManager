package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.display.menu.DisplayManager;


public class DisplayListSiteAction implements IAction {


    public DisplayListSiteAction() {
    }

    public boolean run() {

        DisplayManager entry = new DisplayManager(false);
        entry.displayListSite();
        return true;
    }

    @Override
    public void launchForm() {
    }
}
