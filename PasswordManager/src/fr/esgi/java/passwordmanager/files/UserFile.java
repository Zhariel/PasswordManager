package fr.esgi.java.passwordmanager.files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Class UserFile
 * Represante the userFile containing the list of users of this application.
 **/

public class UserFile {

    private String filename;
    public File file;

    public UserFile() {
        this.filename = "src\\fr\\esgi\\java\\passwordmanager\\json\\UserFile.json";
        try {
            this.file = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\UserFile.json").getAbsoluteFile();

        } catch (Exception exceptionFileNotFound) {
            System.out.println("Le fichier de configuration utilisateur est introuvable. Un nouveau fichier est genere. Les anciens utilisateurs sont perdus.");
            creatDataFile();
        }
    }


    public void creatDataFile() {

        try {
            File newDataFile = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\UserFile.json").getAbsoluteFile();
            newDataFile.createNewFile();
            PrintWriter writer = new PrintWriter(newDataFile.getAbsoluteFile(), "UTF-8");
            writer.println("[]");
            writer.close();
        } catch (Exception e) {
            System.out.println("Impossible d'écrire dans le dossier d'utilisateurs nomme json.");
        }
    }

    public String fileToString() throws IOException {
        String jsonString = "";

        StringBuilder builder = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(this.filename), StandardCharsets.UTF_8);

        stream.forEach(s -> builder.append(s).append("\n"));
        return builder.toString();
    }

    public String getFilename() {
        return filename;
    }
}
