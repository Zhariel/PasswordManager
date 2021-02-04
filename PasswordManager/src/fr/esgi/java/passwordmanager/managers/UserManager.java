package fr.esgi.java.passwordmanager.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.esgi.java.passwordmanager.Session;
import fr.esgi.java.passwordmanager.files.UserFile;
import fr.esgi.java.passwordmanager.models.*;
import org.apache.commons.validator.routines.EmailValidator;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class UserManager
 * Creation, modification and suppression of users are manage by this class.
 **/


public class UserManager {

    private UserFile fileUsers;
    public ArrayList<User> usersList;


    public UserManager() throws Exception {
        fileUsers = new UserFile();
        usersList = creationOfUsersList();
    }


    public ArrayList<User> creationOfUsersList() throws Exception {

        ArrayList<User> users = new ArrayList<User>();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = null;

        array = (ArrayNode) mapper.readTree(fileUsers.file);

        if (array.isArray()) {
            for (JsonNode node : array) {
                users.add(jsonToJava(node));
            }
        }

        return users;
    }


    public void insertUser(User newUser) throws IOException {

        if (checkDuplicateUser(newUser.getName())) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = (ArrayNode) mapper.readTree(fileUsers.file);

        ObjectNode user = javaToJson(newUser);

        array.add(user);
        mapper.writeValue(fileUsers.file, array);
        System.out.println(newUser.getName() + " a bien ete ajoute.");

        usersList.add(newUser);
        creatDataFile(newUser);

    }

    public boolean deleteUser(ArrayList<String> listInputs) throws IOException {
        int i = 0;
        for (User user : usersList) {
            if (user.getName().equals(listInputs.get(0))) {
                if (user.getPassword().getPassword().equals(listInputs.get(1))) {
                    deleteUser(user);
                    return true;
                }
            }
            i++;
        }
        System.out.println("L'utilisateur ou le mdp sont faux.");
        return false;
    }

    public void deleteUser(User userSelected) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ArrayNode array = (ArrayNode) mapper.readTree(fileUsers.file);

        if (array.isArray()) {
            int index = 0;
            for (JsonNode n : array) {
                if (n.get("name").toString().replace("\"", "").equals(userSelected.getName())) {
                    array.remove(index);
                    System.out.println("Utilisateur " + userSelected.getName() + " a été supprimé.");
                    break;
                }
                index++;
            }
            mapper.writeValue(fileUsers.file, array);
        }


        deleteUserInListUsers(userSelected);
        deleteDataFile(userSelected);

    }

    private void deleteUserInListUsers(User userSelected) {
        int i = 0;
        for (User user : usersList) {
            if (user.getName().equals(userSelected.getName())) {
                Session.getInstance().getInstanceUserManager().usersList.remove(i);
                break;
            }
            i++;
        }
    }

    public boolean checkDuplicateUser(String username) {

        for (User user : usersList) {
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ObjectNode javaToJson(User userToConvert) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ObjectNode user = mapper.createObjectNode();

        user.put("name", userToConvert.getName());
        user.put("password", userToConvert.password.getPassword());
        user.put("email", userToConvert.getEmail());

        return user;
    }

    public User jsonToJava(JsonNode node) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Password password = new Password(
                node.get("password").toString().replace("\"", ""),
                true
        );

        User user = new User(
                node.get("name").toString().replace("\"", ""),
                password,
                node.get("email").toString().replace("\"", ""),
                new ArrayList<Site>()
        );

        return user;

    }


    public boolean login(ArrayList<String> ArrayInputs) {


        for (User user : usersList) {
            if (user.getName().equals(ArrayInputs.get(0))) {
                if (user.getPassword().getPassword().equals(ArrayInputs.get(1))) {
                    Session.getInstance().setCurrentUser(user);
                    return true;
                }
            }
        }

        System.out.println("Echec de connexion.");
        return false;
    }


    public void creatDataFile(User newUser) {
        String username = newUser.getName().substring(0, 1).toUpperCase() + newUser.getName().substring(1);
        try {
            File newDataFile = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\" + username + "Data.json").getAbsoluteFile();
            newDataFile.createNewFile();
            PrintWriter writer = new PrintWriter(newDataFile.getAbsoluteFile(), "UTF-8");
            writer.println("[]");
            writer.close();
        } catch (Exception e) {
            System.out.println("Impossible d'écrire dans le dossier d'utilisateurs.");
        }
    }

    public void deleteDataFile(User userSelected) {
        String username = userSelected.getName().substring(0, 1).toUpperCase() + userSelected.getName().substring(1);

        try {
            File newDataFile = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\" + username + "Data.json").getAbsoluteFile();
            newDataFile.delete();
        } catch (Exception e) {
            System.out.println("Impossible d'effacer le fichier utilisateur dans le dossier d'utilisateurs.");
        }
    }

    public boolean addUser(ArrayList<String> inputsForm) throws IOException {

        if (!emailValidity(inputsForm.get(3))) {
            System.out.println("Email invalide.");
            return false;
        }

        if (checkDuplicateUser(inputsForm.get(0))) {
            System.out.println("Utilisateur deja existant.");
            return false;
        }

        if (!inputsForm.get(1).equals(inputsForm.get(2))) {
            System.out.println("Mdp et mdp de confimation different.");
            return false;
        } else if (!creatUser(inputsForm)) {
            System.out.println("Probleme dans la creation de l'utilisateur.");
            return false;
        }

        return true;
    }

    private boolean creatUser(ArrayList<String> inputsForm) throws IOException {

        Password password = new Password(inputsForm.get(1), true);
        User newUser = new User(inputsForm.get(0), password, inputsForm.get(3));

        insertUser(newUser);
        Session.getInstance().getInstanceUserManager().usersList.add(newUser);

        return true;
    }

    private boolean emailValidity(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        // check for valid email addresses using isValid method
        return validator.isValid(email);

    }

    public boolean modificationPassword(ArrayList<String> listInputs) {

        if (!listInputs.get(2).equals(listInputs.get(3))) {
            System.out.println("Nouveau mdp et mdp de confimation different.");
            return false;
        }

        int i = 0;
        for (User user : usersList) {
            if (user.getName().equals(listInputs.get(0))) {
                if (user.getPassword().getPassword().equals(listInputs.get(1))) {
                    user.setPassword(listInputs.get(2));
                    replaceUser(user);
                    return true;
                }
            }
            i++;
        }
        System.out.println("L'utilisateur ou/et le mdp sont faux.");

        return false;
    }

    public void replaceUser(User userSelected) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            ArrayNode array = (ArrayNode) mapper.readTree(fileUsers.file);

            if (array.isArray()) {
                int index = 0;
                for (JsonNode n : array) {
                    if (n.get("name").toString().replace("\"", "").equals(userSelected.getName())) {
                        array.remove(index);
                        ObjectNode user = javaToJson(userSelected);
                        array.add(user);
                        System.out.println("Le mdp de l'utilisateur " + userSelected.getName() + " a été modifie.");
                        break;
                    }
                    index++;
                }
                mapper.writeValue(fileUsers.file, array);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
