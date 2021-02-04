package fr.esgi.java.passwordmanager.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.models.*;
import fr.esgi.java.passwordmanager.files.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class SiteManager
 * Creation, modification and suppression of sites are manage by this class.
 **/


public class SiteManager {

    /**
     * DataFile of the user.
     **/
    DataFile dataFile;

    /**
     * Constructor
     * Instantiate dataFile;
     **/
    public SiteManager() {
        dataFile = new DataFile(Session.getInstance().getCurrentUser().getName());
    }

    /**
     * Function checkDuplicateSite
     *
     * @return boolean
     * @Param nameSite
     **/
    public boolean checkDuplicateSite(String nameNewSite) {

        for (Site site : Session.getInstance().getCurrentUser().getListSites()) {
            if (site.getName().equals(nameNewSite)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function addSite
     * This function launch the process of adSite by run function in addSiteAction
     * It call site constructor, insertSiteInFile and insertSiteOnListSite.
     *
     * @param listInputs : array of input
     * @return boolean
     **/
    public boolean addSite(ArrayList<String> listInputs) {

        Site newSite;

        //First : Creat Site.
        try {
            newSite = new Site(listInputs);
        } catch (Exception e) {
            System.out.println("Un probleme est survenu lors de l'ajout du site. Veuillez ressayer.");
            return false;
        }

        //Second : Insert new site in file
        if (!insertSiteInFileData(newSite)) {
            return false;
        }

        // Thrid : Insert Site in the listSite of currentUser.
        Session.getInstance().callAddSiteOnListSites(newSite);

        return true;
    }

    /**
     * Function insertSiteInFileData
     * This function write in fileData.
     *
     * @param newSite : array of input
     * @return boolean
     **/
    public boolean insertSiteInFileData(Site newSite) {

        try {

            if (checkDuplicateSite(newSite.getName())) {
                System.out.println("Site deja existant");
                return false;
            }

            ObjectMapper mapper = new ObjectMapper();
            ArrayNode array = (ArrayNode) mapper.readTree(dataFile.file);

            ObjectNode site = javaToJson(newSite);

            array.add(site);
            mapper.writeValue(dataFile.file, array);
            System.out.println("Ajout reussi pour " + newSite.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Impossible d'ajouter " + newSite.getName() + ", fichier user introuvable. Veillez relancer l'application.");
            return false;
        } catch (Exception e) {
            System.out.println("Impossible d'ajouter " + newSite.getName() + ", fichier user compromis. Veillez le supprimer et relancer l'application.");
            return false;
        }

        return true;
    }

    public void deleteSiteInFileData(Site s) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            ArrayNode array = (ArrayNode) mapper.readTree(dataFile.file);

            if (array.isArray()) {
                int index = 0;
                for (JsonNode n : array) {
                    if (n.get("name").toString().replace("\"", "").equals(s.getName())) {
                        array.remove(index);
                        System.out.println("Site " + s.getName() + " a été supprimé.");
                        break;
                    }
                    index++;
                }
                mapper.writeValue(dataFile.file, array);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectNode javaToJson(Site s) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ObjectNode site = mapper.createObjectNode();
        ObjectNode constraint = mapper.createObjectNode();
        ObjectNode metadata = mapper.createObjectNode();

        constraint.put("totalLength", s.constraint.getTotalLength());
        constraint.put("upperCase", s.constraint.getUpperCase());
        constraint.put("lowerCase", s.constraint.getLowerCase());
        constraint.put("specialChar", s.constraint.getSpecialChar());
        constraint.put("letter", s.constraint.getLetter());
        constraint.put("digit", s.constraint.getDigit());

        metadata.put("dateCreation", s.metaData.getDateCreation().toString());
        metadata.put("duration", s.metaData.getDuration());
        metadata.put("comment", s.metaData.getComment());

        Password tmpPassword = new Password(s.getPassword(),false);

        site.put("name", s.getName());
        site.put("password", tmpPassword.encryption());
        site.put("idUser", s.getIdUser());
        site.put("constraint", constraint);
        site.put("metaData", metadata);

        return site;
    }

    public Site jsonToJava(JsonNode node) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Constraint c = new Constraint(
                    node.get("constraint").get("totalLength").asInt(),
                    node.get("constraint").get("upperCase").asInt(),
                    node.get("constraint").get("lowerCase").asInt(),
                    node.get("constraint").get("specialChar").asInt(),
                    node.get("constraint").get("letter").asInt(),
                    node.get("constraint").get("digit").asInt()
            );

            LocalDate date = LocalDate.parse(node.get("metaData").get("dateCreation").toString().replace("\"", ""));
            Metadata m = new Metadata(
                    date,
                    node.get("metaData").get("duration").toString().replace("\"", ""),
                    node.get("metaData").get("comment").toString().replace("\"", "")
            );

            Password tmpPassword = new Password(node.get("password").toString().replace("\"", ""),false);

            Password p = new Password(
                    tmpPassword.decryption(), false
            );

            Site s = new Site(
                    node.get("name").toString().replace("\"", ""),
                    p,
                    node.get("idUser").toString().replace("\"", ""),
                    c,
                    m
            );

            return s;

        } catch (Exception e) {
            System.out.println("Attention fichier data de configuration corrompue!");
        }

        return null;

    }


    public ArrayList<Site> creatListSites() {

        ArrayList<Site> sites = new ArrayList<Site>();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = null;

        if (!dataFile.file.exists()) {
            System.out.println("Le fichier de donnee utilisateur est introuvable. L'application ne peut fonctionner sans.");
            System.exit(1);
        }

        if (dataFile.file.length() == 0) {
            return sites;
        }

        try {
            array = (ArrayNode) mapper.readTree(dataFile.file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (array.isArray()) {
            for (JsonNode node : array) {
                Site tmpSite = jsonToJava(node);
                if (tmpSite != null) {
                    sites.add(tmpSite);

                }
            }
        }

        return sites;
    }


    public Site findSiteInListSites(String site) {

        int nbSites = Session.getInstance().getCurrentUser().getListSites().size();

        for (int i = 0; i < nbSites; i++) {
            if (Session.getInstance().getCurrentUser().getListSites().get(i).getName().toLowerCase().equals(site.toLowerCase())) {
                return Session.getInstance().getCurrentUser().getListSites().get(i);
            }
        }

        return null;

    }

    public boolean initDeleteSite(ArrayList<String> inputsForm) {

        if (inputsForm.get(1).equals("y")) {
            int i = 0;
            for (Site site : Session.getInstance().getCurrentUser().getListSites()) {
                if (site.getName().equals(inputsForm.get(0))) {
                    deleteSiteInFileData(site);
                    //Remove site from listSites of currentUser. Function removeSiteOnListSites come from User class.
                    Session.getInstance().callRemoveSiteOnListSites(i);
                    return true;
                }
                i++;
            }
            System.out.println("Site demande non trouve.");
            return false;
        } else {
            return true;
        }
    }


    public boolean modificationSite(ArrayList<String> inputsForm) {

        Site siteSelected = findSiteInListSites(inputsForm.get(0));
        if (siteSelected == null) {
            System.out.println("Site non trouve.");
            return false;
        }
        deleteSiteInFileData(siteSelected);
        removeSiteToListSites(siteSelected); //Remove of curentUser's listSites
        addSite(inputsForm); // Add in file and currentUser's listSites

        return true;
    }


    private void removeSiteToListSites(Site siteSelected) {
        int i = 0;
        for (Site site : Session.getInstance().getCurrentUser().getListSites()) {
            if (site.getName().equals(siteSelected.getName())) {
                Session.getInstance().callRemoveSiteOnListSites(i);
                return;
            }
            i++;
        }
    }


}