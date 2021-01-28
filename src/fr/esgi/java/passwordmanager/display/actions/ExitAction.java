package fr.esgi.java.passwordmanager.display.actions;

public class ExitAction implements IAction {

    public ExitAction() {
    }

    public boolean run() {
        System.out.println("\nA tantôt!");
        System.exit(0);
        return true;
    }

    @Override
    public void launchForm() {

    }
}
