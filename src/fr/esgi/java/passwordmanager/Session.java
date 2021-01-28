package fr.esgi.java.passwordmanager;

import fr.esgi.java.passwordmanager.models.User;

public class Session {

    private static Session currentSession;
    private User CurrentUser;

    private Session() {
    }

    public static Session getInstace() {
        if (currentSession == null) {
            currentSession = new Session();
        }
        return currentSession;
    }

    public User getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }
}
