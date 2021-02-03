package fr.esgi.java.passwordmanager;

import fr.esgi.java.passwordmanager.managers.SiteManager;
import fr.esgi.java.passwordmanager.managers.UserManager;
import fr.esgi.java.passwordmanager.models.Password;
import fr.esgi.java.passwordmanager.models.Site;
import fr.esgi.java.passwordmanager.models.User;

import java.util.ArrayList;

public class Session {

    private static Session currentSession;
    private User currentUser;
    private UserManager userManager;


    private Session() {
    }

    public static Session getInstance() {
        if (currentSession == null) {
            currentSession = new Session();
        }
        return currentSession;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currUser) {

        if(currUser==null){
            currentUser=null;
        }else {
            Password password = new Password(currUser.getPassword().getPassword(), null, true);
            this.currentUser = new User(currUser.getName(), password, currUser.getEmail(), null);

            SiteManager siteManager = new SiteManager();
            ArrayList<Site> tmpListSites = siteManager.creatListSites();
            currentUser.setListSites(tmpListSites);
        }
    }

    public void initUserManager(){
        userManager = new UserManager();
    }

    public UserManager getUserManager() {
        return userManager;
    }

}
