package fr.esgi.java.passwordmanager;

import fr.esgi.java.passwordmanager.managers.SiteManager;
import fr.esgi.java.passwordmanager.managers.UserManager;
import fr.esgi.java.passwordmanager.models.Password;
import fr.esgi.java.passwordmanager.models.Site;
import fr.esgi.java.passwordmanager.models.User;

import java.util.ArrayList;

/**
 * Class Session
 * <b>Contains the current user, that is a variable user containing id, password and email of the current user after authentification.</b>
 * <p>
 * This class is a Singleton because it's necessary to have access to the currentUser anymwhere in the code.
 * </p>
 * <p>
 * This class contains :
 * <ul>
 * <li>Session currentSession</li>
 * <li>User currentUser</li>
 * <li>UserManager userManager : class that manage the creation, deletion and modification of users. The userManager is instancied once during the
 * instanciation of the Session in the main. (class Launcher)</li>
 * </ul>
 * </p>
 **/

public class Session {

    private static Session currentSession;
    private User currentUser;
    private UserManager userManager;


    /**
     * Private constructor
     **/
    private Session() {
    }

    /**
     * Function : getInstance
     *
     * @return currentSession;
     **/
    public static Session getInstance() {
        if (currentSession == null) {
            currentSession = new Session();
        }
        return currentSession;
    }

    /**
     * Function getCurrentUser
     *
     * @Return a copy of currentUser
     **/
    public User getCurrentUser() {
        return new User(this.currentUser.getName(), this.currentUser.getPassword(), this.currentUser.getEmail(),this.currentUser.getListSites());
    }

    /**
     * Function setCurrentUser
     *
     * @param currUser : new current user.
     **/
    public void setCurrentUser(User currUser) {

        if (currUser == null) {
            currentUser = null;
        } else {
            Password password = new Password(currUser.getPassword().getPassword(), true);
            this.currentUser = new User(currUser.getName(), password, currUser.getEmail());

            SiteManager siteManager = new SiteManager();
            ArrayList<Site> tmpListSites = siteManager.creatListSites();
            currentUser.setListSites(tmpListSites);
        }
    }

    /**
     * Function logOut
     * During logout currentUser is reset.
     **/
    public void logOut() {
        this.currentUser = null;
    }


    /**
     * Function initUserManager
     * userManager is initialized once, in the start of application.
     **/
    public void initUserManager() {
        userManager = new UserManager();
    }

    /**
     * Function getInstanceUserManager :
     * get the instance of UserManagerObjet created during initialization of application (launcher class).
     *
     * @return userManager
     **/
    public UserManager getInstanceUserManager() {
        return userManager;
    }

    /**
     * Function callRemoveSiteOnListSites
     *
     * @param index : Index of the site in the listSite of the current User.
     *              remove a site from listSite of the current User.
     **/

    public void callRemoveSiteOnListSites(int index) {

        //function "removeSiteOnListSites from User Class"
        this.currentUser.removeSiteOnListSites(index);
    }

    /**
     * Function callAddSiteOnListSites
     *
     * @param newSite add a site from listSite of the current User.
     **/

    public void callAddSiteOnListSites(Site newSite) {

        //function "addSiteOnListSites from User Class"
        this.currentUser.addSiteOnListSites(newSite);
    }

}
