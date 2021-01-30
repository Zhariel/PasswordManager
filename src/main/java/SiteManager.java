import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.time.LocalDate;

public class SiteManager {

    public boolean checkDuplicateSite(DataFile d, String site) throws IOException{
        String fileString = "";
        fileString = d.fileToString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodeArray = mapper.readTree(fileString);

        if(nodeArray.isArray()){
            for(JsonNode n : nodeArray){
                if(n.get("name").toString().replace("\"", "").equals(site)){
                    System.out.println("Site " + site + " déjà existant.");
                    return true;
                }
            }
        }

        return false;
    }

    public void insertSite(DataFile d, Site s){
        try {
            if(checkDuplicateSite(d, s.getName())){
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode array = (ArrayNode) mapper.readTree(d.file);

            ObjectNode site = javaToJson(s);

            array.add(site);
            mapper.writeValue(d.file, array);
            System.out.println(s.getName() + " added successfully.");
        }
        catch (Exception e){
            System.out.println("Couldn't add " + s.getName());
            System.out.println(e);
        }
    }

    public void deleteSite(DataFile d, Site s){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            ArrayNode array = (ArrayNode) mapper.readTree(d.file);

            if(array.isArray()){
                int index = 0;
                for(JsonNode n : array){
                    if(n.get("siteName").toString().replace("\"", "").equals(s.getName())){
                        array.remove(index);
                        System.out.println("Site " + s.getName() + " a été supprimé.");
                        break;
                    }
                    index++;
                }
                mapper.writeValue(d.file, array);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectNode javaToJson(Site s){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ObjectNode site = mapper.createObjectNode();
        ObjectNode constraint = mapper.createObjectNode();
        ObjectNode metadata = mapper.createObjectNode();

        constraint.put("totalLength", s.constraint.getTotalLength());
        constraint.put("upperCase", s.constraint.getUpperCase());
        constraint.put("lowerCase", s.constraint.getLowerCase());
        constraint.put("specialChars", s.constraint.getSpecialChars());
        constraint.put("letters", s.constraint.getLetters());
        constraint.put("digits", s.constraint.getDigits());

        metadata.put("dateCreation", s.metaData.getDateCreation().toString());
        metadata.put("duration", s.metaData.getDuration());
        metadata.put("comment", s.metaData.getComment());

        site.put("siteName", s.getName());
        site.put("password", s.getPassword());
        site.put("idUserInSite", s.getIdUserInSite());
        site.put("constraint", constraint);
        site.put("metaData", metadata);

        return site;
    }

    public Site jsonToJava(JsonNode node){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Constraint c = new Constraint(
                node.get("constraint").get("totalLength").asInt(),
                node.get("constraint").get("upperCase").asInt(),
                node.get("constraint").get("lowerCase").asInt(),
                node.get("constraint").get("specialChars").asInt(),
                node.get("constraint").get("letters").asInt(),
                node.get("constraint").get("digits").asInt()
        );

        LocalDate date = LocalDate.parse(node.get("metaData").get("dateCreation").toString().replace("\"", ""));
        Metadata m = new Metadata(
                date,
                node.get("metaData").get("duration").asInt(),
                node.get("metaData").get("comment").toString().replace("\"", "")
        );

        Site s = new Site(
                node.get("name").toString().replace("\"", ""),
                node.get("password").toString().replace("\"", ""),
                node.get("idUserInSite").toString().replace("\"", ""),
                c,
                m
        );

        return s;
    }
}
