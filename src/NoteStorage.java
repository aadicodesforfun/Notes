import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NoteStorage {

    private final File folder = new File(System.getProperty("user.home"), "SimpleNotes");

    public NoteStorage() {
        folder.mkdirs();
    }

    public List<String> names() {
        String[] files = folder.list((dir, n) -> n.endsWith(".txt"));
        if (files == null) return List.of();
        Arrays.sort(files);
        return Arrays.stream(files).map(f -> f.replace(".txt", "")).collect(Collectors.toList());
    }

    public boolean exists(String name) {
        return file(name).exists();
    }

    public String read(String name) {
        try {
            return Files.readString(file(name).toPath());
        } catch (Exception e) {
            return "";
        }
    }

    public void write(String name, String text) {
        try {
            Files.writeString(file(name).toPath(), text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        file(name).delete();
    }

    private File file(String name) {
        return new File(folder, name + ".txt");
    }
}