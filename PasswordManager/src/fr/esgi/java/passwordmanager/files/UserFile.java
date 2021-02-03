package fr.esgi.java.passwordmanager.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class UserFile {

    private String filename;
    public File file;

    public UserFile(){
        this.filename = "src\\fr\\esgi\\java\\passwordmanager\\json\\UserFile.json";
        try{
            this.file = new File("src\\fr\\esgi\\java\\passwordmanager\\json\\UserFile.json").getAbsoluteFile();

        }catch (Exception exceptionFileNotFound){
            System.out.println("Le fichier de configuration utilisateur est introuvable. L'application ne peut fonctionner sans.");
            System.exit(1);
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
