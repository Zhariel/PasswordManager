package fr.esgi.java.passwordmanager.display.actions;

import fr.esgi.java.passwordmanager.Session;

public class LogoutAction implements IAction {

    public LogoutAction() {
    }

    public boolean run() {

        Session.getInstance().setCurrentUser(null);
        return true;
    }

    @Override
    public void launchForm() {

    }
}
