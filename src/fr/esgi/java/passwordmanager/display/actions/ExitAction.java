package fr.esgi.java.passwordmanager.display.actions;

public class ExitAction implements IAction {

    public ExitAction() {
    }

    public boolean run() {
        System.exit(0);
        return true;
    }

    @Override
    public void launchForm() {

    }
}
