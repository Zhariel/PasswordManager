package fr.esgi.java.passwordmanager.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DataFile {

    private final String username;
    public File file;

    public DataFile(String username) {
        this.username = username.substring(0, 1).toUpperCase() + username.substring(1);
        try {
            this.file = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\" + this.username + "Data.json").getAbsoluteFile();
        } catch (Exception exceptionFileNotFound) {
            System.out.println("Le fichier de donnee utilisateur est introuvable. L'application ne peut fonctionner sans.");
            System.exit(1);
        }
    }

    public String getUsername(){
        return this.username;
    }

    public String fileToString() throws IOException {
        String filename = "src\\fr\\esgi\\java\\passwordmanager\\json\\"+ this.username + "Data.json";
        String jsonString = "";

        StringBuilder builder = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8);

        stream.forEach(s -> builder.append(s).append("\n"));
        return builder.toString();
    }

    public String path(){
        return "json\\"+ this.username + "Data.json";
    }
}
