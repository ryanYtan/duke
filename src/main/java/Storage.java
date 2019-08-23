import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load()
            throws DukeException {
        try {
            return readFile();
        } catch (IOException e) {
            throw new DukeException("Loading from file failed.");
        }
    }

    public void writeToFile(ArrayList<String> list)
            throws IOException {
        FileWriter fw = new FileWriter(new File(filePath));
        for (String s : list) {
            fw.write(s + "\n");
        }
        fw.close();
    }

    /**
     * Helper method to get lines from the File specified 
     * @return
     * @throws IOException
     */
    private ArrayList<String> readFile()
            throws IOException {
        Stream<String> stream = Files.lines(Paths.get(filePath));
        ArrayList<String> list = new ArrayList<>();
        stream.forEach(x -> list.add(x));
        stream.close();
        return list;
    }
}
