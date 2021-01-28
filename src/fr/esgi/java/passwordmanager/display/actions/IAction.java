package fr.esgi.java.passwordmanager.display.actions;

/**IAction
 * Interface for each menu action.
 * Contain two functions :
 * run() :  For all action classes, this function is the star of process.
 * launchForm() : display the action form
 * */

public interface IAction {

    public boolean run();
    public void launchForm();
}
