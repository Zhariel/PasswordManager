package fr.esgi.java.passwordmanager.files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**Class DataFile
 * Represante the dataFile of one user.
 **/

public class DataFile {

    private final String username;
    public File file;


    public DataFile(String username) {
        this.username = username.substring(0, 1).toUpperCase() + username.substring(1);
        try {
            this.file = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\" + this.username + "Data.json").getAbsoluteFile();
        } catch (Exception exceptionFileNotFound) {
            System.out.println("Le fichier de donnee utilisateur est introuvable. Un nouveau fichier donne utilisateur est cree. Les anciennes informations sont perdues.");
            creatDataFile(this.username);
        }
    }


    public void creatDataFile(String userName) {

        String username = userName.substring(0, 1).toUpperCase() + userName.substring(1);
        try {
            File newDataFile = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\" + username + "Data.json").getAbsoluteFile();
            newDataFile.createNewFile();
            PrintWriter writer = new PrintWriter(newDataFile.getAbsoluteFile(), "UTF-8");
            writer.println("[]");
            writer.close();
        } catch (Exception e) {
            System.out.println("Impossible d'écrire dans le dossier d'utilisateurs nomme json.");
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String fileToString() throws IOException {
        String filename = "src\\fr\\esgi\\java\\passwordmanager\\json\\" + this.username + "Data.json";
        String jsonString = "";

        StringBuilder builder = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8);

        stream.forEach(s -> builder.append(s).append("\n"));
        return builder.toString();
    }

    public String path() {
        return "src\\fr\\esgi\\java\\passwordmanager\\json\\" + this.username + "Data.json";
    }
}
