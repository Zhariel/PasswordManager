package fr.esgi.java.passwordmanager.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    public List<Site> listSites(DataFile d){

        List<Site> sites = new ArrayList<Site>();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = null;
        try {
            array = (ArrayNode) mapper.readTree(d.file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SiteManager s = new SiteManager();
        if(array.isArray()){
            for(JsonNode node : array){
                sites.add(s.jsonToJava(node));
            }
        }

        return sites;
    }

    public void insertUser(User u){
        try {
            if(checkDuplicateUser(u.getName())){
                return;
            }
            UserFile f = new UserFile();
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode array = (ArrayNode) mapper.readTree(f.file);

            ObjectNode user = javaToJson(u);

            array.add(user);
            mapper.writeValue(f.file, array);
            System.out.println(u.getName() + " added successfully.");
        }
        catch (Exception e){
            System.out.println("Couldn't add " + u.getName());
            System.out.println(e);
        }
    }

    public void deleteUser(User u){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            UserFile f = new UserFile();
            ArrayNode array = (ArrayNode) mapper.readTree(f.file);

            if(array.isArray()){
                int index = 0;
                for(JsonNode n : array){
                    if(n.get("name").toString().replace("\"", "").equals(u.getName())){
                        array.remove(index);
                        System.out.println("Utilisateur " + u.getName() + " a été supprimé.");
                        break;
                    }
                    index++;
                }
                mapper.writeValue(f.file, array);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDuplicateUser(String username) throws IOException{
        UserFile f = new UserFile();
        String fileString = "";
        fileString = f.fileToString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodeArray = mapper.readTree(fileString);

        if(nodeArray.isArray()){
            for(JsonNode n : nodeArray){
                if(n.get("name").toString().replace("\"", "").equals(username)){
                    System.out.println("Utilisateur " + username + " déjà existant.");
                    return true;
                }
            }
        }
        return false;
    }

    public ObjectNode javaToJson(User u){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ObjectNode user = mapper.createObjectNode();

        user.put("name", u.getName());
        user.put("password", u.password.getPassword());
        user.put("email", u.getEmail());

        return user;
    }

    public void jsonToJava(JsonNode node){

    }
}
