package fr.esgi.java.passwordmanager.managers;

import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.models.Site;

public class SiteManager {

    public SiteManager() {
    }

    public Site findSiteInListSites(String site) {

        int nbSites = Session.getInstace().getCurrentUser().getListSites().size();

        for (int i = 0; i < nbSites; i++) {
            if (Session.getInstace().getCurrentUser().getListSites().get(i).getName().toLowerCase().equals(site.toLowerCase())) {
                return Session.getInstace().getCurrentUser().getListSites().get(i);
            }
        }

        return null;

    }

}
