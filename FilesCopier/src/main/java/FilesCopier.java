import java.nio.file.*;
import java.util.Scanner;
import java.util.function.Predicate;

public class FilesCopier {

    public void copyFiles(Path fromCopy, Path forCopy) {
        try {
            Files.walk(fromCopy)
                    .forEach((path ->
                            copy(fromCopy, forCopy, path)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void copy(Path fromCopy, Path forCopy, Path file) {
        try {
            Files.walk(fromCopy)
                    .filter(Predicate.not(Files::isDirectory))
                    .forEach(path ->
                            copyFile(file, forCopy.resolve(fromCopy.relativize(file))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void copyFile(Path fromCopy, Path forCopy) {
        try {
            Files.copy(fromCopy, forCopy, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Path getEnter() {
        Scanner scanner = new Scanner(System.in);
        return Paths.get(scanner.nextLine());
    }
}
