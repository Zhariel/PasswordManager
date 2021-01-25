package fr.esgi.java.passwordmanager.display.actions;

public class DisplayListSiteAction implements IAction {


    public DisplayListSiteAction() {
    }

    public boolean run() {

        System.out.println("ListSites");
        return true;
    }

    @Override
    public void launchForm() {
    }
}
