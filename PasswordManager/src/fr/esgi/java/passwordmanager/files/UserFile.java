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
        this.filename = "json\\UserFile.json";
        this.file = new File("json\\UserFile.json").getAbsoluteFile();
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
